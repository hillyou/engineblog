package com.hico.vish.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.appengine.api.datastore.Key;
import com.hico.vish.dao.table.Article;
import com.hico.vish.dao.table.Blog;
import com.hico.vish.dao.table.Category;
import com.hico.vish.view.BaseController;

@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController{

	@RequestMapping("/{blogname}")
	public String blogHome(@PathVariable String blogname,Model model,HttpServletResponse response){
		model.addAttribute("blogname", blogname);
		loadBlogAndArticles(model);
		return "frontend/pages/blog";
	}
	
	@RequestMapping("/{blogname}/{categoryname}")
	public String blogCategory(@PathVariable String blogname,@PathVariable String categoryname,Model model){
		model.addAttribute("blogname", blogname);
		model.addAttribute("categoryname", categoryname);
		loadBlogAndArticles(model);
		loadCategoryArticles(model);
		return "frontend/pages/blog";
	}
	
	
	private void loadBlogAndArticles(Model model){
		String blogname=(String) model.asMap().get("blogname");
		Blog blog=blogManager.fetchBlogArticle(blogname);
		List<Article> articles=null;
		if(blog!=null && blog.getArticles()!=null){
			articles=blog.getArticles();
		}
		model.addAttribute("BLOG", blog);
		model.addAttribute("ARTICLES", articles);
	}

	@SuppressWarnings("unchecked")
	private void loadCategoryArticles(Model model){
		Blog blog= (Blog) model.asMap().get("BLOG");
		Object articlesO= model.asMap().get("ARTICLES");
		String blogname=(String) model.asMap().get("categoryname");
		Category currentCategory=blog.getCategoryByName(blogname);
		List<Key> allSubs=blog.getSubCategoryKey(currentCategory.getKey());
		model.addAttribute("CURRENT_CATEGORY", currentCategory);
		model.addAttribute("CHILD_CATEGORY", blog.getSubCategory(currentCategory));
		if(articlesO!=null){
			List<Article> categoryArticles=new ArrayList<Article>();
			List<Article> articles=(List<Article>)articlesO;
			for (Article article : articles) {
				if(allSubs.contains(article.getCategory())){
					categoryArticles.add(article);
				}
			}
			model.addAttribute("ARTICLES", categoryArticles);
		}
	}
	
}
