package com.hico.vish.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {

	@RequestMapping
	public String goToHome(HttpServletRequest request){
		return "frontend/message";
	}
}
