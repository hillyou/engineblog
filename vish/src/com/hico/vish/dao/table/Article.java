package com.hico.vish.dao.table;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable = "true")
@FetchGroup(name = "fullArticle", members = { @Persistent(name = "comments") }) 
public class Article {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private String title;
	@Persistent
	private String content;
	@Persistent
	private Date publishDate;
	@Persistent
	private Date createDate;
	@Persistent
	private Date modifyDate;
	@Persistent
	private boolean isDelete=false;
	@Persistent
	private boolean isValid=true;
	@Persistent
	private boolean isPublished;
	@Persistent
	private boolean isOpenComment=true;
	
	@Persistent(mappedBy = "article")
	@Element(dependent = "true")
	private List<Comment> comments;
	
	public Article() {
		
	}
	
	public Article(String title, String content) {
		this.title = title;
		this.content = content;
	}
	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		if(key!=null) {
			return key.getId();
		}
		return null;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		key=KeyFactory.createKey(getClass().getSimpleName(), id);
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the publishDate
	 */
	public Date getPublishDate() {
		return publishDate;
	}
	/**
	 * @param publishDate the publishDate to set
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
	
	/**
	 * @return the isOpenComment
	 */
	public boolean isOpenComment() {
		return isOpenComment;
	}

	/**
	 * @param isOpenComment the isOpenComment to set
	 */
	public void setOpenComment(boolean isOpenComment) {
		this.isOpenComment = isOpenComment;
	}

	@Override
	public String toString() {
		return "Article [id=" + key + ", title=" + title + ", content="
				+ content + ", publishDate=" + publishDate + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", isDelete="
				+ isDelete + ", isValid=" + isValid + ", isPublished="
				+ isPublished + "]";
	}
	
 }
