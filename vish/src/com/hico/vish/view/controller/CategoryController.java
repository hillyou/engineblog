package com.hico.vish.view.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Blog;
import com.hico.vish.dao.table.Category;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.util.KeyUtil;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController extends BaseController{

	@RequestMapping(value = "/list")
	public String managerCategory(Model model){
		UserEntity owner=getCurrentUser(model);
		Blog blog=owner.getCurrentBlog();
		List<Category> categories=blog.getCategories();
		model.addAttribute("CATEGORIES", categories);
		return "backend/category/categorylist";
	}
	
	@RequestMapping(value = "/new")
	public String createCategory(){
		return "backend/category/createcategory";
	}
	
	@RequestMapping(value = "/del/{categoryId}")
	public synchronized String deleteCategory(@PathVariable String categoryId,Model model,HttpServletRequest request){
		String isDel=request.getParameter("isdelrelatedarticles");
		boolean delArticle=false;
		if(isDel!=null && "TRUE".equals(isDel.toUpperCase())){
			delArticle=true;
		}
		Key categoryKey=KeyUtil.stringToKey(categoryId);
//		categoryManager.delete(category);
		UserEntity owner=getCurrentUser(model);
//		Blog blog=blogManager.fetchBlogArticle(owner.getCurrentBlog().getKey());
		Blog blog=blogManager.fetchBlogArticle(owner.getCurrentBlogKey());
		List<Category> removedCategories=blog.removeCategory(categoryKey);
		List<Article> removedArticles=blog.removeArticle(removedCategories);
//		if(!removedArticles.isEmpty()) {
//			deleteArticles(removedArticles);
//		}
		blogManager.update(blog);
		owner.setCurrentBlog(blog);
		updateUserInSession(request, owner);
		return "redirect:/admin/category/list.html";
	}
	
//	private void deleteArticles(List<Article> articles){
//		for (Article article : articles) {
//			articleManager.deleteById(article.getKey());
//		}
//	}
	
//	private void deleteCategories(List<Category> categories){
//		for (Category category : categories) {
//			Category del=categoryManager.get(category.getKey());
//			categoryManager.delete(del);
//		}
//	}
	
//	private void deleteCategories(List<Key> keys){
//		for (Key key : keys) {
//			categoryManager.deleteById(key);
//		}
//	}
	
	@RequestMapping(value = "/create")
	public String createCategory(Category category,Model model,HttpServletRequest request){
		UserEntity owner=getCurrentUser(model);
		Blog blog=blogManager.fetchBlogArticle(owner.getCurrentBlogKey());
		category.setCreateDate(new Date());
		category.setBlog(blog);
		blog.addCategory(category);
		blogManager.update(blog);
		updateUserInSession(request, owner);
		return "redirect:/admin/category/list.html";
	}
	
}
