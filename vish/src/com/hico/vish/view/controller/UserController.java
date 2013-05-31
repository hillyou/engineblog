package com.hico.vish.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Blog;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = {"/admin","/admin/user"})
public class UserController extends BaseController{
	
	@RequestMapping
	public String gotoUserHome(Model model) {
		return "backend/home";
	}
	
	@RequestMapping(value="/openblog",method=RequestMethod.GET)
	public String gotoOpenBlog(Model model,HttpServletRequest request,HttpServletResponse response) {
		return "backend/user/newblog";
	}
	
	@RequestMapping(value="/openblog",method=RequestMethod.POST)
	public String openBlog(Blog blog,Model model,HttpServletRequest request) {
		UserEntity persisted=getCurrentUser(model);
		blog.setBlogger(persisted);
		blog.setCreateDate(new Date());
		persisted.addBlog(blog);
		try{
			userManager.updateUser(persisted);
			persisted.setCurrentBlog(blog);
			updateUserInSession(request,persisted);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "redirect:/admin.html";
	}
	
	@RequestMapping(value="/ajaxopenblog",method=RequestMethod.POST)
	public void openBlogWithAjax(Blog blog,Model model,HttpServletRequest request,HttpServletResponse response) {
		openBlog(blog,model,request);
		try {
			response.setContentType("text/html");
			PrintWriter printWriter=response.getWriter();
			printWriter.println("Blog opened successfully<br>");
			printWriter.println("<a href=\""+request.getContextPath()+"/admin/article/createarticle.html\">go write blog</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
