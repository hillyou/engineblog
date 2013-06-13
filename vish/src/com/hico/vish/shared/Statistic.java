package com.hico.vish.shared;

import java.util.Observable;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class Statistic extends Observable {

	public void stat(ServletRequest req) {
		HttpServletRequest request = (HttpServletRequest) req;
		IUserRequest userRequest=new UserRequest(request);
		super.setChanged();
		super.notifyObservers(userRequest);
	}

}
