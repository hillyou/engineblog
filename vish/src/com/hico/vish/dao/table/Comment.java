package com.hico.vish.dao.table;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable = "true")
public class Comment {

//	@PrimaryKey
//    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
//	private Key key;
	@Persistent
	private Article article;
	@Persistent
	private String content;
	@Persistent
	private Date commentDate;
	@Persistent
	private Key commentBy;
	@Persistent
	private boolean isDelete=false;
	@Persistent
	private boolean isValid=true;
	@Persistent
	private boolean isPublished=true;
	
	public Comment() {}
	
	public Comment(String content) {
		this.content=content;
	}
//	/**
//	 * @return the key
//	 */
//	public Key getKey() {
//		return key;
//	}
//	/**
//	 * @param key the key to set
//	 */
//	public void setKey(Key key) {
//		this.key = key;
//	}
	
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
	 * @return the commentDate
	 */
	public Date getCommentDate() {
		return commentDate;
	}
	/**
	 * @param commentDate the commentDate to set
	 */
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
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
	 * @return the isDelete
	 */
	public boolean isDelete() {
		return isDelete;
	}
	/**
	 * @param isDelete the isDelete to set
	 */
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * @return the isValid
	 */
	public boolean isValid() {
		return isValid;
	}
	/**
	 * @param isValid the isValid to set
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	/**
	 * @return the isPublished
	 */
	public boolean isPublished() {
		return isPublished;
	}
	/**
	 * @param isPublished the isPublished to set
	 */
	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}
	
	
}
