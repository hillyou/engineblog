package com.hico.vish.service;

import java.util.Observable;

import com.google.appengine.api.xmpp.Message;

public class MessageMonitor extends Observable{

	public void process(Message message) {
		super.setChanged();
		super.notifyObservers(message);
	}
	
}
