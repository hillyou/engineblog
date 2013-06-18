package com.hico.vish.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jsr107cache.Cache;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;

public class MessageProcessor extends MessageChecker {
	private final static Logger LOGGER=LoggerFactory.getLogger(MessageProcessor.class);
	private Cache messageCacher=CacheService.getCache();

	@Override
	public void cache(Message message) {
		String from=message.getFromJid().getId();
		JID[] tos=message.getRecipientJids();
		String key=from;
		if(tos.length>1) {
			key+=tos[0].getId();
		}
		LOGGER.info("putting into cache {} <> {}",key,message.getBody());
		this.messageCacher.put(key, message);
	}
	
}
