package com.hico.vish.view.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.dao.table.Comment;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = "/user/comment")
public class CommentController extends BaseController{

	
	@RequestMapping("/addcomment")
	public String addComment(Comment comment,Model model) {
		UserEntity owner=getCurrentUser(model);
		comment.setCommentEmail(owner.getEmail());
		comment.setCommentBy(owner.getKey());
		comment.setCreateDate(new Date());
		articleManager.addComment(comment);
		return "redirect:/article/showarticle/{articleid}.html";
	}
	
	@RequestMapping("/ajaxaddcomment")
	public void addCommentWithAjax(Comment comment,Model model,HttpServletResponse response) {
		addComment(comment,model);
		String message="Save successfully";
		if(comment.getKey()==null){
			message="Invalid comment on this article!";
		}
		try {
			response.setContentType("text/plain");
			PrintWriter printWriter=response.getWriter();
			printWriter.print(message);
			printWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
