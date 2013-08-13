package com.hico.vish.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.hico.vish.dao.table.XMPPMessage;

public class MessageCacher {
	private final static MessageCacher INSTANCE = new MessageCacher();
	private List<XMPPMessage> cache=Collections.synchronizedList(new LinkedList<XMPPMessage>());
	private MessageCacher() {}
	
	public static MessageCacher getInstance() {
		return INSTANCE;
	}
	
	public List<XMPPMessage> getAll(){
		return cache;
	}
	
	public void put(XMPPMessage message) {
		cache.add(message);
	}
	
	public void removeAll(){
		cache.clear();
	}
}
