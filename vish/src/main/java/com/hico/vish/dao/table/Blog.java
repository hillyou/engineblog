package com.hico.vish.dao.table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Unique;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.util.CategoryUtil;

@PersistenceCapable(detachable="true")
@Inheritance(customStrategy = "complete-table")
@FetchGroup(name = "articleGroup", members = { @Persistent(name = "articles") }) 
public class Blog extends StatusEntity{
	private static final long serialVersionUID = -5043813840114854869L;
	@Persistent
	@Unique
	private String name;
	@Persistent
	private String title;
	@Persistent(defaultFetchGroup = "false",mappedBy = "blog")
	private List<Article> articles;
	@Persistent(defaultFetchGroup = "true",mappedBy = "blog")
	@Element(dependent = "true")
	private List<Category> categories;
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
	
	public String getDisplayName(){
		return name==null?"":name.toLowerCase();
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

	@SuppressWarnings("unchecked")
	public List<Article> getUsableArticles() {
		return (List<Article>) getUsableEntities(articles);
	}
	
	
	public void addArticle(Article article) {
		if(articles==null) {
			articles=new ArrayList<Article>();
		}
		articles.add(article);
	}
	
	
	
	public void addCategory(Category category) {
		if(categories==null) {
			categories=new ArrayList<Category>();
		}
		categories.add(category);
	}
	
	public void removeCategory(Category category) {
		if(categories != null) {
			categories.remove(category);
		}
	}
	
	
	public synchronized List<Category> removeCategory(Key categoryKey) {
		List<Category> removed=new ArrayList<Category>();
		if(categories != null) {
			Iterator<Category> iterator=categories.iterator();
			while (iterator.hasNext()) {
				Category category =  iterator.next();
				if(category.getKey().equals(categoryKey)){
					removeSubCategory(categoryKey,removed);
//					category.setBlog(null);
					removed.add(category);
					categories.remove(category);
				}
			}
		}
		return removed;
	}
	
	private void removeSubCategory(Key key,List<Category> removed){
		Iterator<Category> iterator=categories.iterator();
		while (iterator.hasNext()) {
			Category category =  iterator.next();
			if(key.equals(category.getParentKey())){
				Key subKey=category.getKey();
				removeSubCategory(subKey,removed);
				removed.add(category);
				categories.remove(category);
			}
		}
	}
	
	public List<Article> removeArticle(List<Category> categories){
		List<Article> removed=new ArrayList<Article>();
		for(Category category:categories) {
			removeArticle(category.getKey(),removed);
		}
		return removed;
	}
	
	private void removeArticle(Key key,List<Article> removed){
		if(articles != null) {
			for (Article article:articles) {
				Key articleCategoryKey = article.getCategory();
				if(articleCategoryKey!=null && articleCategoryKey.equals(key)){
					article.setCategory(null);
					removed.add(article);
				}
			}
		}
	}
	
	/**
	 * @param articles the articles to set
	 */
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return categories;
	}
	
	public List<Category> getRootCategories() {
		List<Category> root=new ArrayList<Category>();
		if(categories!=null) {
			for(Category category:categories) {
				if(category.isRoot()) {
					root.add(category);
				}
			}
		}
		return root;
	}
	
	
	public Category getCategory(Key key){
		Category re=null;
		if(categories!=null){
			for(Category category:categories){
				if(category.getKey().equals(key)){
					re=category;
					break;
				}
			}
		}
		return re;
	}
	
	
	public Category getCategoryById(String categoryId){
		Category re=null;
		if(categories!=null){
			for(Category category:categories){
				if(category.getId().equals(Long.valueOf(categoryId))){
					re=category;
					break;
				}
			}
		}
		return re;
	}
	
	
	public List<Category> getSubCategory(Category parent){
		List<Category> allSubs=new ArrayList<Category>();
		getSubCategory(parent.getKey(),allSubs);
		return allSubs;
	} 
	
	public List<Category> getSubCategory(Key parentKey){
		List<Category> allSubs=new ArrayList<Category>();
		if(categories!=null){
			for(Category category:categories){
				if(category.getKey().equals(parentKey)){
					getSubCategory(parentKey,allSubs);
				}
			}
		}
		return allSubs;
	}
	
	
	
	private void getSubCategory(Key key,List<Category> sub){
		for(Category category:categories){
			if(category.getParentKey()!=null && category.getParentKey().equals(key)){
				sub.add(category);
				getSubCategory(category.getKey(),sub);
			}
		}
	}
	
	
	public List<Key> getSubCategoryKey(Key parentKey){
		List<Key> allSubs=new ArrayList<Key>();
		if(categories!=null){
			getSubCategoryKey(parentKey,allSubs);
		}
		return allSubs;
	}
	
	
	public List<Article> getArticlesUnderCategory(List<Category> categories){
		return CategoryUtil.getArticlesUnderCategory(articles, categories);
	}
	
	public List<Article> getUsableArticlesUnderCategory(List<Category> categories){
		return CategoryUtil.getArticlesUnderCategory(getUsableArticles(), categories);
	}
	
	private void getSubCategoryKey(Key key,List<Key> sub){
		for(Category category:categories){
			if(category.getParentKey()!=null && category.getParentKey().equals(key)){
				Key subKey = category.getKey();
				sub.add(subKey);
				getSubCategoryKey(subKey,sub);
			}
		}
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

	/**
	 * @return the draftArticles
	 */
	public List<Article> getDraftArticles() {
		List<Article> drafts=new ArrayList();
		if(articles!=null) {
			for(Article article:articles) {
				if(article.isDraft()) {
					drafts.add(article);
				}
			}
		}
		return drafts;
	}


}
