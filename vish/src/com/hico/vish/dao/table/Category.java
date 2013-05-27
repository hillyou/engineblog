package com.hico.vish.dao.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Category implements Comparable<Category>,Serializable{
	private static final long serialVersionUID = -4805200387345970084L;
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private String name;
	@Persistent
	private Date createDate;
	@Persistent
	private Category parent;
	@Persistent(defaultFetchGroup = "true")
	@Element(dependent = "true") 
	private List<Category> subCategory;
	@Persistent(defaultFetchGroup = "true")
	private Sequence position;
	
	public Category(String name) {
		this.name=name;
	}
	
	public Category(String name,Category parent) {
		this.name=name;
		this.parent=parent;
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
	 * @return the parent
	 */
	public Category getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Category parent) {
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
			this.subCategory=new ArrayList();
		}
		this.subCategory.add(sub);
	}

	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(List<Category> subCategory) {
		this.subCategory = subCategory;
	}



	@Override
	public int compareTo(Category category) {
		return 0;
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

}
