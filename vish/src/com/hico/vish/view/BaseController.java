package com.hico.vish.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.AppUser;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.manager.ArticleManager;
import com.hico.vish.manager.BlogManager;
import com.hico.vish.manager.CategoryManager;
import com.hico.vish.manager.UserManager;
import com.hico.vish.util.UserUtil;

public abstract class BaseController {

	protected final static String REQ_ATTR_MESSAGE="MESSAGE";
	protected final static String REQ_ATTR_CURRENT_USER="CURRENT_USER";
	
	@Autowired
	protected ArticleManager articleManager;
	
	@Autowired
	protected UserManager userManager;
	
	@Autowired
	protected CategoryManager categoryManager;
	
	@Autowired
	protected BlogManager blogManager;
	
	private static int counter=0;
	@ModelAttribute("CONTER")
	public String beforeController() {
		String counters=String.valueOf(++counter);
		return counters;
	}
	
	@ModelAttribute("CURRENT_USER")
	public UserEntity retriveCurrentUser(Model model,HttpServletRequest request) {
		UserEntity loginUser=null;
		AppUser user=UserUtil.getAppUser();
		if(!user.isLogin()) {
			return loginUser;
		}
		String userEmail = user.getEmail();
		HttpSession session=request.getSession();
		Object usero=session.getAttribute(userEmail);
		if(usero!=null) {
			loginUser=(UserEntity)usero;
		}else {
			loginUser=userManager.getUserByEmail(userEmail);
			if(loginUser==null) {
				loginUser=new UserEntity();
				loginUser.setUserName(userEmail);
				loginUser.setEmail(userEmail);
				loginUser.setNickName(user.getNickName());
				loginUser.setUserId(user.getUserId());
				userManager.save(loginUser);
			}
			session.setAttribute(userEmail, loginUser);
		}
		model.addAttribute(loginUser.getEmail(), loginUser);
		return loginUser;
	}
	
	protected UserEntity getCurrentUser(Model model) {
		return (UserEntity)model.asMap().get(REQ_ATTR_CURRENT_USER);
	}
	

	protected void updateUserInSession(Model model,UserEntity loginUser) {
		model.addAttribute(loginUser.getEmail(), loginUser);
	}
	
	
//	protected void updateUserInSession(HttpServletRequest request,UserEntity loginUser) {
//		HttpSession session=request.getSession();
//		session.removeAttribute(loginUser.getEmail());
//		session.setAttribute(loginUser.getEmail(), loginUser);
//	}

	
	protected UserEntity retrieveFlushUser(UserEntity loginUser) {
		Key blogKey=loginUser.getCurrentBlogKey();
		UserEntity persistent=userManager.get(loginUser.getKey());
		persistent.setCurrentBlog(blogKey);
		return persistent;
	}
}
