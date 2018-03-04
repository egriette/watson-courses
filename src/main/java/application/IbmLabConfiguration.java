package application;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public class IbmLabConfiguration {

	private String discoveryCollectionId;

	private String discoveryEnvironmentId;

	private String discoveryConversationId;

	private String watsonConversationUsername;

	private String watsonConversationPassword;

	private String watsonDiscoveryUsername;

	private String watsonDiscoveryPassword;

	private String watsonDiscoveryUrl;

	private String textToSpeechUrl;

	private String textToSpeechUsername;

	private String textToSpeechPassword;

	private String couchDbUrl;

	private String couchDbName;

	public String getDiscoveryCollectionId() {
		return discoveryCollectionId;
	}

	public void setDiscoveryCollectionId(String discoveryCollectionId) {
		this.discoveryCollectionId = discoveryCollectionId;
	}

	public String getDiscoveryEnvironmentId() {
		return discoveryEnvironmentId;
	}

	public void setDiscoveryEnvironmentId(String discoveryEnvironmentId) {
		this.discoveryEnvironmentId = discoveryEnvironmentId;
	}

	public String getConversationEnvironmentId() {
		return discoveryConversationId;
	}

	public void setConversationEnvironmentId(String discoveryConversationId) {
		this.discoveryConversationId = discoveryConversationId;
	}

	public String getWatsonConversationUsername() {
		return watsonConversationUsername;
	}

	public void setWatsonConversationUsername(String watsonConversationUsername) {
		this.watsonConversationUsername = watsonConversationUsername;
	}

	public String getWatsonConversationPassword() {
		return watsonConversationPassword;
	}

	public void setWatsonConversationPassword(String watsonConversationPassword) {
		this.watsonConversationPassword = watsonConversationPassword;
	}

	public String getWatsonDiscoveryUsername() {
		return watsonDiscoveryUsername;
	}

	public void setWatsonDiscoveryUsername(String watsonDiscoveryUsername) {
		this.watsonDiscoveryUsername = watsonDiscoveryUsername;
	}

	public String getWatsonDiscoveryPassword() {
		return watsonDiscoveryPassword;
	}

	public void setWatsonDiscoveryPassword(String watsonDiscoveryPassword) {
		this.watsonDiscoveryPassword = watsonDiscoveryPassword;
	}

	public String getWatsonDiscoveryUrl() {
		return watsonDiscoveryUrl;
	}

	public void setWatsonDiscoveryUrl(String watsonDiscoveryUrl) {
		this.watsonDiscoveryUrl = watsonDiscoveryUrl;
	}

	public String getCouchDbUrl() {
		return couchDbUrl;
	}

	public void setCouchDbUrl(String couchDbUrl) {
		this.couchDbUrl = couchDbUrl;
	}

	public String getCouchDbName() {
		return couchDbName;
	}

	public void setCouchDbName(String couchDbName) {
		this.couchDbName = couchDbName;
	}

	public String getTextToSpeechUrl() {
		return textToSpeechUrl;
	}

	public void setTextToSpeechUrl(String textToSpeechUrl) {
		this.textToSpeechUrl = textToSpeechUrl;
	}

	public String getTextToSpeechUsername() {
		return textToSpeechUsername;
	}

	public void setTextToSpeechUsername(String textToSpeechUsername) {
		this.textToSpeechUsername = textToSpeechUsername;
	}

	public String getTextToSpeechPassword() {
		return textToSpeechPassword;
	}

	public void setTextToSpeechPassword(String textToSpeechPassword) {
		this.textToSpeechPassword = textToSpeechPassword;
	}

}
