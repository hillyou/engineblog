package com.hico.vish.view.converter;

import org.springframework.core.convert.converter.Converter;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Category;
import com.hico.vish.util.KeyUtil;

public class CategoryConverter implements Converter<String, Category>{
	@Override
	public Category convert(String source) {
		if(source!=null && !"".equals(source)){
			Key categoryKey=KeyUtil.stringToKey(source);
			if(categoryKey!=null){
				Category category=new Category();
				category.setKey(categoryKey);
				return category;
			}
		}
		return null;
	}
	
}
