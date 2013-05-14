package com.hico.vish.dao.table;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Author {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private String nickName;
	@Persistent
	private Email email;
	@Persistent
	private boolean isDelete;
	@Persistent
	private boolean isValid;
	@Persistent
	private boolean isLock;
	
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
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the email
	 */
	public Email getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(Email email) {
		this.email = email;
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
	 * @return the isLock
	 */
	public boolean isLock() {
		return isLock;
	}
	/**
	 * @param isLock the isLock to set
	 */
	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}
	
}
