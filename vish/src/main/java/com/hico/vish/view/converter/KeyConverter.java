package com.hico.vish.view.converter;

import org.springframework.core.convert.converter.Converter;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.util.KeyUtil;

public class KeyConverter implements Converter<String, Key>{
private final static char SPLITOR=':';
	@Override
	public Key convert(String source) {
		if(source==null || "".equals(source) || source.indexOf(SPLITOR)<0) {
			return null;
		}
		
		try {
			return KeyUtil.stringToKey(source);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
}
