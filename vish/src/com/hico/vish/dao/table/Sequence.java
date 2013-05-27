package com.hico.vish.dao.table;

import java.io.Serializable;

public class Sequence implements Comparable<String>,Serializable{
	private static final long serialVersionUID = 8203253829819751724L;
	private String position;
	
	public Sequence(String position) {
		this.position=position;
	}

	@Override
	public int compareTo(String o) {
		if(position==null || o==null) {
			return -1;
		}
		return o.compareTo(position);
	}
	
	
}
