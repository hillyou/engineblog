package com.hico.vish.service;

import java.util.HashSet;
import java.util.Set;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheStatistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.appengine.api.xmpp.Message;
import com.hico.vish.dao.table.XMPPMessage;
import com.hico.vish.manager.XMPPMessageManager;

public class MessageSaver {
	private final static Logger LOGGER=LoggerFactory.getLogger(MessageSaver.class);
	private static Cache messageCache=CacheService.getCache();
	@Autowired
	private XMPPMessageManager messageManager;
	private final static int CACHE_LIMIT_LENGTH=100;
	private final static int CACHE_LIMIT_SIZE=20;
	
	public void synchCache() {
		if(checkCahce()) {
			saveMessages();
		}
	}
	
	public void flush() {
		saveMessages();
	}
	
	private void saveMessages() {
		Set<String> messageKeys=messageCache.keySet();
		Set<XMPPMessage> messages=new HashSet<XMPPMessage>();
		for(String key:messageKeys) {
			Message message=(Message)messageCache.get(key);
			messages.add(new XMPPMessage(message));
		}
		messageManager.saveMessages(messages);
		LOGGER.info("{} messages have been saved",messages.size());
		messages.removeAll(messages);
		LOGGER.debug("removing message from cache after saved into DB");
	}
	
	
	private boolean checkCahce() {
		CacheStatistics statistics=messageCache.getCacheStatistics();
		int count=statistics.getObjectCount();
		int size=statistics.getStatisticsAccuracy();
		LOGGER.debug("checking cache information, count {}, size {}",count,size);
		if(count>CACHE_LIMIT_LENGTH || size>CACHE_LIMIT_SIZE*1024) {
			return true;
		}
		return false;
	}
	
}
