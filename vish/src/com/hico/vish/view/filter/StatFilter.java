package com.hico.vish.view.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class StatFilter implements Filter {
//	private static Statistic stat;
//	private static Observer applicationStat;
//	private static Observer articleViewCountStat;
//	private static Observer logStat;
//	private static Observer userStat;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		stat.stat(request);
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
//		stat=new Statistic();
//		applicationStat=new ApplicationStat();
//		articleViewCountStat=new ArticleViewCountStat();
//		logStat=new LogStat();
//		userStat=new UserStat();
//		stat.addObserver(applicationStat);
//		stat.addObserver(articleViewCountStat);
//		stat.addObserver(logStat);
//		stat.addObserver(userStat);
	}
	
	public void destroy() {
	}

}
