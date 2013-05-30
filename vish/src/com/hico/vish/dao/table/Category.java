package com.hico.vish.dao.table;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Category extends BaseEntity{
	private static final long serialVersionUID = -4805200387345970084L;
	@Persistent
	private String name;
	@Persistent
	private Key parent;
	@Persistent
	private Blog blog;
//	@Persistent(defaultFetchGroup = "true")
//	@Element(dependent = "true") 
	@NotPersistent
	private List<Category> subCategory;
	@Persistent(defaultFetchGroup = "true")
	private Sequence position;
	@Persistent
	private Key owner;
	
	public Category() {
	}
	
	public Category(String name) {
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
	 * @return the parentId
	 */
	public Key getParent() {
		return parent;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParent(Key parent) {
		this.parent = parent;
	}

	/**
	 * @return the subCategory
	 */
	public List<Category> getSubCategory() {
		return subCategory;
	}


	public void addSubCategory(Category sub) {
		if(this.subCategory ==null) {
			this.subCategory=new ArrayList<Category>();
		}
		this.subCategory.add(sub);
	}

	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(List<Category> subCategory) {
		this.subCategory = subCategory;
	}

	/**
	 * @return the position
	 */
	public Sequence getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Sequence position) {
		this.position = position;
	}

	public Key getOwner() {
		return owner;
	}

	public void setOwner(Key owner) {
		this.owner = owner;
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
	
}
