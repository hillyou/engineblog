package com.hico.vish.manager;

import com.hico.vish.dao.processor.ArticleDao;
import com.hico.vish.dao.table.Comment;

public class CommentManager {
	private ArticleDao articleDao;
	
	public void saveComment(Comment comment) {
		
	}
	
	/**
	 * @return the articleDao
	 */
	public ArticleDao getArticleDao() {
		return articleDao;
	}

	/**
	 * @param articleDao
	 *            the articleDao to set
	 */
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
}
