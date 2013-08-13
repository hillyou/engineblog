package com.hico.vish.dao.table;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;

@PersistenceCapable(detachable = "true")
@Inheritance(customStrategy = "complete-table")
public class XMPPMessage extends StatusEntity {
	private static final long serialVersionUID = 7425401597278149771L;
	@Persistent
	private String fromId;
	@Persistent
	private String toId[];
	@Persistent
	private String type;
	@Persistent
	private String message;
	
	public XMPPMessage() {}
	
	public XMPPMessage(Message xmpp) {
		this.fromId = xmpp.getFromJid().getId();
		this.toId = getIds(xmpp.getRecipientJids());
		this.message = xmpp.getBody();
		
	}
	
	private String[] getIds(JID[] jids) {
		String[] ids=new String[jids.length];
		int index=0;
		for (JID jid : jids) {
			ids[index++]=jid.getId();
		}
		return ids;
	}
	
	public XMPPMessage(String fromId, String[] toId, String type, String message) {
		this.fromId = fromId;
		this.toId = toId;
		this.type = type;
		this.message = message;
	}
	/**
	 * @return the fromId
	 */
	public String getFromId() {
		return fromId;
	}
	/**
	 * @param fromId the fromId to set
	 */
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	/**
	 * @return the toId
	 */
	public String[] getToId() {
		return toId;
	}
	/**
	 * @param toId the toId to set
	 */
	public void setToId(String[] toId) {
		this.toId = toId;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
