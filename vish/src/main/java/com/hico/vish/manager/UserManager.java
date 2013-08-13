package com.hico.vish.manager;

import com.hico.vish.dao.processor.UserDao;
import com.hico.vish.dao.table.Blog;
import com.hico.vish.dao.table.UserEntity;

public class UserManager extends BaseManager<UserEntity>{

	public UserEntity getUserByEmail(String email) {
		return ((UserDao)dao).getUserByEmail(email);
	}

	public UserEntity addBlog(Blog blog) {
		return ((UserDao)dao).addBlog(blog);
	}

}
