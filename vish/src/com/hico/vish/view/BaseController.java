package com.hico.vish.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {

	private static int counter=0;
	@ModelAttribute("CONTER")
	public String beforeController() {
		String counters=String.valueOf(++counter);
		System.out.println(counters);
		return counters;
	}
	
	@ModelAttribute("REQ_URL")
	public String beforeController(HttpServletRequest request) {
		String url=request.getRequestURL().toString();
		System.out.println("requesting URL: "+url);
		return url;
	}
}
