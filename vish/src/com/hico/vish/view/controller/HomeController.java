package com.hico.vish.view.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.dao.table.Article;
import com.hico.vish.manager.ArticleManager;
import com.hico.vish.manager.UserManager;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController{
	@Autowired
	private ArticleManager articleManager;
	@Autowired
	private UserManager userManager;

	@RequestMapping
	public String goToHome(HttpServletRequest request,HttpServletResponse response){
		loadArticleList(request);
		return "frontend/home";
	}
	
	
	private void loadArticleList(HttpServletRequest request){
		List<Article> articles=articleManager.getArticleList();
		Collections.sort(articles);
		request.setAttribute("ARTICLES", articles);
	}

	
	
}
