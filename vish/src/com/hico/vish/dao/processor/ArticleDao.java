package com.hico.vish.dao.processor;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Comment;

public class ArticleDao {

	private PersistenceManagerFactory persistenceManagerFactory;
	
	public void saveOrUpdate(Article article) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try {
			transaction.begin();
			persistenceManager.makePersistent(article);
			transaction.commit();
		}catch(Exception ex) {
			ex.printStackTrace();
			if(transaction.isActive()) {
				transaction.rollback();
			}
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
		}
	}

	public Article get(Long id) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		persistenceManager.setDetachAllOnCommit(true); 
		persistenceManager.getFetchPlan().addGroup("fullArticle");
		Article article=persistenceManager.getObjectById(Article.class,id);
		return article;
	}
	
	
	public List<Article> getArticleList(){
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Query query=persistenceManager.newQuery(Article.class);
		List<Article> articles=(List<Article>) query.execute();
		for(Article article:articles ) {
			System.out.println(article);
		}
		return articles;
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
