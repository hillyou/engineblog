package com.hico.vish.service;

import java.util.Date;
import java.util.Observable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.hico.vish.dao.table.XMPPMessage;

@Service
public class MessageMonitor extends Observable{
	private final static Logger LOGGER=LoggerFactory.getLogger(MessageMonitor.class);
	private MessageCacher cache=MessageCacher.getInstance();
	public void process(Message message) {
		XMPPMessage cacheMessage=convert(message);
		LOGGER.info("putting into cache {} {} {}",cacheMessage.getFromId(),cacheMessage.getCreateDate(),cacheMessage.getMessage());
		cache.put(cacheMessage);
		super.setChanged();
		super.notifyObservers(message);
	}
	
	private XMPPMessage convert(Message message) {
		XMPPMessage xmpp=new XMPPMessage();
		xmpp.setFromId(message.getFromJid().getId());
		xmpp.setToId(getIds(message.getRecipientJids()));
		xmpp.setMessage(message.getBody());
		xmpp.setType(message.getMessageType().name());
		xmpp.setCreateDate(new Date());
		return xmpp;
	}
	

	private String[] getIds(JID[] jids) {
		String[] ids=new String[jids.length];
		int index=0;
		for (JID jid : jids) {
			ids[index++]=jid.getId();
		}
		return ids;
	}
}
