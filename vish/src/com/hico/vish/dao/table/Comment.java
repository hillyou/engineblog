package com.hico.vish.dao.table;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class Comment extends StatusEntity{
	private static final long serialVersionUID = -2968102350245609016L;
	@Persistent
	private Article article;
	@Persistent
	private String content;
	@Persistent
	private String commentEmail;
	@Persistent
	private Key commentBy;
	@NotPersistent
	private UserEntity reviewer;
	
	public Comment() {}
	
	public Comment(String content) {
		this.content=content;
	}
	
	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the commentBy
	 */
	public Key getCommentBy() {
		return commentBy;
	}
	/**
	 * @param commentBy the commentBy to set
	 */
	public void setCommentBy(Key commentBy) {
		this.commentBy = commentBy;
	}

	/**
	 * @param isValid the isValid to set
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public UserEntity getReviewer() {
		return reviewer;
	}

	public void setReviewer(UserEntity reviewer) {
		this.reviewer = reviewer;
	}

	public String getCommentEmail() {
		return this.commentEmail;
	}

	public void setCommentEmail(String commentEmail) {
		this.commentEmail = commentEmail;
	}
	
}
