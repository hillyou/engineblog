package com.hico.vish.shared;

import javax.servlet.ServletRequest;

public interface IStatistic {

	public void stat(ServletRequest request);
	
	public IStatResult getStatResult();
	
}
