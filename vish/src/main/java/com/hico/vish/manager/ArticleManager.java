package com.hico.vish.manager;

import java.util.Date;
import java.util.List;

import com.hico.vish.dao.processor.ArticleDao;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Blog;
import com.hico.vish.dao.table.Comment;
import com.hico.vish.dao.table.UserEntity;

public class ArticleManager extends BaseManager<Article>{
	
	public void addComment(Comment comment) {
		((ArticleDao)dao).addComment(comment);
	}
	
	public List<Article> getArticleList(){
		return ((ArticleDao)dao).getArticleList();
	}
	
	public List<Article> getArticleList(UserEntity user){
		return ((ArticleDao)dao).getArticleList(user);
	}
	
	public List<Article> getBlogArticleList(Blog blog){
		return ((ArticleDao)dao).getBlogArticleList(blog);
	}
	
	public void save(Article article) {
		setDefault(article);
		super.save(article);
	}

	public void saveAndPublish(Article article) {
		article.setPublishDate(new Date());
		article.setPublished(true);
		super.save(article);
	}
	
	public void updateAndPublish(Article article) {
		article.setPublishDate(new Date());
		article.setPublished(true);
		super.update(article);
	}
	
	public void publish(Article article) {
		article.setPublishDate(new Date());
		article.setPublished(true);
		super.update(article);
	}
	
	public void withdrawPublish(Article article) {
		article.setPublishDate(new Date());
		article.setPublished(false);
		super.update(article);
	}


	public void valid(Article article) {
		article.setValid(true);
		((ArticleDao)dao).update(article);
	}
	
	public void inValid(Article article) {
		article.setValid(false);
		((ArticleDao)dao).update(article);
	}

	public void delete(Article article) {
		article.setDeleted(true);
		((ArticleDao)dao).update(article);
	}
	
	public void restore(Article article) {
		article.setDeleted(false);
		((ArticleDao)dao).update(article);
	}
	
	private void setDefault(Article article) {
		article.setPublishDate(new Date());
		article.setPublished(true);
		article.setValid(true);
		article.setDeleted(false);
	}

}
