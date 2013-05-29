package com.hico.vish.manager;

import java.util.Date;
import java.util.List;

import com.hico.vish.dao.processor.ArticleDao;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Comment;
import com.hico.vish.dao.table.UserEntity;

public class ArticleManager {
	private ArticleDao articleDao;
	
	public void saveComment(Comment comment) {
		articleDao.saveComment(comment);
	}
	
	public Article getById(Long id) {
		return articleDao.get(id);
	}
	
	public List<Article> getArticleList(){
		return articleDao.getArticleList();
	}
	
	public List<Article> getArticleList(UserEntity user){
		return articleDao.getArticleList(user);
	}
	
	public void save(Article article) {
		setDefault(article);
		articleDao.save(article);
	}

	public void update(Article article) {
		articleDao.update(article);
	}
	
	public void saveAndPublish(Article article) {
		article.setPublishDate(new Date());
		article.setPublished(true);
		articleDao.save(article);
	}
	
	public void updateAndPublish(Article article) {
		article.setPublishDate(new Date());
		article.setPublished(true);
		articleDao.update(article);
	}
	
	public void publish(Article article) {
		article.setPublishDate(new Date());
		article.setPublished(true);
		articleDao.update(article);
	}
	
	public void withdrawPublish(Article article) {
		article.setPublishDate(new Date());
		article.setPublished(false);
		articleDao.update(article);
	}


	public void valid(Article article) {
		article.setValid(true);
		articleDao.update(article);
	}
	
	public void inValid(Article article) {
		article.setValid(false);
		articleDao.update(article);
	}

	public void delete(Article article) {
		article.setDelete(true);
		articleDao.update(article);
	}
	
	public void restore(Article article) {
		article.setDelete(false);
		articleDao.update(article);
	}
	
	private void setDefault(Article article) {
		article.setPublishDate(new Date());
		article.setPublished(true);
		article.setValid(true);
		article.setDelete(false);
	}
	
	/**
	 * @return the articleDao
	 */
	public ArticleDao getArticleDao() {
		return articleDao;
	}

	/**
	 * @param articleDao
	 *            the articleDao to set
	 */
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

}
