package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;

@SpringBootApplication
public class SBApplication {

	public static void main(String[] args) {
		SpringApplication.run(SBApplication.class, args);
	}

	@Bean
	@DependsOn("configuration")
	public Conversation getWatsonConversation() {
		Conversation service = new Conversation(Conversation.VERSION_DATE_2017_05_26);
		service.setUsernameAndPassword(getConfiguration().getWatsonConversationUsername(),
				getConfiguration().getWatsonConversationPassword());
		return service;
	}

	@Bean
	@DependsOn("configuration")
	public Discovery getWatsonDiscovery() {
		Discovery discovery = new Discovery(Discovery.VERSION_DATE_2017_11_07);
		discovery.setEndPoint(getConfiguration().getWatsonDiscoveryUrl());
		discovery.setUsernameAndPassword(getConfiguration().getWatsonDiscoveryUsername(),
				getConfiguration().getWatsonDiscoveryPassword());
		return discovery;
	}

	@Bean
	@DependsOn("configuration")
	public TextToSpeech getTextToSpeech() {
		TextToSpeech ttp = new TextToSpeech();
		ttp.setEndPoint(getConfiguration().getTextToSpeechUrl());
		ttp.setUsernameAndPassword(getConfiguration().getTextToSpeechUsername(),
				getConfiguration().getTextToSpeechPassword());
		return ttp;
	}

	@Bean(name = "configuration")
	public IbmLabConfiguration getConfiguration() {
		return new IbmLabConfiguration();
	}

	// @Bean
	// public CloudantClient cloudantclient() {
	// try {
	// return ClientBuilder.url(new URL("http://localhost:5984")).build();
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	// @Bean
	// @DependsOn("configuration")
	// public Database cloudantDatabase() {
	// Database db = null;
	// try {
	// System.out.println("URL=" + getConfiguration().getCouchDbUrl());
	//
	// CloudantClient client = ClientBuilder.url(new
	// URL(getConfiguration().getCouchDbUrl())).build();
	// //
	// .username(dbconfig.getUsername()).password(dbconfig.getPassword()).build();
	//
	// // Get a Database instance to interact with, but don't create it if it
	// doesn't
	// // already exist
	// db = client.database(getConfiguration().getCouchDbName(), true);
	// System.out.println(db.info().toString());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return db;
	// }

}
