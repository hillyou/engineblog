package com.hico.vish.dao.table;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class Article implements Comparable<Article>,Serializable{
	private static final long serialVersionUID = 3813488904204157021L;
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private String title;
	@Persistent
	private Text content;
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
	@Persistent
	private Key author;
	
	@Persistent(defaultFetchGroup = "true",mappedBy = "article")
	@Element(dependent = "true") 
	private List<Comment> comments;
	
	@Persistent(defaultFetchGroup = "true",mappedBy = "article")
	@Element(dependent = "true") 
	private Category category;
	
	public Article() {
		
	}
	
	public Article(String title, String contents,Key author) {
		this.title = title;
		this.content = new Text(contents);
		this.author=author;
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
	public Text getContent() {
		return content;
	}
	
	public String getContentValue() {
		return content==null?"":content.getValue();
	}
	
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = new Text(content);
	}
	
	public void setContent(Text content) {
		this.content = content;
	}
	
	public String getSnipptContent(){
		if(this.content!=null){
			String contentvalue = content.getValue();
			String noHTMLContent = contentvalue.replaceAll("\\<.*?\\>", "");
			if(noHTMLContent.length()>70){
				return noHTMLContent.substring(0, 70)+"...";
			}
			return noHTMLContent;
		}
		return "";
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
		if(comments!=null){
			Collections.sort(comments);
		}
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

	/**
	 * @return the author
	 */
	public Key getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(Key author) {
		this.author = author;
	}
	

	@Override
	public String toString() {
		return "Article [id=" + key + ", title=" + title + ", content="
				+ content + ", publishDate=" + publishDate + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", isDelete="
				+ isDelete + ", isValid=" + isValid + ", isPublished="
				+ isPublished + "]";
	}

	@Override
	public int compareTo(Article article) {
		if(createDate==null || article.createDate==null) {
			return -1;
		}
		return article.createDate.compareTo(createDate);
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
 }
