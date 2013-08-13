package com.hico.vish.dao.table;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;

public class AppUserImpl implements AppUser {
	private UserService userService;

	public AppUserImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String getUserName() {
		return getUser().getAuthDomain();
	}

	@Override
	public String getNickName() {
		return getUser().getNickname();
	}

	@Override
	public String getEmail() {
		return getUser().getEmail();
	}

	private User getUser() {
		return userService.getCurrentUser();
	}
	
	public boolean isLogin() {
		User user=getUser();
		if(user!=null && user.getEmail()!=null) {
			return true;
		}
		return false;
	}

	@Override
	public String getUserId() {
		return getUser().getUserId();
	}

}
