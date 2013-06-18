package com.hico.vish.service;

import java.util.Observable;
import java.util.Observer;

import com.google.appengine.api.xmpp.Message;

public abstract class MessageChecker implements Observer{
	
	@Override
	public void update(Observable o, Object message) {
		if(check((Message)message)) {
			cache((Message)message);
		}
	}
	
	protected boolean check(Message message) {
		return true;
	}
	
	public abstract void cache(Message message);
}
