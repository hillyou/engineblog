package com.hico.vish.shared;

public interface IStatResult {

	public Object get(ResultType type);
	
	public Object get(ResultType type,Object flag);
	
	public void set(ResultType type,Object resultValue);
}
