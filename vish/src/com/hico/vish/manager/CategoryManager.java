package com.hico.vish.manager;

import com.hico.vish.dao.processor.CategoryDao;
import com.hico.vish.dao.table.Category;

public class CategoryManager {

	private CategoryDao categoryDao;
	
	public Category get(Long id) {
		return categoryDao.get(id);
	}
	
	
	public void saveCategory(Category category) {
		categoryDao.save(category);
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public void deleteCategoryById(Long id,boolean delRelatedArticle){
		categoryDao.deleteCategoryById(id,delRelatedArticle);
	}
	
}
