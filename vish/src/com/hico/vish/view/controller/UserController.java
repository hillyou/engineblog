package com.hico.vish.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.manager.ArticleManager;
import com.hico.vish.manager.UserManager;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{

	@Autowired
	private ArticleManager articleManager;
	
	@Autowired
	private UserManager userManager;
	
	@RequestMapping
	public String gotoUserHome(HttpServletRequest request) {
		loadArticleList(request);
		return "backend/home";
	}
	
	
	@RequestMapping("/setting/openblog")
	public void updateUser(HttpServletRequest request,HttpServletResponse response) {
		UserEntity user=getCurrentUser(request);
		user.setBloger(true);
		userManager.saveOrUpdateUser(user);
		try {
			response.setContentType("text/html");
			PrintWriter printWriter=response.getWriter();
			printWriter.println("Blog opened successfully<br>");
			printWriter.println("<a href=\""+request.getContextPath()+"/article/user/createarticle.html\">go write blog</a>");
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
