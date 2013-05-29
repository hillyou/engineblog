package com.hico.vish.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hico.vish.dao.table.Article;
import com.hico.vish.manager.ArticleManager;
import com.hico.vish.manager.CategoryManager;
import com.hico.vish.view.BaseController;
public class BaseArticleController extends BaseController{
	@Autowired
	private CategoryManager categoryManager;
	
	@Autowired
	private ArticleManager articleManager;
	/**
	 * @param model
	 */
	@ModelAttribute("ARTICLE")
	protected Article loadArticleBeforeUpdate(HttpServletRequest request) {
		String id=request.getParameter("keysid");
		return articleManager.getById(Long.valueOf(id));
	}
	
	
}
