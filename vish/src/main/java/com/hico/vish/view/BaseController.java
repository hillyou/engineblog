package com.hico.vish.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

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
	private static ThreadLocal threadLocal=new ThreadLocal();
	
	@Autowired
	protected ArticleManager articleManager;
	
	@Autowired
	protected UserManager userManager;
	
	@Autowired
	protected CategoryManager categoryManager;
	
	@Autowired
	protected BlogManager blogManager;
	
	@ModelAttribute("CURRENT_USER")
	public synchronized UserEntity retriveCurrentUser(HttpServletRequest request) {
		UserEntity loginUser=null;
		AppUser user=UserUtil.getAppUser();
		if(!user.isLogin()) {
			return null;
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
		return loginUser;
	}
	
	private Object getModelAttribute(Model model,Object attrName) {
		return model.asMap().get(attrName);
	}
	
	protected UserEntity getCurrentUser(Model model) {
		return (UserEntity)getModelAttribute(model,REQ_ATTR_CURRENT_USER);
	}
	
	protected synchronized void updateUserInSession(HttpServletRequest request,UserEntity loginUser) {
		HttpSession session=request.getSession();
		session.removeAttribute(loginUser.getEmail());
		session.setAttribute(loginUser.getEmail(), loginUser);
	}
	
	public void set(Object value) {
		threadLocal.set(value);
	}
	
	public Object get() {
		return threadLocal.get();
	}
}
