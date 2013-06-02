package com.hico.vish.manager;

import com.hico.vish.dao.processor.CategoryDao;
import com.hico.vish.dao.table.Category;

public class CategoryManager {

	private CategoryDao categoryDao;
	
	public Category get(Object id) {
		return categoryDao.get(id);
	}
	
	
	public void save(Category category) {
		categoryDao.save(category);
	}
	
	public void update(Category category) {
		categoryDao.update(category);
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public void deleteCategoryById(Object id,boolean delRelatedArticle){
		categoryDao.deleteCategoryById(id,delRelatedArticle);
	}
	
}
