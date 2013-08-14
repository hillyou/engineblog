package com.hico.vish.view.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Blog;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.util.KeyUtil;
import com.hico.vish.view.BaseController;
import com.hico.vish.view.response.ArticleJsonResponse;

@Controller
@RequestMapping(value = "/admin/article")
public class AdminArticleController extends BaseController{
	private final static Logger LOGGER=Logger.getLogger(AdminArticleController.class);
	@RequestMapping("/articlelist")
	public String showArticleList(Model model) {
		UserEntity user=getCurrentUser(model);
		Blog blog=blogManager.fetchBlogArticle(user.getCurrentBlogKey());
		List<Article> articles=blog.getUsableArticles();
		model.addAttribute("ARTICLES", articles);
		return "backend/article/articlelist";
	}
	
	@RequestMapping("/draftarticle")
	public String showDraftArticles(Model model) {
		UserEntity user=getCurrentUser(model);
		Blog blog=blogManager.fetchBlogArticle(user.getCurrentBlogKey());
		List<Article> articles=blog.getDraftArticles();
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
	public String saveArticle(Model model,Article article,HttpServletRequest request) {
		UserEntity owner=getCurrentUser(model);
		Blog currentBlog=owner.getCurrentBlog();
		article.setPublishDate(new Date());
		article.setPublished(true);
		article.setAuthor(owner.getKey());
		article.setBlog(currentBlog);
		Blog persistentBlog=blogManager.addArticle(article);
		UserEntity persistentUser=userManager.get(owner.getKey());
		persistentUser.setCurrentBlog(persistentBlog);
		updateUserInSession(request, persistentUser);
		model.addAttribute("ARTICLE", article);
		model.addAttribute("MESSAGE", "Save successfully");
		return "backend/article/update";
	}
	
	@RequestMapping(value="/updatearticle", method=RequestMethod.POST)
	public String updateArticle(Model model,Article article) {
		Article persisted=articleManager.get(article.getKey());
		persisted.setCategory(article.getCategory());
		persisted.setTitle(article.getTitle());
		persisted.setContent(article.getContent());
		persisted.setKeywords(article.getKeywords());
		articleManager.update(persisted);
		model.addAttribute("ARTICLE", persisted);
		model.addAttribute("MESSAGE", "Update successfully");
		return "backend/article/update";
	}
	
	@RequestMapping("/ajaxsavearticle")
	@ResponseBody
	public ArticleJsonResponse saveArticleWithAjax(Model model,Article article,HttpServletRequest request) {
		saveArticle(model,article,request);
		ArticleJsonResponse jsonresp=new ArticleJsonResponse();
		String message="Save successfully";
		jsonresp.setArticleId(article.getDisplayKey());
		jsonresp.setMessage(message);
		return jsonresp;
	}
	
	@RequestMapping(value="/ajaxsavedraft",produces="application/json;charset=UTF-8")
	@ResponseBody
	public ArticleJsonResponse saveDraftWithAjax(Model model,Article article,HttpServletRequest request) {
		LOGGER.info("ajax saving draft article");
		article.setDraft(true);
		return saveArticleWithAjax(model,article,request);
	}
	
	@RequestMapping("/ajaxupdatearticle")
	@ResponseBody
	public ArticleJsonResponse updateArticleWithAjax(Model model,Article article) {
		updateArticle(model,article);
		ArticleJsonResponse jsonresp=new ArticleJsonResponse();
		String message="Save successfully";
		jsonresp.setArticleId(article.getDisplayKey());
		jsonresp.setMessage(message);
		return jsonresp;
	}
	
}
