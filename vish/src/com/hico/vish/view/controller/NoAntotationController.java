package com.hico.vish.view.controller;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hico.vish.manager.UserManager;

@RequestMapping(value = "/test")
public class NoAntotationController {

	private UserManager userManager;
	
	@RequestMapping("/controller")
	public @ResponseBody String test() {
		Assert.notNull(userManager);
		Assert.notNull(userManager.getUserDao());
		Assert.notNull(userManager.getUserDao().getPersistenceManagerFactory());
		String facName=userManager.getUserDao().getPersistenceManagerFactory().getName();
		System.out.println("facName: "+facName);
		return "</br>factory name: "+facName;
	}

	/**
	 * @return the userManager
	 */
	public UserManager getUserManager() {
		return userManager;
	}

	/**
	 * @param userManager the userManager to set
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	
	
}
