package com.hico.vish.shared;

import javax.servlet.ServletRequest;

public interface IStatRequest {
	
	public void stat(UserRequest request);
	
	public Object getStatResult();
}
