package com.hico.vish.view.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public void updateUser(HttpServletRequest request,HttpServletResponse response) {
		UserEntity user=getCurrentUser();
		user.setBloger(true);
		userManager.saveOrUpdateUser(user);
		try {
			response.setContentType("text/html");
			PrintWriter printWriter=response.getWriter();
			printWriter.println("Blog opened successfully<br>");
			printWriter.println("<a href=\""+request.getContextPath()+"/article/user/createarticle.html\">go write blog</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
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
