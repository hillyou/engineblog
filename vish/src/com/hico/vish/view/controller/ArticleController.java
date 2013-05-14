package com.hico.vish.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Comment;
import com.hico.vish.manager.ArticleManager;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

	@Autowired
	private ArticleManager articleManager;
	
	@RequestMapping("/createarticle")
	public String createArticle() {
		return "backend/article/create";
	}
	
	@RequestMapping("/savearticle")
	public String saveArticle(HttpServletRequest request) {
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		Article article=new Article(title,content);
		articleManager.save(article);
		request.setAttribute("ARTICLE", article);
		return "backend/article/update";
	}
	
	@RequestMapping("/updatearticle")
	public String updateArticle(HttpServletRequest request) {
		String articleid=request.getParameter("articleid");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		Article article=articleManager.getById(Long.valueOf(articleid).longValue());
		article.setTitle(title);
		article.setContent(content);
		articleManager.update(article);
		request.setAttribute("ARTICLE", article);
		return "backend/article/update";
	}
	
	@RequestMapping("/ajaxsavearticle")
	public void saveArticleWithAjax(HttpServletRequest request,HttpServletResponse response) {
		saveArticle(request);
		try {
			response.setContentType("text/plain");
			PrintWriter printWriter=response.getWriter();
			printWriter.print("Save successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/ajaxupdatearticle")
	public void updateArticleWithAjax(HttpServletRequest request,HttpServletResponse response) {
		updateArticle(request);
		try {
			response.setContentType("text/plain");
			PrintWriter printWriter=response.getWriter();
			printWriter.print("Save successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/articlelist")
	public String showArticleList(HttpServletRequest request) {
		List<Article> articles=articleManager.getArticleList();
		request.setAttribute("ARTICLES", articles);
		return "frontend/articlelist";
	}
	
	
	@RequestMapping("/showarticle/{articleid}")
	public String showArticle(@PathVariable String articleid,HttpServletRequest request) {
		Article article=articleManager.getById(Long.valueOf(articleid).longValue());
		request.setAttribute("ARTICLE", article);
		return "frontend/showarticle";
	}
	
	@RequestMapping("/addcomment")
	public String addComment(HttpServletRequest request) {
		String articleid=request.getParameter("articleid");
		String content=request.getParameter("comment");
		Article article=articleManager.getById(Long.valueOf(articleid).longValue());
		List comments=article.getComments();
		if(comments==null) {
			comments=new ArrayList();
		}
		Comment comment=new Comment(content);
		comment.setArticle(article);
		comment.setCommentDate(new Date());
		comments.add(comment);
		articleManager.save(article);
		request.setAttribute("ARTICLE", article);
		return "frontend/showarticle";
	}
	
	@RequestMapping("/ajaxaddcomment")
	public void addCommentWithAjax(HttpServletRequest request,HttpServletResponse response) {
		addComment(request);
		try {
			response.setContentType("text/plain");
			PrintWriter printWriter=response.getWriter();
			printWriter.print("Save successfully");
			printWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
