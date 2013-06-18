package com.hico.vish.dao.processor;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.hico.vish.dao.table.XMPPMessage;

public class XMPPMessageDao extends BaseDao<XMPPMessage>{

	public void saveMessages(Collection<XMPPMessage> messages) {
		PersistenceManager  persistenceManager=persistenceManagerFactory.getPersistenceManager();
		Transaction transaction=persistenceManager.currentTransaction();
		try {
			transaction.begin();
			persistenceManager.makePersistentAll(messages);
			transaction.commit();
		}catch(Exception ex) {
			if(transaction.isActive()) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}finally{
			persistenceManager.close();
		}
	}
	
}
