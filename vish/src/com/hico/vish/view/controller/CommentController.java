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
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Comment;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = "/user/comment")
public class CommentController extends BaseController{

	
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
	
}
