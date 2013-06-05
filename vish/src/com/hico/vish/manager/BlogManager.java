package com.hico.vish.manager;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.processor.BlogDao;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Blog;
import com.hico.vish.dao.table.Category;
import com.hico.vish.dao.table.UserEntity;

public class BlogManager extends BaseManager<Blog>{
	
	public Blog fetchBlogArticle(Object id){
		return ((BlogDao)dao).fetchBlogArticle(id);
	}
	
	public Blog fetchBlogArticle(String blogName){
		return ((BlogDao)dao).getByNameWithArticles(blogName);
	}
	
	public List<Blog> getUserBlog(UserEntity user) {
		return ((BlogDao)dao).getBlogList(user);
	}

	public void addArticle(Article article) {
		((BlogDao)dao).addArticle(article);
	}

	public void addCategory(Category category) {
		((BlogDao)dao).addCategory(category);
	}
	
	public void deleteCategory(Key categoryKey,boolean isDeleteArticle) {
		((BlogDao)dao).deleteCategory(categoryKey,isDeleteArticle);
	}
}
