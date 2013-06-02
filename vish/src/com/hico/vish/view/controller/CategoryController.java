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
		categoryManager.deleteCategoryById(categoryKey,delArticle);
		UserEntity owner=getCurrentUser(model);
		Blog blog=owner.getCurrentBlog();
		blog.removeCategory(categoryKey);
		updateUserInSession(request, owner);
		return "redirect:/admin/category/list.html";
	}
	
	@RequestMapping(value = "/create")
	public synchronized String createCategory(Category category,Model model,HttpServletRequest request){
		UserEntity owner=getCurrentUser(model);
		Blog blog=owner.getCurrentBlog();
		category.setCreateDate(new Date());
		category.setBlog(blog);
		blog.addCategory(category);
		blogManager.update(blog);
		updateUserInSession(request, owner);
		return "redirect:/admin/category/list.html";
	}
	
}
