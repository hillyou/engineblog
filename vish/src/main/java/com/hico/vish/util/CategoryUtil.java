package com.hico.vish.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Category;

public final class CategoryUtil {

	public static List<Category> getSubCategoriesAndSelf(Collection<Category> categories,Key parentKey){
		List<Category> subsAndself=new ArrayList<Category>();
		if(categories!=null){
			for(Category category:categories){
				if(category.getKey().equals(parentKey)){
					getSubCategories(categories,parentKey,subsAndself);
					subsAndself.add(category);
					break;
				}
			}
		}
		return subsAndself;
	}
	
	
	
	public static void getSubCategories(Collection<Category> categories,Key key,List<Category> subs){
		for(Category category:categories){
			if(category.getParentKey()!=null && category.getParentKey().equals(key)){
				getSubCategories(categories,category.getKey(),subs);
				subs.add(category);
			}
		}
	}
	
	public static List<Article> getArticlesUnderCategory(List<Article> articles,List<Category> categories) {
		List<Article> articlesUnderCategory=new ArrayList<Article>();
		for(Category category:categories) {
			for(Article article:articles) {
				if(category.getKey().equals(article.getCategory())) {
					articlesUnderCategory.add(article);
				}
			}
			
		}
		return articlesUnderCategory;
	}
	
}
