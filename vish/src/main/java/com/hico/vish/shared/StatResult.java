package com.hico.vish.shared;

import java.util.HashMap;
import java.util.Map;

public class StatResult implements IStatResult{

	private final static StatResult INSTANCE=new StatResult();
	private static Map result=new HashMap();
	
	public static StatResult getInstance() {
		return INSTANCE;
	}
	
	private StatResult() {
		
	}
	
	@Override
	public Object get(ResultType type) {
		return result.get(type);
	}

	@Override
	public synchronized void set(ResultType type, Object resultValue) {
		result.put(type, resultValue);
	}

	@Override
	public Object get(ResultType type, Object flag) {
		Object resultObject=get(type);
		if(resultObject!=null && resultObject instanceof Map) {
			Map resultMap=(Map)resultObject;
			return resultMap.get(flag);
		}
		return null;
	}

	
	
	
}
