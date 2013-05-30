package com.hico.vish.manager;

import java.util.List;

import com.hico.vish.dao.processor.BlogDao;
import com.hico.vish.dao.table.Blog;
import com.hico.vish.dao.table.UserEntity;

public class BlogManager{
	
	private BlogDao blogDao;
	
	public Blog get(Object blogId) {
		return blogDao.get(blogId);
	}
	
	public void save(Blog blog) {
		blogDao.save(blog);
	}
	
	public List<Blog> getUserBlog(UserEntity user) {
		return blogDao.getBlogList(user);
	}
	
	public void update(Blog blog) {
		blogDao.update(blog);
	}

	/**
	 * @return the blogDao
	 */
	public BlogDao getBlogDao() {
		return blogDao;
	}

	/**
	 * @param blogDao the blogDao to set
	 */
	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}
	
}
