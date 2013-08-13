package com.hico.vish.manager;

import java.util.Collection;

import com.hico.vish.dao.processor.BaseDao;
import com.hico.vish.dao.table.BaseEntity;

public abstract class BaseManager<T extends BaseEntity> {
	
	protected BaseDao dao;
	
	public void save(T t) {
//		t.setCreateDate(new Date());
		dao.save(t);
	}
	
	public void update(T t) {
		dao.update(t);
	}
	
	public void delete(T t) {
		dao.delete(t);
	}
	
	public void delete(Collection<T> t) {
		dao.delete(t);
	}
	
	public T get(Object id) {
		return (T)dao.get(id);
	}
	
	public Collection<T> get(Collection ids,Class<T> clazz) {
		return dao.get(ids,clazz);
	}

	/**
	 * @return the dao
	 */
	public BaseDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

}
