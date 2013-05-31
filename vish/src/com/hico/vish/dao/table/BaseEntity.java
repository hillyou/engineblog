package com.hico.vish.dao.table;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable="true")
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class BaseEntity implements Comparable<BaseEntity>,Serializable{
	private static final long serialVersionUID = 2457263669418667692L;
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	protected Key key;
	@Persistent
	protected Date createDate;

	
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
	protected void setId(Long id) {
		System.out.println(getClass().getSimpleName());
		key=KeyFactory.createKey(getClass().getSimpleName(), id);
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
	
	@Override
	public int compareTo(BaseEntity entity) {
		if(createDate==null || entity.getCreateDate()==null) {
			return -1;
		}
		return entity.getCreateDate().compareTo(createDate);
	}

	@Override
	public boolean equals(Object entity) {
		return this.key.equals(((BaseEntity)entity).getKey());
	}
	
	
}
