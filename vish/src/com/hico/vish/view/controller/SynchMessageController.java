package com.hico.vish.view.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hico.vish.service.MessageSaver;

@Controller
@RequestMapping(value = {"/admin/message/flush"})
public class SynchMessageController {
	private final static Logger LOGGER=LoggerFactory.getLogger(SynchMessageController.class);
	@Autowired
	private MessageSaver messageSaver;

	@RequestMapping
	public void flush(HttpServletResponse response) {
		try {
			LOGGER.info("flush messages from cache into database");
			messageSaver.flush();
			response.getWriter().println("flush succefully");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}

	
}
