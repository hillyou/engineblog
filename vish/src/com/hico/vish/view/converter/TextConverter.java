package com.hico.vish.view.converter;

import org.springframework.core.convert.converter.Converter;

import com.google.appengine.api.datastore.Text;

public class TextConverter implements Converter<String, Text>{

	@Override
	public Text convert(String source) {
		if(source==null) {
			source="";
		}
		return new Text(source);
	}

}
