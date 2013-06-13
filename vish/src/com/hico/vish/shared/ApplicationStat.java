package com.hico.vish.shared;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicLong;

public class ApplicationStat implements IStatistic,Observer{
	private IStatResult statResult=StatResult.getInstance();
	@Override
	public void update(Observable o, Object arg) {
		stat((IUserRequest) arg);
	}

	@Override
	public void stat(IUserRequest request) {
		Object currentCount=statResult.get(ResultType.APP);
		AtomicLong nextCount=null;
		if(currentCount==null) {
			nextCount=new AtomicLong(0); 
		}else {
			nextCount=new AtomicLong((Long)currentCount); 
		}
		statResult.set(ResultType.APP,nextCount.addAndGet(1));
		
	}

	@Override
	public Object getStatResult() {
		return statResult.get(ResultType.APP);
	}



}
