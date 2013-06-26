package com.hico.vish.view.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.MessageType;
import com.google.appengine.api.xmpp.Presence;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.hico.vish.service.MessageMonitor;
import com.hico.vish.service.MessageSaver;

public class XmppService extends HttpServlet {
	private static final long serialVersionUID = -1911436328355288719L;
	private final static Logger LOGGER=LoggerFactory.getLogger(XmppService.class);
	private XMPPService xmppService;
	private MessageMonitor messageMonitor;
	MessageSaver messageSaver;

	@Override
	public void init() {
		this.xmppService = XMPPServiceFactory.getXMPPService();
		this.messageMonitor=new MessageMonitor();
		this.messageSaver=new MessageSaver();
		this.messageMonitor.addObserver(messageSaver);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Message message = new MessageBuilder()
				.withMessageType(MessageType.CHAT)
				.withFromJid(new JID(req.getParameter("from")))
				.withRecipientJids(new JID(req.getParameter("to")))
				.withBody(req.getParameter("body")).build();
		processMessage(message, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		Message message=xmppService.parseMessage(req);
		this.messageMonitor.process(message);
		processMessage(message, res);
	}

	public void processMessage(Message message, HttpServletResponse res) throws IOException{
		JID recipientJid = message.getRecipientJids()[0];
		Presence presence = xmppService.getPresence(recipientJid);
		try {
			if(presence.isAvailable()) {
				SendResponse response = xmppService.sendMessage(message);
				if(response.getStatusMap().get(recipientJid) == SendResponse.Status.SUCCESS) {
					res.sendRedirect("/message.html?message=Message send successfully.");
				}else {
					sendMail(message);
				}
			}else {
				sendMail(message);
			}
			res.sendRedirect("/message.html?message=Message send successfully.");
		}catch(Exception ex){
			LOGGER.error(ex.getMessage());
			res.sendRedirect("/message.html?message=Error when trying to processing message.");
		}
	}

	@Override
	public void destroy() {
		this.messageSaver.flush();
		this.messageMonitor.deleteObservers();
		this.messageSaver=null;
	}
	
	private void sendMail(Message message) throws IOException, MessagingException {
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
		javax.mail.Message msg = new MimeMessage(session);
	    msg.setFrom(new InternetAddress(message.getFromJid().getId()));
	    msg.addRecipient(javax.mail.Message.RecipientType.TO,
	                     new InternetAddress("hillyou@gmail.com"));
	    msg.setSubject("Message from "+message.getFromJid().getId());
	    msg.setText(message.getBody());
	    Transport.send(msg);
	}
	
}
