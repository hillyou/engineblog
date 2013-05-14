package com.hico.vish.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.dao.table.AppUser;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.manager.UserManager;
import com.hico.vish.util.UserUtil;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserManager userManager;
	
	@RequestMapping("/setting/openblog")
	public String updateUser() {
		UserEntity user=getCurrentUser();
		user.setBloger(true);
		userManager.saveOrUpdateUser(user);
		return "backend/article/create";
	}
	
	private UserEntity getCurrentUser() {
		AppUser user=UserUtil.getAppUser();
		UserEntity loginUser=userManager.getUserByEmail(user.getEmail());
		return loginUser;
	}
	
	
	private boolean isValidBlogger() {
		UserEntity user=getCurrentUser();
		return isValidBlogger(user);
	}
	
	private boolean isValidBlogger(UserEntity user) {
		return user.isBloger() && !user.isDeleted() && !user.isLocked() && user.isValid();
	}
	
}
