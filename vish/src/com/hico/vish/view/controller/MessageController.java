package com.hico.vish.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class MessageController {

	@RequestMapping
	public String goToHome(){
		return "frontend/message";
	}
}
