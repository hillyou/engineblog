package com.hico.vish.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Category;
import com.hico.vish.dao.table.Comment;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = "/admin/article")
public class AdminArticleController extends BaseController{

	
	@RequestMapping(value="/del/{articleId}")
	public String delArticle(@PathVariable Long articleId,Model model) {
		Article persisted=articleManager.getById(articleId);
		UserEntity user=getCurrentUser(model);
		if(persisted.getAuthor().equals(user.getKey())) {
			articleManager.delete(persisted);
		}else {
			model.addAttribute(REQ_ATTR_MESSAGE, "You are not allowed to delete this article.");
		}
		return "backend/home";
	}
	
	@RequestMapping("/createarticle")
	public String createArticle(Model model,HttpServletRequest request) {
		loadCategory(model);
		return "backend/article/create";
	}
	
	@RequestMapping(value="/updatearticle/{articleId}", method=RequestMethod.GET)
	public String gotoUpdateArticle(@PathVariable String articleId,Model model,HttpServletRequest request) {
		Assert.hasText(articleId);
		Article article=articleManager.getById(Long.valueOf(articleId).longValue());
		request.setAttribute("ARTICLE", article);
		loadCategory(model);
		return "backend/article/update";
	}
	
	@RequestMapping("/savearticle")
	public String saveArticle(Model model,Article article) {
		UserEntity user=getCurrentUser(model);
		article.setAuthor(user.getKey());
		articleManager.save(article);
		model.addAttribute("ARTICLE", article);
		model.addAttribute("MESSAGE", "Save successfully");
		loadCategory(model);
		return "backend/article/update";
	}
	
	@RequestMapping(value="/updatearticle", method=RequestMethod.POST)
	public String updateArticle(Model model,Article article) {
		Article persisted=articleManager.getById(article.getId());
		persisted.setCategory(article.getCategory());
		persisted.setTitle(article.getTitle());
		persisted.setContent(article.getContent());
		persisted.setKeywords(article.getKeywords());
		articleManager.update(persisted);
		model.addAttribute("ARTICLE", persisted);
		model.addAttribute("MESSAGE", "Update successfully");
		loadCategory(model);
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
	
	@RequestMapping("/addcomment")
	public String addComment(Model model,HttpServletRequest request) {
		String articleid=request.getParameter("articleid");
		String content=request.getParameter("comment");
		Article article=articleManager.getById(Long.valueOf(articleid).longValue());
		List<Comment> comments=article.getComments();
		if(comments==null) {
			comments=new ArrayList<Comment>();
		}
		Comment comment=new Comment(content);
		comment.setCommentEmail(getCurrentUser(model).getEmail());
		comment.setCommentDate(new Date());
		comment.setArticle(article);
		comments.add(comment);
		articleManager.save(article);
		request.setAttribute("ARTICLE", article);
		return "frontend/showarticle";
	}
	
	@RequestMapping("/ajaxaddcomment")
	public void addCommentWithAjax(Model model,HttpServletRequest request,HttpServletResponse response) {
		addComment(model,request);
		try {
			response.setContentType("text/plain");
			PrintWriter printWriter=response.getWriter();
			printWriter.print("Save successfully");
			printWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadCategory(Model model) {
		List<Category> categories=categoryManager.getUserCategory(getCurrentUser(model));
		model.addAttribute("CATEGORIES", categories);
	}
	
	
}
