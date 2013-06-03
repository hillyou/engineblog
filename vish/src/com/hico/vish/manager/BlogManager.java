package com.hico.vish.manager;

import java.util.List;

import com.hico.vish.dao.processor.BlogDao;
import com.hico.vish.dao.table.Blog;
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
	
}
