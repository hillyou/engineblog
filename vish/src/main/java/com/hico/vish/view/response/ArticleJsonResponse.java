package com.hico.vish.view.response;

import java.io.Serializable;

public class ArticleJsonResponse implements Serializable{
	private static final long serialVersionUID = -6681001282301365842L;

	private String articleId;
	
	private String message;

	/**
	 * @return the articleId
	 */
	public String getArticleId() {
		return articleId;
	}

	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
