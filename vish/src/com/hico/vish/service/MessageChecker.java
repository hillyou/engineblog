package com.hico.vish.service;

import java.util.Observable;

import com.google.appengine.api.xmpp.Message;

public abstract class MessageChecker extends Observable{
	public void process(Message message) {
		if(check(message)) {
			cache(message);
		}
	}
	
	protected abstract boolean check(Message message);
	
	protected abstract void cache(Message message);
	
}
