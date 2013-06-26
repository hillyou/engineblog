package com.hico.vish.view.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

/**
 * Servlet implementation class TaskQueueService
 */
public class TaskQueueService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER=LoggerFactory.getLogger(TaskQueueService.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
//		try {
//			Queue queue = QueueFactory.getQueue("synch-cache");
//			TaskOptions taskOptions=TaskOptions.Builder.withDefaults();
//	        queue.add(taskOptions.url("/admin/message/flush.html").method(Method.GET));
//		}catch(Exception ex) {
//			LOGGER.error(ex.getMessage());
//		}
	}

}
