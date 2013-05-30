package com.hico.vish.view.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hico.vish.dao.table.UserEntity;

public class LoginFilter implements Filter{

	private final static String FORBIDDEN_URIS="/user|/admin";
	private final static String REQ_ATTR_MESSAGE="MESSAGE";
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;
		System.out.println("Requesting URL -> "+request.getRequestURL());
		String uri=request.getRequestURI();
		String contextPath=request.getContextPath();
		String[] forbiddens=FORBIDDEN_URIS.split("\\|");
		String email=null;
		Principal principal=request.getUserPrincipal();
		if(principal!=null) {
			email=principal.getName();
		}
		UserEntity user=null;
		if(email!=null) {
			HttpSession session=request.getSession();
			Object usero=session.getAttribute(email);
			if(usero!=null) {
				user=(UserEntity)usero;
			}
		}
		for (String forbidden : forbiddens) {
			if((email==null || user==null || (isInvalidBlogger(user))) && uri.startsWith(contextPath+forbidden)) {
				request.setAttribute(REQ_ATTR_MESSAGE, "User forbidden,Click <a href=\""+contextPath+"/home.html\">here</a> go to home page.");
				request.getRequestDispatcher("/error.html").forward(request, response);
				return;
			}
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		
	}
	
	private boolean isInvalidBlogger(UserEntity currentUser) {
		return !(!currentUser.isDeleted() && !currentUser.isLocked() && currentUser.isValid());
	}

}
