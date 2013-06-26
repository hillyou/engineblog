package com.hico.vish.util;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.hico.vish.dao.table.AppUser;
import com.hico.vish.dao.table.AppUserImpl;

public final class UserUtil {
	
	private static final UserService USERSERVICE=UserServiceFactory.getUserService();
	
	public static AppUser getAppUser() {
		return new AppUserImpl(USERSERVICE);
	}
	
	public static boolean isAdmin() {
		return USERSERVICE.isUserAdmin();
	}
}
