package com.hico.vish.view.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.dao.table.Article;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController{

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
		Article article=articleManager.get(Long.valueOf(articleid).longValue());
		request.setAttribute("ARTICLE", article);
		return "frontend/showarticle";
	}
	
}
