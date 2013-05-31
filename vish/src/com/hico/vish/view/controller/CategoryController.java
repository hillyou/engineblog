package com.hico.vish.view.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.dao.table.Blog;
import com.hico.vish.dao.table.Category;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController extends BaseController{

	@RequestMapping(value = "/list")
	public String managerCategory(Model model){
		UserEntity owner=getCurrentUser(model);
		Blog blog=blogManager.get(owner.getCurrentBlog().getId());
		if(blog==null) {
			model.addAttribute(REQ_ATTR_MESSAGE, "Invalid blog");
		}else if(!blog.getBlogger().getKey().equals(owner.getKey())) {
			model.addAttribute(REQ_ATTR_MESSAGE, "Invalid user for this blog ");
		}else {
			List<Category> categories=blog.getCategories();
			model.addAttribute("CATEGORIES", categories);
		}
		return "backend/category/categorylist";
	}
	
	@RequestMapping(value = "/new")
	public String createCategory(){
		return "backend/category/createcategory";
	}
	
	@RequestMapping(value = "/del/{categoryId}")
	public String deleteCategory(@PathVariable Long categoryId,HttpServletRequest request){
		String isDel=request.getParameter("isdelrelatedarticles");
		boolean delArticle=false;
		if(isDel!=null && "TRUE".equals(isDel.toUpperCase())){
			delArticle=true;
		}
		categoryManager.deleteCategoryById(categoryId,delArticle);
		return "redirect:/admin/category/list.html";
	}
	
	@RequestMapping(value = "/create")
	public String createCategory(Category category,Model model){
		UserEntity owner=getCurrentUser(model);
		category.setOwner(owner.getKey());
		category.setCreateDate(new Date());
		boolean errprFlag=false;
		if(category.getParent()!=null){
			Category parent=categoryManager.get(category.getId());
			if(parent==null){
				model.addAttribute("MESSAGES", "Failed to save, Invalid parent category");
				errprFlag=true;
			}else{
				category.setParent(parent.getKey());
			}
		}
		
		Blog blog=blogManager.get(owner.getCurrentBlog().getId());
		List<Category> categories=blog.getCategories();
		if(categories==null) {
			categories=new ArrayList<Category>();
		}
		category.setBlog(blog);
		categories.add(category);
		blogManager.update(blog);
		if(!errprFlag){
			model.addAttribute("CATEGORIES", categories);
		}
		return "redirect:/admin/category/list.html";
	}
	
}
