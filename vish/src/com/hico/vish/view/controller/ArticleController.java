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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hico.vish.dao.table.AppUser;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Comment;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.manager.ArticleManager;
import com.hico.vish.manager.UserManager;
import com.hico.vish.util.UserUtil;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController{

	@Autowired
	private ArticleManager articleManager;
	
	@Autowired
	private UserManager userManager;
	
	private final static String REQ_ATTR_MESSAGE="MESSAGE";
	
	@RequestMapping("/user/createarticle")
	public String createArticle(HttpServletRequest request) {
		if(!isValidBlogger()) {
			request.setAttribute(REQ_ATTR_MESSAGE, "User forbidden</br>Currently you don't have a blog, please click <a href=\""+request.getContextPath()+"/user/setting/openblog.html\">here</a> to open");
			return "frontend/message";
		}
		return "backend/article/create";
	}
	
	@RequestMapping(value="/user/updatearticle/{articleId}", method=RequestMethod.GET)
	public String gotoUpdateArticle(@PathVariable String articleId,HttpServletRequest request) {
		if(!isValidBlogger()) {
			request.setAttribute(REQ_ATTR_MESSAGE, "User forbidden");
			return "frontend/message";
		}
		Assert.hasText(articleId);
		Article article=articleManager.getById(Long.valueOf(articleId).longValue());
		request.setAttribute("ARTICLE", article);
		return "backend/article/update";
	}
	
	@RequestMapping("/user/savearticle")
	public String saveArticle(HttpServletRequest request) {
		UserEntity user=getCurrentUser();
		if(!isValidBlogger(user)) {
			request.setAttribute(REQ_ATTR_MESSAGE, "User forbidden");
			return "frontend/message";
		}
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		Article article=new Article(title,content,user.getKey());
		articleManager.save(article);
		request.setAttribute("ARTICLE", article);
		request.setAttribute("MESSAGE", "Save successfully");
		return "backend/article/update";
	}
	
	@RequestMapping(value="/user/updatearticle", method=RequestMethod.POST)
	public String updateArticle(HttpServletRequest request) {
		if(!isValidBlogger()) {
			request.setAttribute(REQ_ATTR_MESSAGE, "User forbidden");
			return "frontend/message";
		}
		String articleid=request.getParameter("articleid");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		Article article=articleManager.getById(Long.valueOf(articleid).longValue());
		article.setTitle(title);
		article.setContent(content);
		articleManager.update(article);
		request.setAttribute("ARTICLE", article);
		request.setAttribute("MESSAGE", "Update successfully");
		return "backend/article/update";
	}
	
	@RequestMapping("/user/ajaxsavearticle")
	public void saveArticleWithAjax(HttpServletRequest request,HttpServletResponse response) {
		String message="Save successfully";
		if(!isValidBlogger()) {
			message="User forbidden";
		}else {
			saveArticle(request);
		}
		try {
			response.setContentType("text/plain");
			PrintWriter printWriter=response.getWriter();
			printWriter.print(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/user/ajaxupdatearticle")
	public void updateArticleWithAjax(HttpServletRequest request,HttpServletResponse response) {
		if(!isValidBlogger()) {
			request.setAttribute(REQ_ATTR_MESSAGE, "User forbidden");
		}
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
		UserEntity user=getCurrentUser();
		request.setAttribute("AUTHOR", user);
		return "frontend/articlelist";
	}
	
	
	@RequestMapping("/showarticle/{articleid}")
	public String showArticle(@PathVariable String articleid,HttpServletRequest request) {
		Assert.hasText(articleid);
		Article article=articleManager.getById(Long.valueOf(articleid).longValue());
		Assert.notNull(article);
		UserEntity user=userManager.getUserById(article.getAuthor().getId());
		request.setAttribute("ARTICLE", article);
		request.setAttribute("AUTHOR", user);
		return "frontend/showarticle";
	}
	
	@RequestMapping("/user/addcomment")
	public String addComment(HttpServletRequest request) {
		String articleid=request.getParameter("articleid");
		String content=request.getParameter("comment");
		Article article=articleManager.getById(Long.valueOf(articleid).longValue());
		List<Comment> comments=article.getComments();
		if(comments==null) {
			comments=new ArrayList<Comment>();
		}
		Comment comment=new Comment(content);
		comment.setCommentEmail(getCurrentUser().getEmail());
		comment.setCommentDate(new Date());
		comment.setArticle(article);
		comments.add(comment);
		articleManager.save(article);
		request.setAttribute("ARTICLE", article);
		return "frontend/showarticle";
	}
	
	@RequestMapping("/user/ajaxaddcomment")
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
	
	private UserEntity getCurrentUser() {
		AppUser user=UserUtil.getAppUser();
		UserEntity loginUser=userManager.getUserByEmail(user.getEmail());
		if(loginUser==null) {
			loginUser=new UserEntity();
			loginUser.setUserName(user.getEmail());
			loginUser.setEmail(user.getEmail());
			loginUser.setNickName(user.getNickName());
			loginUser.setUserId(user.getUserId());
			userManager.saveOrUpdateUser(loginUser);
		}
		return loginUser;
	}
	
	
	private boolean isValidBlogger() {
		UserEntity user=getCurrentUser();
		return isValidBlogger(user);
	}
	
	private boolean isValidBlogger(UserEntity user) {
		return user.isBloger() && !user.isDeleted() && !user.isLocked() && user.isValid();
	}
}
