package com.hico.vish.dao.table;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable="true")
public class Category extends BaseEntity{
	private static final long serialVersionUID = -4805200387345970084L;
	@Persistent
	private String name;
	@Persistent
	private Key parentKey;
	@Persistent
	private Blog blog;
	@Persistent(defaultFetchGroup = "true")
	private Sequence position;
	
	
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
	
	public boolean isRoot() {
		return parentKey==null?true:false;
	}
	

	/**
	 * @return the parentKey
	 */
	public Key getParentKey() {
		return parentKey;
	}

	/**
	 * @param parentKey the parentKey to set
	 */
	public void setParentKey(Key parentKey) {
		this.parentKey = parentKey;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
