package com.hico.vish.view.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.view.BaseController;

@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController{

	@RequestMapping("/{blogname}")
	public String blogHome(@PathVariable String blogname,Model model,HttpServletResponse response){
		loadArticleList(model);
		return "frontend/blog/home";
	}
	
	
	private void loadArticleList(Model model){
		String blogname=(String) model.asMap().get("blogname");
//		List<Article> articles=articleManager.getArticleList(blogname);
//		Collections.sort(articles);
//		model.addAttribute("ARTICLES", articles);
	}

	
	
}
