package com.hico.vish.dao.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable(detachable="true")
@Inheritance(customStrategy = "complete-table")
public class Article extends StatusEntity{
	private static final long serialVersionUID = 3813488904204157021L;
	@Persistent
	private String title;
	@Persistent(defaultFetchGroup = "true",serialized = "true")
	private Text content;
	@Persistent
	private Date publishDate;
	@Persistent
	private Date modifyDate;
	@Persistent
	private boolean isPublished;
	@Persistent
	private boolean isOpenComment=true;
	@Persistent
	private String keywords;
	@Persistent
	private Key author;
	@Persistent
	private Blog blog;
	@NotPersistent
	private Long blogId;
	@Persistent(defaultFetchGroup = "true",mappedBy = "article")
	@Element(dependent = "true") 
	private List<Comment> comments;
	@Persistent
	private Key category;
	
	public Article() {
		
	}
	
	public Article(String title, String contents,Key author) {
		this.title = title;
		this.content = new Text(contents);
		this.author=author;
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
	
	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	/**
	 * @return the blog
	 */
	public Blog getBlog() {
		return blog;
	}

	/**
	 * @param blog the blog to set
	 */
	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	/**
	 * @return the blogId
	 */
	public Long getBlogId() {
		return blogId;
	}

	/**
	 * @param blogId the blogId to set
	 */
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}
	

//	/**
//	 * @return the categoryId
//	 */
//	public Key getCategoryId() {
//		return categoryId;
//	}
//
//	/**
//	 * @param categoryId the categoryId to set
//	 */
//	public void setCategoryId(Key categoryId) {
//		this.categoryId = categoryId;
//		setPersistCategory(categoryId);
//	}
	
//	private void setPersistCategory(Key newkey) {
//		if(newkey!=null && blog!=null && blog.getCategories()!=null) {
//			List<Category> categories=blog.getCategories();
//			for (Category category : categories) {
//				if(category.getKey().equals(newkey)) {
//					this.category=category;
//					break;
//				}
//			}
//		}
//	}
	
	
	public void addComment(Comment comment) {
		if(comments==null) {
			comments=new ArrayList<Comment>();
		}
		comments.add(comment);
	}

	/**
	 * @return the category
	 */
	public Key getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Key category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Article [title=" + title + ", content=" + content
				+ ", publishDate=" + publishDate + ", modifyDate=" + modifyDate
				+ ", isPublished=" + isPublished + ", isOpenComment="
				+ isOpenComment + ", keywords=" + keywords + ", author="
				+ author + ", blog=" + blog + ", blogId=" + blogId
				+ ", comments=" + comments + ", category=" + category
				+ ", isDeleted=" + isDeleted + ", isValid=" + isValid
				+ ", isLocked=" + isLocked + ", createDate=" + createDate + "]";
	}
	
	
	
 }
