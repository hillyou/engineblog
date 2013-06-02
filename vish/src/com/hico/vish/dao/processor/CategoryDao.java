package com.hico.vish.dao.processor;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Category;
import com.hico.vish.dao.table.UserEntity;


public class CategoryDao extends BaseDao<Category>{

	public Category get(Object id) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Category category=persistenceManager.getObjectById(Category.class, id);
			return category;
		}finally{
			persistenceManager.close();
		}
	}
	
	public synchronized void deleteCategoryById(Object id,boolean delRelatedArticle){
		List<Category> allCategories=retriveSubCategoryById(id);
		deleteArticleFromCategory(allCategories,delRelatedArticle);
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			for (Category subcategory : allCategories) {
				Category toBeDel=persistenceManager.getObjectById(Category.class,subcategory.getKey());
				persistenceManager.deletePersistent(toBeDel);
			}
		}finally{
			persistenceManager.close();
		}
	}
	
	public List<Category> retriveSubCategoryById(Object id){
		List<Category> list=new ArrayList<Category>();
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Category parent=persistenceManager.getObjectById(Category.class,id);
			if(parent!=null){
				retriveSubCategories(persistenceManager,parent.getKey(),list);
				list.add(parent);
			}
		}finally{
			persistenceManager.close();
		}
		return list;
	}
	
	
	public void deleteArticleFromCategory(List<Category> categories,boolean isDel){
		for (Category category : categories) {
			deleteArticleFromCategory(category.getKey(),isDel);
		}
	}
	
	
	public void deleteArticleFromCategory(Key category,boolean isDel){
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Query query=persistenceManager.newQuery(Article.class);
			query.setFilter("category == paramCategory");
			query.declareParameters(Key.class.getName()+" paramCategory");
			List<Article> articles=(List<Article>) query.execute(category);
			if(articles!=null && !articles.isEmpty()){
				if(isDel){
					persistenceManager.deletePersistentAll(articles);
				}else{
					for(Article article:articles ) {
						article.setCategory(null);
					}
				}
			}
		}finally{
			persistenceManager.close();
		}
	}
	
	private void retriveSubCategories(PersistenceManager  persistenceManager,Key parentId,List<Category> list){
			Query query=persistenceManager.newQuery(Category.class);
			query.setFilter("parent == paramParent");
			query.declareParameters(Key.class.getName()+" paramParent");
			Object result=query.execute(parentId);
			 if(result!=null){
				 List<Category> categories=(List<Category>)result;
				 for (Category category : categories) {
					 retriveSubCategories(persistenceManager,category.getKey(),list);
					 list.add(category);
				}
			 }
			
	}
	
	public List<Category> getUserCategory(UserEntity owner){
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Query query=persistenceManager.newQuery(Category.class);
			query.setFilter("owner == paramOwner");
			query.declareParameters(Key.class.getName()+" paramOwner");
			Object result=query.execute(owner.getKey());
			return result!=null?(List<Category>)result:null;
		}finally{
			persistenceManager.close();
		}
	}
	
}
