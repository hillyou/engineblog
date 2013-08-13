package com.hico.vish.view.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.hico.vish.dao.table.Article;
import com.hico.vish.manager.ArticleManager;

public class ArticleResolver implements HandlerMethodArgumentResolver{
	@Autowired
	private ArticleManager articleManager;
	
	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer container, NativeWebRequest request,
			WebDataBinderFactory factory) throws Exception {
		String id=request.getParameter("keysid");
		if(id!=null) {
			try {
				Article article=articleManager.get(Long.valueOf(id));
				return article;
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if(parameter.getParameterType()== Article.class) {
			return true;
		}
		return false;
	}

}
