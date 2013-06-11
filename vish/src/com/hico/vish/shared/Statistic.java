package com.hico.vish.shared;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class Statistic implements IStatistic{

	@Override
	public void stat(ServletRequest req) {
		HttpServletRequest request=(HttpServletRequest)req;
	}

	@Override
	public IStatResult getStatResult() {
		
		return null;
	}

}
