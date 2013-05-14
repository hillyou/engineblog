package com.hico.vish.util;

import com.google.appengine.api.users.UserServiceFactory;
import com.hico.vish.dao.table.AppUser;
import com.hico.vish.dao.table.AppUserImpl;

public class UserUtil {
	
	public static AppUser getAppUser() {
		return new AppUserImpl(UserServiceFactory.getUserService());
	}
}
