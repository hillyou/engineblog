package com.hico.vish.view.controller;

public enum EntityStatus {

	USABLE("USABLE"),
	UNUSABLE("UNUSABLE"),
	DELETED("DELETED"),
	INVALID("INVALID"),
	LOCKED("LOCKED");
	
	private String statusName;
	
	EntityStatus(String name){
		this.statusName=name;
	}
	
	public String getValue(){
		return statusName;
	}
}
