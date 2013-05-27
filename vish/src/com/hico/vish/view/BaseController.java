package com.hico.vish.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hico.vish.dao.table.AppUser;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.manager.UserManager;
import com.hico.vish.util.UserUtil;

public abstract class BaseController {

	@Autowired
	private UserManager userManager;
	protected final static String REQ_ATTR_CURRENT_USER="CURRENT_USER";
	
	private static int counter=0;
	@ModelAttribute("CONTER")
	public String beforeController() {
		String counters=String.valueOf(++counter);
		System.out.println(counters);
		return counters;
	}
	
	@ModelAttribute("CURRENT_USER")
	public UserEntity retriveCurrentUser(HttpServletRequest request) {
		AppUser user=UserUtil.getAppUser();
		String userEmail = user.getEmail();
		HttpSession session=request.getSession();
		Object usero=session.getAttribute(userEmail);
		if(usero!=null) {
			return (UserEntity)usero;
		}
		UserEntity loginUser=userManager.getUserByEmail(userEmail);
		if(loginUser==null) {
			loginUser=new UserEntity();
			loginUser.setUserName(userEmail);
			loginUser.setEmail(userEmail);
			loginUser.setNickName(user.getNickName());
			loginUser.setUserId(user.getUserId());
			userManager.saveOrUpdateUser(loginUser);
		}
		session.setAttribute(userEmail, loginUser);
		return loginUser;
	}
	
	protected UserEntity getCurrentUser(HttpServletRequest request) {
		return (UserEntity)request.getAttribute(REQ_ATTR_CURRENT_USER);
	}
	
	protected boolean isValidBlogger(HttpServletRequest request) {
		UserEntity currentUser=getCurrentUser(request);
		return currentUser.isBloger() && !currentUser.isDeleted() && !currentUser.isLocked() && currentUser.isValid();
	}
	

}
