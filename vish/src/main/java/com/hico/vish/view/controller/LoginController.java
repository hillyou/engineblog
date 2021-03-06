package com.hico.vish.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping("/site")
public class LoginController extends BaseController{

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		UserService userService = UserServiceFactory.getUserService();
		String targetURL=request.getHeader("Referer");
		if(targetURL==null || "".equals(targetURL.trim())) {
			targetURL="/home.html";
		}
		String url=userService.createLoginURL(targetURL);
		String contextPath=request.getContextPath();
		String loginURL="redirect:"+url.substring(contextPath.length());
		return loginURL;
	}
	
	@RequestMapping("/logout")
	public String logout(Model model,HttpServletRequest request) {
		UserEntity user=getCurrentUser(model);
		HttpSession session=request.getSession();
		session.removeAttribute(user.getEmail());
		session.invalidate();
		invalidatedUser(model);
		
		UserService userService = UserServiceFactory.getUserService();
		String targetURL="/home.html";
		String url=userService.createLogoutURL(targetURL);
		String contextPath=request.getContextPath();
		String loginURL="redirect:"+url.substring(contextPath.length());
		return loginURL;
	}
	
	private synchronized void invalidatedUser(Model model) {
		model.asMap().remove(REQ_ATTR_CURRENT_USER);
	}
}
