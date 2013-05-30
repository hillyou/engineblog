package com.hico.vish.dao.table;

import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class Blog extends StatusEntity{
	private static final long serialVersionUID = -5043813840114854869L;
	@Persistent
	private String name;
	@Persistent
	private String title;
	@Persistent(mappedBy = "blog")
	@Element(dependent = "true") 
	private List<Article> articles;
	@Persistent(mappedBy = "blog")
	@Element(dependent = "true") 
	private List<Category> categories;
	@NotPersistent
	private Key owner;
	@Persistent
	private UserEntity blogger;
	
	
	public Blog() {
	}
	
	public Blog(String name) {
		this.name=name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the articles
	 */
	public List<Article> getArticles() {
		return articles;
	}

	/**
	 * @param articles the articles to set
	 */
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Key getOwner() {
		return owner;
	}

	public void setOwner(Key owner) {
		this.owner = owner;
	}

	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	/**
	 * @return the blogger
	 */
	public UserEntity getBlogger() {
		return blogger;
	}

	/**
	 * @param blogger the blogger to set
	 */
	public void setBlogger(UserEntity blogger) {
		this.blogger = blogger;
	}

	@Override
	public String toString() {
		return "Blog [name=" + name + ", title=" + title + ", articles="
				+ articles + ", categories=" + categories + ", owner=" + owner
				+ ", blogger=" + blogger + ", isDeleted=" + isDeleted
				+ ", isValid=" + isValid + ", isLocked=" + isLocked
				+ ", createDate=" + createDate + "]";
	}
	
	
	
}
