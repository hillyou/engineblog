package com.hico.vish.manager;

import java.util.Collection;

import com.hico.vish.dao.processor.XMPPMessageDao;
import com.hico.vish.dao.table.XMPPMessage;

public class XMPPMessageManager extends BaseManager<XMPPMessage>{
	
	public void saveMessages(Collection<XMPPMessage> messages) {
		((XMPPMessageDao)dao).saveMessages(messages);
	}
}
