package application.resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.cloudant.client.org.lightcouch.NoDocumentException;
import com.google.gson.internal.LinkedTreeMap;
import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryOptions;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResult;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.AudioFormat;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

import application.IbmLabConfiguration;
import application.entities.TextInput;
import application.entities.UserInput;
import application.entities.Utilisateur;
import application.entities.WatsonResponse;

@RestController
public class MyConversation {

	@Autowired
	private Database dbClient;

	@Autowired
	private IbmLabConfiguration configuration;

	@Autowired
	protected Discovery discovery;

	@Autowired
	protected Conversation conversation;

	@Autowired
	private TextToSpeech textToSpeech;

	@RequestMapping("v1/")
	public @ResponseBody ResponseEntity<String> example() {
		List<String> list = new ArrayList<>();
		// return a simple list of strings
		list.add("Congratulations, your application is up and running");
		return new ResponseEntity<String>(list.toString(), HttpStatus.OK);
	}

	// @RequestMapping("v1/cloudant")
	// public @ResponseBody ResponseEntity<String> cloudant() {
	// List<String> list = new ArrayList<>();
	// try {
	// list = client.getAllDbs();
	// } catch (NullPointerException e) {
	// return new ResponseEntity<String>("Server Error",
	// HttpStatus.INTERNAL_SERVER_ERROR);
	// }
	// return new ResponseEntity<String>("Available databases : " + list.toString(),
	// HttpStatus.OK);
	// }

	@RequestMapping("v1/discover")
	public @ResponseBody ResponseEntity<String> discover(String query) {
		QueryOptions options = new QueryOptions.Builder(configuration.getDiscoveryEnvironmentId(),
				configuration.getDiscoveryCollectionId()).naturalLanguageQuery(query).build();
		QueryResponse queryResponse = discovery.query(options).execute();

		List<QueryResult> results = queryResponse.getResults();
		Stream<ArrayList> res0 = results.stream().map(r -> (LinkedTreeMap) r.get("enriched_text"))
				.map(r -> (ArrayList) r.get("entities"));
		Stream<String> res = res0.map(r -> (LinkedTreeMap) r.get(0)).map(r -> (String) r.get("text"));
		String titles = res.collect(Collectors.joining("\n<p>"));

		return new ResponseEntity<String>(titles, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "v1/conversation/{user}/message", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<WatsonResponse> askWatson(@PathVariable("user") String userId,
			@RequestBody UserInput jsonString) {
		Utilisateur user = null;
		try {
			user = dbClient.find(Utilisateur.class, userId);
		} catch (NoDocumentException e) {
			user = new Utilisateur();
			user.set_id(userId);
			user.setName("me");
			Response resp = dbClient.post(user);
			user.set_rev(resp.getRev());
		}

		InputData input = new InputData.Builder(jsonString.getSource()).build();
		MessageOptions options = null;
		if (user.getContext() != null) {
			options = new MessageOptions.Builder(configuration.getConversationEnvironmentId())
					.context(user.getContext()).input(input).build();
		} else {
			options = new MessageOptions.Builder(configuration.getConversationEnvironmentId()).input(input).build();
		}

		MessageResponse response = conversation.message(options).execute();
		user.setContext(response.getContext());

		com.cloudant.client.api.model.Response clResp = dbClient.update(user);

		WatsonResponse resp = new WatsonResponse();
		resp.setSource(response.getOutput().getText());
		return new ResponseEntity<WatsonResponse>(resp, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "v1/texttospeech/{user}/synthetise-stream", method = RequestMethod.POST)
	public @ResponseBody StreamingResponseBody synthesizeStream(@PathVariable("user") String userId,
			@RequestBody TextInput jsonString) {
		Utilisateur user = null;
		try {
			user = dbClient.find(Utilisateur.class, userId);
		} catch (NoDocumentException e) {
			user = new Utilisateur();
			user.set_id(userId);
			user.setName("me");
			Response resp = dbClient.post(user);
			user.set_rev(resp.getRev());
		}

		return new StreamingResponseBody() {
			@Override
			public void writeTo(OutputStream out) throws IOException {
				String text = jsonString.getText();
				InputStream stream = textToSpeech.synthesize(text, Voice.EN_ALLISON, AudioFormat.WAV).execute();
				InputStream in = WaveUtils.reWriteWaveHeader(stream);
				// out = new FileOutputStream("hello_world.wav");
				byte[] buffer = new byte[1024];
				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
				out.flush();
				in.close();
				stream.close();
			}
		};
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "v1/texttospeech/{user}/synthetise", method = RequestMethod.POST, produces = "audio/wav")
	public @ResponseBody ResponseEntity<byte[]> synthesize(@PathVariable("user") String userId,
			@RequestBody TextInput jsonString) throws IOException {
		Utilisateur user = null;
		try {
			user = dbClient.find(Utilisateur.class, userId);
		} catch (NoDocumentException e) {
			user = new Utilisateur();
			user.set_id(userId);
			user.setName("me");
			Response resp = dbClient.post(user);
			user.set_rev(resp.getRev());
		}

		String text = jsonString.getText();
		InputStream stream = textToSpeech.synthesize(text, Voice.FR_RENEE, AudioFormat.WAV).execute();
		InputStream in = WaveUtils.reWriteWaveHeader(stream);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("audio", "wav"));
		headers.setContentDispositionFormData("inline", "audio.wav");

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}

		headers.setContentLength(out.size());
		ResponseEntity<byte[]> ret = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);

		out.flush();
		in.close();
		stream.close();

		return ret;
	}

}
