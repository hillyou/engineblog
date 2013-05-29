package com.hico.vish.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = {"/admin","/admin/user"})
public class UserController extends BaseController{
	
	@RequestMapping
	public String gotoUserHome(HttpServletRequest request) {
		loadArticleList(request);
		return "backend/home";
	}
	
	@RequestMapping("/setting/openblog")
	public void updateUser(Model model,HttpServletRequest request,HttpServletResponse response) {
		UserEntity user=getCurrentUser(model);
		user.setBloger(true);
		userManager.saveOrUpdateUser(user);
		try {
			response.setContentType("text/html");
			PrintWriter printWriter=response.getWriter();
			printWriter.println("Blog opened successfully<br>");
			printWriter.println("<a href=\""+request.getContextPath()+"/admin/article/createarticle.html\">go write blog</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadArticleList(HttpServletRequest request) {
		List<Article> articles=articleManager.getArticleList();
		Collections.sort(articles);
		request.setAttribute("ARTICLES", articles);
	}
	
}
