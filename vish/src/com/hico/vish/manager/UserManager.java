package com.hico.vish.manager;

import com.hico.vish.dao.processor.UserDao;
import com.hico.vish.dao.table.UserEntity;

public class UserManager extends BaseManager<UserEntity>{

	public UserEntity getUserByEmail(String email) {
		return ((UserDao)dao).getUserByEmail(email);
	}

}
