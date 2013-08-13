package com.hico.vish.shared;

import java.util.Observable;
import java.util.Observer;

public class LogStat implements IStatistic,Observer{
	private IStatResult statResult=StatResult.getInstance();
	@Override
	public void update(Observable o, Object arg) {
		stat((IUserRequest) arg);
	}

	@Override
	public void stat(IUserRequest request) {
		System.out.println("--------LogStat");
	}

	@Override
	public Object getStatResult() {
		return statResult.get(ResultType.LOG);
	}

}
