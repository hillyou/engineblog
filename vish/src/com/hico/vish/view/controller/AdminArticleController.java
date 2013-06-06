package com.hico.vish.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Blog;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.util.KeyUtil;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = "/admin/article")
public class AdminArticleController extends BaseController{
	
	@RequestMapping("/articlelist")
	public String showArticleList(Model model) {
		UserEntity user=getCurrentUser(model);
		Blog blog=blogManager.fetchBlogArticle(user.getCurrentBlogKey());
		List<Article> articles=blog.getArticles();
		model.addAttribute("ARTICLES", articles);
		return "backend/article/articlelist";
	}
	
	@RequestMapping(value="/del/{articleId}")
	public String delArticle(@PathVariable String articleId,Model model) {
		Key articleKey=KeyUtil.stringToKey(articleId);
		Article persisted=articleManager.get(articleKey);
		UserEntity user=getCurrentUser(model);
		String url="";
		if(persisted.getAuthor().equals(user.getKey())) {
			articleManager.delete(persisted);
			url="redirect:/admin.html";
		}else {
			model.addAttribute(REQ_ATTR_MESSAGE, "You are not allowed to delete this article.");
		}
		return url;
	}
	
	@RequestMapping(value="/publish/{articleId}")
	public String publishArticle(@PathVariable Long articleId,Model model) {
		Article article=articleManager.get(articleId);
		UserEntity user=getCurrentUser(model);
		String url="";
		if(article.getAuthor().equals(user.getKey())) {
			articleManager.publish(article);
			url="redirect:/admin.html";
		}else {
			model.addAttribute(REQ_ATTR_MESSAGE, "You are not allowed to delete this article.");
		}
		return url;
	}
	
	@RequestMapping("/createarticle")
	public String createArticle(Model model,HttpServletRequest request) {
		return "backend/article/create";
	}
	
	@RequestMapping(value="/updatearticle/{articleId}", method=RequestMethod.GET)
	public String gotoUpdateArticle(@PathVariable String articleId,Model model,HttpServletRequest request) {
		UserEntity user=getCurrentUser(model);
		Key articleKey=KeyUtil.stringToKey(articleId);
		Article article=articleManager.get(articleKey);
		Blog current=blogManager.get(articleKey.getParent());
		user.setCurrentBlog(current);
		updateUserInSession(request, user);
		model.addAttribute("ARTICLE", article);
		return "backend/article/update";
	}
	
	@RequestMapping("/savearticle")
	public String saveArticle(Model model,Article article) {
		UserEntity owner=getCurrentUser(model);
		Blog persistent=owner.getCurrentBlog();
		article.setCreateDate(new Date());
		article.setPublishDate(new Date());
		article.setPublished(true);
		article.setAuthor(owner.getKey());
		article.setBlog(persistent);
		blogManager.addArticle(article);
		model.addAttribute("ARTICLE", article);
		model.addAttribute("MESSAGE", "Save successfully");
		return "backend/article/update";
	}
	
	@RequestMapping(value="/updatearticle", method=RequestMethod.POST)
	public String updateArticle(Model model,Article article) {
//		UserEntity owner=getCurrentUser(model);
		//!performance issue
//		Blog persistent=blogManager.fetchBlogArticle(owner.getCurrentBlogKey());
//		List<Article> articles=persistent.getArticles();
//		Article persisted=articles.get(articles.indexOf(article));
		Article persisted=articleManager.get(article.getKey());
		persisted.setCategory(article.getCategory());
		persisted.setTitle(article.getTitle());
		persisted.setContent(article.getContent());
		persisted.setKeywords(article.getKeywords());
		articleManager.update(persisted);
//		blogManager.update(persistent);
		model.addAttribute("ARTICLE", persisted);
		model.addAttribute("MESSAGE", "Update successfully");
		return "backend/article/update";
	}
	
	@RequestMapping("/ajaxsavearticle")
	public void saveArticleWithAjax(Model model,Article article,HttpServletResponse response) {
		String message="Save successfully";
		saveArticle(model,article);
		try {
			response.setContentType("text/plain");
			PrintWriter printWriter=response.getWriter();
			printWriter.print(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/ajaxupdatearticle")
	public void updateArticleWithAjax(Model model,Article article,HttpServletResponse response) {
		updateArticle(model,article);
		try {
			response.setContentType("text/plain");
			PrintWriter printWriter=response.getWriter();
			printWriter.print("Save successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
