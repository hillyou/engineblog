package com.hico.vish.manager;

import com.hico.vish.dao.processor.BaseDao;
import com.hico.vish.dao.table.BaseEntity;

public abstract class BaseManager<T extends BaseEntity> {
	
	protected BaseDao dao;
	
	public void save(T t) {
		dao.save(t);
	}
	
	public void update(T t) {
		dao.update(t);
	}
	
	public void delete(T t) {
		dao.delete(t);
	}

	/**
	 * @return the baseDao
	 */
	public BaseDao getDao() {
		return dao;
	}

	/**
	 * @param baseDao the baseDao to set
	 */
	public void setBaseDao(BaseDao dao) {
		this.dao = dao;
	}

}
