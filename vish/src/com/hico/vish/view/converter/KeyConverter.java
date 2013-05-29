package com.hico.vish.view.converter;

import org.springframework.core.convert.converter.Converter;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class KeyConverter implements Converter<String, Key>{
private final static char SPLITOR=':';
	@Override
	public Key convert(String source) {
		if(source==null || "".equals(source) || source.indexOf(SPLITOR)<0) {
			return null;
		}
		int splitorIndex=source.indexOf(SPLITOR);
		String className=source.trim().substring(0,splitorIndex);
		String id=source.trim().substring(splitorIndex+1);
		
		Key key=null;
		try {
			key=KeyFactory.createKey(className, Long.valueOf(id));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return key;
	}
	
}
