package com.hico.vish.service;

import com.google.appengine.api.xmpp.Message;

public class MessageProcessor extends MessageChecker{

	@Override
	public boolean check(Message message) {
		return false;
	}

	@Override
	public void cache(Message message) {
		
	}

	
	
}
