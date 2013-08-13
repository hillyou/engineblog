package com.hico.vish.shared;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class UserRequest implements IUserRequest{

	private HttpServletRequest request;
	
	public UserRequest(HttpServletRequest request) {
		this.request=request;
	}

	@Override
	public String getUserEmail() {
		
		Principal principal=request.getUserPrincipal();
		if(principal!=null) {
			return principal.getName();
		}
		return "";
	}

	@Override
	public String getRequestURL() {
		return request.getRequestURL().toString();
	}

	@Override
	public Date getRequestDate() {
		return new Date();
	}
	
	public String getRemoteHost() {
		return request.getRemoteHost();
	}
	
}
