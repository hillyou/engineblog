package com.hico.vish.view.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Blog;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController{

	@RequestMapping("/{blogname}")
	public String blogHome(@PathVariable String blogname,Model model,HttpServletResponse response){
		model.addAttribute("blogname", blogname);
		loadBlogAndArticles(model);
		return "frontend/pages/blog";
	}
	
	
	private void loadBlogAndArticles(Model model){
		String blogname=(String) model.asMap().get("blogname");
		Blog blog=blogManager.fetchBlogArticle(blogname);
		List<Article> articles=null;
		if(blog!=null && blog.getArticles()!=null){
			articles=blog.getArticles();
			Collections.sort(articles);
		}
		model.addAttribute("BLOG", blog);
		model.addAttribute("ARTICLES", articles);
	}

	
	
}
