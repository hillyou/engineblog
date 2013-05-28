package com.hico.vish.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hico.vish.dao.table.Category;
import com.hico.vish.manager.CategoryManager;
import com.hico.vish.view.BaseController;
public class BaseArticleController extends BaseController{
	@Autowired
	private CategoryManager categoryManager;
	/**
	 * @param model
	 */
	@ModelAttribute("CATEGORIES")
	protected List<Category> loadCategory(Model model) {
		return categoryManager.getUserCategory(getCurrentUser(model));
	}
	
}
