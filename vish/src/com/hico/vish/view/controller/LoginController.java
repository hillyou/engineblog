package com.hico.vish.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
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
	public String logout(HttpServletRequest request) {
		UserService userService = UserServiceFactory.getUserService();
		String targetURL="/home.html";
		String url=userService.createLogoutURL(targetURL);
		String contextPath=request.getContextPath();
		String loginURL="redirect:"+url.substring(contextPath.length());
		return loginURL;
	}
	
}
