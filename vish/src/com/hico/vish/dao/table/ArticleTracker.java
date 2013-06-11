package com.hico.vish.dao.table;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable="true")
@Inheritance(customStrategy = "complete-table")
public class ArticleTracker extends StatusEntity{
private static final long serialVersionUID = 1L;

private Key articleKey;

private int viewCount;




	
	
}
