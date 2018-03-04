package application.entities;

import com.ibm.watson.developer_cloud.conversation.v1.model.Context;

public class Utilisateur {

	private String _id;

	private String name;

	private String conversationId;

	private String _rev;

	private Context context;

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String get_rev() {
		return _rev;
	}

	public void set_rev(String rev) {
		this._rev = rev;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

}
