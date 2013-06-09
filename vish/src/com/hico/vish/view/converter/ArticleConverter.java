package com.hico.vish.view.converter;

import org.springframework.core.convert.converter.Converter;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Article;
import com.hico.vish.util.KeyUtil;

public class ArticleConverter implements Converter<String, Article>{

	@Override
	public Article convert(String source) {
		if(source!=null && !"".equals(source)){
			Key articleKey=KeyUtil.stringToKey(source);
			if(articleKey!=null){
				Article article=new Article();
				article.setKey(articleKey);
				return article;
			}
		}
		return null;
	}

}
