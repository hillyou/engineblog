package com.hico.vish.manager;

import com.hico.vish.dao.processor.UserDao;
import com.hico.vish.dao.table.UserEntity;

public class UserManager {
	private UserDao userDao;

	public UserEntity getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}
	
	public UserEntity get(Object id) {
		return userDao.get(id);
	}
	
	public void saveUser(UserEntity user) {
		userDao.save(user);
	}
	
	public void updateUser(UserEntity user) {
		userDao.update(user);
	}
	/**
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
