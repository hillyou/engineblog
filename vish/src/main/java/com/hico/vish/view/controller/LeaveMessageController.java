package com.hico.vish.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.view.BaseController;

@Controller
@RequestMapping("/leavemessage")
public class LeaveMessageController extends BaseController{

	@RequestMapping
	public String goToPage(HttpServletRequest request){
		return "frontend/pages/leavemessage";
	}
	
}
