package com.hico.vish.dao.processor;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Comment;
import com.hico.vish.dao.table.UserEntity;

public class ArticleDao {

	private PersistenceManagerFactory persistenceManagerFactory;
	
	public void save(Article article) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try {
			transaction.begin();
			article.setCreateDate(new Date());
			persistenceManager.makePersistent(article);
			transaction.commit();
		}catch(Exception ex) {
			if(transaction.isActive()) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}finally{
			persistenceManager.close();
		}
	}
	
	public void update(Article article) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try {
			transaction.begin();
			article.setModifyDate(new Date());
			persistenceManager.makePersistent(article);
			transaction.commit();
		}catch(Exception ex) {
			if(transaction.isActive()) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}finally{
			persistenceManager.close();
		}
	}
	
	public void saveComment(Comment comment) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try {
			transaction.begin();
			persistenceManager.makePersistent(comment);
			System.out.println(comment);
			transaction.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
			if(transaction.isActive()) {
				transaction.rollback();
			}
		}finally{
			persistenceManager.close();
		}
	}

	public Article get(Long id) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Article article=persistenceManager.getObjectById(Article.class,id);
			System.out.println(article);
			return article;
		}finally{
			persistenceManager.close();
		}
	}
	
	public List<Article> getArticleList(){
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Query query=persistenceManager.newQuery(Article.class);
			query.setFilter("isDelete == false && isValid == true && isPublished ==true");
			List<Article> articles=(List<Article>) query.execute();
			for(Article article:articles ) {
				System.out.println(article);
			}
			return articles;
		}finally{
			persistenceManager.close();
		}
	}
	
	
	public List<Article> getArticleList(UserEntity user){
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		try{
			Query query=persistenceManager.newQuery(Article.class);
			query.setFilter("author == paramAuthor && isDelete == false && isValid == true");
			query.declareParameters(Key.class.getName()+" paramAuthor");
			List<Article> articles=(List<Article>) query.execute(user.getKey());
			for(Article article:articles ) {
				System.out.println(article);
			}
			return articles;
		}finally{
			persistenceManager.close();
		}
	}
	
	/**
	 * @return the persistenceManagerFactory
	 */
	public PersistenceManagerFactory getPersistenceManagerFactory() {
		return persistenceManagerFactory;
	}

	/**
	 * @param persistenceManagerFactory the persistenceManagerFactory to set
	 */
	public void setPersistenceManagerFactory(
			PersistenceManagerFactory persistenceManagerFactory) {
		this.persistenceManagerFactory = persistenceManagerFactory;
	}
	
	
}
