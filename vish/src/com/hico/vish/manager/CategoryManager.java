package com.hico.vish.manager;

import java.util.List;

import com.hico.vish.dao.processor.CategoryDao;
import com.hico.vish.dao.table.Category;
import com.hico.vish.dao.table.UserEntity;

public class CategoryManager {

	private CategoryDao categoryDao;
	
	public Category getById(Long id) {
		return categoryDao.getById(id);
	}
	
	public List<Category> getUserCategory(UserEntity owner){
		return categoryDao.getUserCategory(owner);
	}
	
	public void saveCategory(Category category) {
		categoryDao.saveCategory(category);
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	
}
