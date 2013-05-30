package com.hico.vish.dao.table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.hico.vish.util.EmailUtil;

@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class UserEntity extends StatusEntity{
	private static final long serialVersionUID = 1L;
	@Persistent
	private String userId;
	@Persistent
	private String userName;
	@Persistent
	private String nickName;
	@Persistent
	private String email;
	@Persistent
	private Date lastLogin;
	@Persistent(defaultFetchGroup = "true",mappedBy = "blogger")
	@Element(dependent = "true") 
	private List<Blog> blogs;
	@NotPersistent
	private transient Blog currentBlog;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMessEmail() {
		return EmailUtil.messEmail(email);
	}
	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}
	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	/**
	 * @return the isBloger
	 */
	public boolean isBloger() {
		return (blogs==null || blogs.isEmpty())?false:true;
	}
	
	/**
	 * @return the blogs
	 */
	public List<Blog> getBlogs() {
		return blogs;
	}
	/**
	 * @param blogs the blogs to set
	 */
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
	/**
	 * @return the currentBlog
	 */
	public Blog getCurrentBlog() {
		if(blogs!=null && blogs.size()==1) {
			return blogs.get(0);
		}
		return currentBlog;
	}
	/**
	 * @param currentBlog the currentBlog to set
	 */
	public synchronized void setCurrentBlog(Blog currentBlog) {
		this.currentBlog = currentBlog;
	}
	
	public void addBlog(Blog blog) {
		if(blogs==null) {
			blogs=new ArrayList<Blog>();
		}
		blogs.add(blog);
	}
	
}
