package com.hico.vish.service;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hico.vish.dao.processor.XMPPMessageDao;
import com.hico.vish.dao.table.XMPPMessage;
import com.hico.vish.dao.util.PMF;
import com.hico.vish.manager.XMPPMessageManager;

/*@Service
@Resource(name="messageSaver")*/
public class MessageSaver implements Observer{
	private static final MessageCacher INSTANCE = MessageCacher.getInstance();
	private final static Logger LOGGER=LoggerFactory.getLogger(MessageSaver.class);
	private static XMPPMessageManager messageManager;
	private final static int CACHE_LIMIT_LENGTH=100;
//	private final static int CACHE_LIMIT_SIZE=20;
	static{
		XMPPMessageDao dao=new XMPPMessageDao();
		dao.setPersistenceManagerFactory(PMF.getInstance().get());
		messageManager=new XMPPMessageManager();
		messageManager.setDao(dao);
	}
	public synchronized void flush() {
		saveMessages();
	}
	
	private void saveMessages() {
		List<XMPPMessage> messages=INSTANCE.getAll();
		messageManager.saveMessages(messages);
		LOGGER.info("{} messages have been saved",messages.size());
		INSTANCE.removeAll();
		LOGGER.debug("removing message from cache after saved into DB");
	}
	
	
	private boolean checkCahce() {
		List<XMPPMessage> messages=INSTANCE.getAll();
		int count=messages.size();
		LOGGER.debug("checking cache information, count {}",count);
		if(count>CACHE_LIMIT_LENGTH ) {
			return true;
		}
		return false;
	}

	@Override
	public synchronized void update(Observable o, Object messages) {
		if(checkCahce()) {
			saveMessages();
		}
	}
	
}
