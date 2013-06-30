package com.hico.vish.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Blog;
import com.hico.vish.dao.table.Category;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController{

	@RequestMapping("/{blogname}")
	public String blogHome(@PathVariable String blogname,Model model,HttpServletResponse response){
		model.addAttribute("blogname", blogname);
		loadBlog(model);
		loadAllCategoryAndArticles(model);
		return "frontend/pages/blog";
	}
	
	@RequestMapping("/{blogname}/{categoryid}")
	public String blogCategory(@PathVariable String blogname,@PathVariable String categoryid,Model model){
		model.addAttribute("blogname", blogname);
		model.addAttribute("categoryid", categoryid);
		loadBlog(model);
		loadCategoryAndArticles(model);
		return "frontend/pages/blog";
	}
	
	
	private void loadBlog(Model model){
		String blogname=(String) model.asMap().get("blogname");
		Blog blog=blogManager.fetchBlogArticle(blogname);
		model.addAttribute("BLOG", blog);
	}
	
	private void loadAllCategoryAndArticles(Model model){
		Blog blog= (Blog) model.asMap().get("BLOG");
		model.addAttribute("ARTICLES", blog.getUsableArticles());
	}

	private void loadCategoryAndArticles(Model model){
		Blog blog= (Blog) model.asMap().get("BLOG");
		String categoryid=(String) model.asMap().get("categoryid");
		Category currentCategory=blog.getCategoryById(categoryid);
		List<Category> childCategories=blog.getSubCategory(currentCategory);
		model.addAttribute("CURRENT_CATEGORY", currentCategory);
		model.addAttribute("CHILD_CATEGORY", childCategories);
		List<Category> all=new ArrayList<Category>();
		all.add(currentCategory);
		all.addAll(childCategories);
		List<Article> categoryArticles=blog.getUsableArticlesUnderCategory(all);
		model.addAttribute("ARTICLES", categoryArticles);
	}
	
}
