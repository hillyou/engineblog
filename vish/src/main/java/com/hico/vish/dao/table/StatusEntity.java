package com.hico.vish.dao.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable="true")
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class StatusEntity extends BaseEntity{
	private static final long serialVersionUID = -6577278653216162740L;
	@Persistent
	protected boolean isDeleted=false;
	@Persistent
	protected boolean isValid=true;
	@Persistent
	protected boolean isLocked=false;
	
	/**
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}
	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * @return the isValid
	 */
	public boolean isValid() {
		return isValid;
	}
	/**
	 * @param isValid the isValid to set
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	/**
	 * @return the isLocked
	 */
	public boolean isLocked() {
		return isLocked;
	}
	/**
	 * @param isLocked the isLocked to set
	 */
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	
	public boolean isUsable() {
		if(!isDeleted && !isLocked && isValid) {
			return true;
		}
		return false;
	}
	
	protected List<? extends StatusEntity> getUsableEntities(List<? extends StatusEntity> entityList){
		return getSortsOfEntities(entityList,EntityStatus.USABLE);
	}
	
	protected List<? extends StatusEntity> getUnusableEntities(List<? extends StatusEntity> entityList){
		return getSortsOfEntities(entityList,EntityStatus.UNUSABLE);
	}
	
	protected List<? extends StatusEntity> getDeletedEntities(List<? extends StatusEntity> entityList){
		return getSortsOfEntities(entityList,EntityStatus.DELETED);
	}
	
	protected List<? extends StatusEntity> getInvalidEntities(List<? extends StatusEntity> entityList){
		return getSortsOfEntities(entityList,EntityStatus.INVALID);
	}
	
	protected List<? extends StatusEntity> getLockedEntities(List<? extends StatusEntity> entityList){
		return getSortsOfEntities(entityList,EntityStatus.LOCKED);
	}
	
	
	@SuppressWarnings("unchecked")
	private List<? extends StatusEntity> getSortsOfEntities(List<? extends StatusEntity> entityList,EntityStatus status){
		List kinds=new ArrayList();
		if(entityList==null){
			return kinds;
		}
		for(StatusEntity entity:entityList) {
			if(EntityStatus.USABLE.equals(status) && entity.isUsable()) {
				kinds.add(entity);
			}else if(EntityStatus.UNUSABLE.equals(status) && !entity.isUsable()) {
				kinds.add(status);
			}else if(EntityStatus.DELETED.equals(status) && entity.isDeleted()) {
				kinds.add(status);
			}else if(EntityStatus.INVALID.equals(status) && !entity.isValid()) {
				kinds.add(status);
			}else if(EntityStatus.LOCKED.equals(status) && entity.isLocked()) {
				kinds.add(status);
			}
		}
		Collections.sort(kinds);
		return kinds;
	}
	
}
