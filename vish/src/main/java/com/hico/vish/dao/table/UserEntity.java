package com.hico.vish.dao.table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Unique;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.util.EmailUtil;
import com.hico.vish.util.UserUtil;

@PersistenceCapable(detachable="true")
@Inheritance(customStrategy = "complete-table")
public class UserEntity extends StatusEntity{
	private static final long serialVersionUID = 1L;
	@Persistent
	@Unique
	private String userId;
	@Persistent
	private String userName;
	@Persistent
	private String nickName;
	@Persistent
	@Unique
	private String email;
	@Persistent
	private Date lastLogin;
	@Persistent(defaultFetchGroup = "true",mappedBy = "blogger")
	@Element(dependent = "true")
	private List<Blog> blogs;
	@NotPersistent
	private Blog currentBlog;
	@NotPersistent
	private transient boolean isSetCurrentBlog=false;

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
	
	public boolean isAdmin(){
		return UserUtil.isAdmin();
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
	
	public Key getCurrentBlogKey(){
		return currentBlog.getKey();
	}
	
	/**
	 * @return the isBloger
	 */
	public boolean isHasBlog() {
		return (blogs==null || blogs.isEmpty())?false:true;
	}
	
	public boolean isValidBlogger() {
		return !isDeleted && !isLocked && isValid;
	}
	
	private static final String USABLE="USABLE";
	private static final String UNUSABLE="UNUSABLE";
	private static final String DELETED="DELETED";
	private static final String INVALID="INVALID";
	private static final String LOCKED="LOCKED";
	
	private List<Blog> getKindsBlogs(String kind){
		List<Blog> kinds=new ArrayList<Blog>();
		if(isHasBlog()) {
			for(Blog blog:blogs) {
				if(USABLE.equals(kind) && blog.isUsable()) {
					kinds.add(blog);
				}else if(UNUSABLE.equals(kind) && !blog.isUsable()) {
					kinds.add(blog);
				}else if(DELETED.equals(kind) && blog.isDeleted()) {
					kinds.add(blog);
				}else if(INVALID.equals(kind) && !blog.isValid()) {
					kinds.add(blog);
				}else if(LOCKED.equals(kind) && blog.isLocked()) {
					kinds.add(blog);
				}
			}
		}
		return kinds;
	}
	
	
	public List<Blog> getUsableBlogs(){
		return getKindsBlogs(USABLE);
	}
	
	public int getUsableBlogsSize(){
		return getKindsBlogs(USABLE).size();
	}
	
	public List<Blog> getUnusableBlogs(){
		return getKindsBlogs(UNUSABLE);
	}
	
	public List<Blog> getDeletedBlogs(){
		return getKindsBlogs(DELETED);
	}
	
	public List<Blog> getInvalidBlogs(){
		return getKindsBlogs(INVALID);
	}
	
	public List<Blog> getLockedBlogs(){
		return getKindsBlogs(LOCKED);
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
		if(isSetCurrentBlog) {
			return this.currentBlog;
		}
		synchronized(this) {
			if(isValidBlogger()) {
				List<Blog> blogs=getUsableBlogs();
				if(blogs.size()>=1) {
					this.isSetCurrentBlog=true;
					currentBlog=blogs.get(0);
				}
			}
		}
		return currentBlog;
	}
	
	
	/**
	 * @param currentBlog the currentBlog to set
	 */
	public synchronized void setCurrentBlog(Blog currentBlog) {
		List<Blog> usableBlogs=getUsableBlogs();
		for (Blog blog : usableBlogs) {
			if(blog.getKey().equals(currentBlog.getKey())){
				blogs.remove(blog);
				break;
			}
		}
		blogs.add(currentBlog);
		this.currentBlog=currentBlog;
		this.isSetCurrentBlog=true;
	}
	
	/**
	 * @param currentBlog the currentBlog to set
	 */
	public synchronized void setCurrentBlog(Long blogId) {
		List<Blog> blogs=getUsableBlogs();
		for (Blog blog : blogs) {
			if(blog.getId().equals(blogId)){
				this.currentBlog=blog;
				this.isSetCurrentBlog=true;
				break;
			}
		}
	}
	
	public void addBlog(Blog blog) {
		if(blogs==null) {
			blogs=new ArrayList<Blog>();
		}
		blogs.add(blog);
	}
	
}
