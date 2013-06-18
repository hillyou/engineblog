package com.hico.vish.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hico.vish.service.MessageSaver;

public class SynchMessageService extends HttpServlet{
	private static final long serialVersionUID = -545115537142180691L;
	private final static Logger LOGGER=LoggerFactory.getLogger(SynchMessageService.class);
	private MessageSaver messageSaver;
	
	@Override
	public void init() throws ServletException {
		messageSaver=new MessageSaver();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action=req.getParameter("action");
		if("flush".equals(action)) {
			LOGGER.info("flush messages from cache into database");
			messageSaver.flush();
		}else if(action==null) {
			LOGGER.info("synchronized messages from cache into database");
			messageSaver.synchCache();
		}else {
			LOGGER.warn("Invalid action {}",action);
		}
	}

	
}
