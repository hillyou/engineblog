package com.hico.vish.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hico.vish.manager.ArticleManager;
import com.hico.vish.manager.CategoryManager;
import com.hico.vish.manager.UserManager;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController{

	@Autowired
	private ArticleManager articleManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private CategoryManager categoryManager;
	
	private final static String REQ_ATTR_MESSAGE="MESSAGE";
	
	@RequestMapping("/user/createarticle")
	public String createArticle(Model model,HttpServletRequest request) {
		loadCategory(model);
		return "backend/article/create";
	}
	
	@RequestMapping(value="/user/updatearticle/{articleId}", method=RequestMethod.GET)
	public String gotoUpdateArticle(@PathVariable String articleId,Model model,HttpServletRequest request) {
		Assert.hasText(articleId);
		Article article=articleManager.getById(Long.valueOf(articleId).longValue());
		request.setAttribute("ARTICLE", article);
		loadCategory(model);
		return "backend/article/update";
	}
	
	@RequestMapping("/user/savearticle")
	public String saveArticle(Model model,HttpServletRequest request) {
		UserEntity user=getCurrentUser(model);
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String categoryId=request.getParameter("category");
		Article article=new Article(title,content,user.getKey());
		if(categoryId !=null && !"".equals(categoryId)) {
			Category category=categoryManager.getById(Long.valueOf(categoryId));
			if(category!=null) {
				article.setCategory(category.getKey());
			}
		}
		articleManager.save(article);
		request.setAttribute("ARTICLE", article);
		request.setAttribute("MESSAGE", "Save successfully");
		loadCategory(model);
		return "backend/article/update";
	}
	
	@RequestMapping(value="/user/updatearticle", method=RequestMethod.POST)
	public String updateArticle(Model model,HttpServletRequest request) {
		String articleid=request.getParameter("articleid");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String categoryId=request.getParameter("category");
		Article article=articleManager.getById(Long.valueOf(articleid).longValue());
		if(categoryId !=null && !"".equals(categoryId) && 
				(article.getCategory()==null || !categoryId.equals(String.valueOf(article.getCategory().getId())))) {
			Category category=categoryManager.getById(Long.valueOf(categoryId));
			if(category!=null) {
				article.setCategory(category.getKey());
			}
		}
		article.setTitle(title);
		article.setContent(content);
		articleManager.update(article);
		request.setAttribute("ARTICLE", article);
		request.setAttribute("MESSAGE", "Update successfully");
		loadCategory(model);
		return "backend/article/update";
	}
	
	@RequestMapping("/user/ajaxsavearticle")
	public void saveArticleWithAjax(Model model,HttpServletRequest request,HttpServletResponse response) {
		String message="Save successfully";
		saveArticle(model,request);
		try {
			response.setContentType("text/plain");
			PrintWriter printWriter=response.getWriter();
			printWriter.print(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/user/ajaxupdatearticle")
	public void updateArticleWithAjax(Model model,HttpServletRequest request,HttpServletResponse response) {
		updateArticle(model,request);
		try {
			response.setContentType("text/plain");
			PrintWriter printWriter=response.getWriter();
			printWriter.print("Save successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/articlelist")
	public String showArticleList(Model model,HttpServletRequest request) {
		List<Article> articles=articleManager.getArticleList();
		Collections.sort(articles);
		request.setAttribute("ARTICLES", articles);
		return "frontend/articlelist";
	}
	
	
	@RequestMapping("/showarticle/{articleid}")
	public String showArticle(@PathVariable String articleid,HttpServletRequest request) {
		Assert.hasText(articleid);
		Article article=articleManager.getById(Long.valueOf(articleid).longValue());
		Assert.notNull(article);
		request.setAttribute("ARTICLE", article);
		return "frontend/showarticle";
	}
	
	@RequestMapping("/user/addcomment")
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
	
	@RequestMapping("/user/ajaxaddcomment")
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
