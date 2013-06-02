package com.hico.vish.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Comment;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.util.KeyUtil;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = "/user/comment")
public class CommentController extends BaseController{

	
	@RequestMapping("/addcomment")
	public String addComment(Model model,HttpServletRequest request) {
		UserEntity owner=getCurrentUser(model);
		String content=request.getParameter("comment");
		String articleid=request.getParameter("articleid");
		Key articleKey=KeyUtil.stringToKey(articleid);
		Article article=articleManager.get(articleKey);
		Comment comment=new Comment(content);
		comment.setCommentEmail(owner.getEmail());
		comment.setCreateDate(new Date());
		comment.setArticle(article);
		article.addComment(comment);
		articleManager.update(article);
		model.addAttribute("ARTICLE", article);
		return "redirect:/article/showarticle/{articleid}.html";
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
