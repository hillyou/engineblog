package com.hico.vish.view.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.dao.table.Category;
import com.hico.vish.dao.table.UserEntity;
import com.hico.vish.manager.CategoryManager;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController extends BaseController{

	@Autowired
	private CategoryManager categoryManager;
	
	@RequestMapping(value = "/list")
	public String managerCategory(Model model){
		UserEntity owner=getCurrentUser(model);
		List<Category> category=categoryManager.getUserCategory(owner);
		model.addAttribute("CATEGORIES", category);
		return "backend/category/categorylist";
	}
	
	@RequestMapping(value = "/newcategory")
	public String createCategory(){
		return "backend/category/createcategory";
	}
	
	@RequestMapping(value = "/create")
	public String createCategory(HttpServletRequest request,Model model){
		UserEntity owner=getCurrentUser(model);
		String categoryName=request.getParameter("categoryname");
		String parentCategory=request.getParameter("parentcategory");
		Category category=new Category(categoryName);
		category.setOwner(owner.getKey());
		category.setCreateDate(new Date());
		boolean errprFlag=false;
		if(parentCategory!=null && !"".equals(parentCategory.trim())){
			Category parent=categoryManager.getById(Long.valueOf(parentCategory));
			if(parent==null){
				request.setAttribute("MESSAGES", "Failed to save, Invalid parent category");
				errprFlag=true;
			}else{
				category.setParent(parent.getKey());
			}
		}
		if(!errprFlag){
			categoryManager.saveCategory(category);
			List<Category> categories=categoryManager.getUserCategory(owner);
			request.setAttribute("CATEGORIES", categories);
		}
		return "redirect:/admin/category/list.html";
	}
	
}
