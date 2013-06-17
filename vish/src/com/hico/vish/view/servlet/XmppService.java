package com.hico.vish.view.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jsr107cache.Cache;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.MessageType;
import com.google.appengine.api.xmpp.Presence;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.hico.vish.service.CacheService;

public class XmppService extends HttpServlet {
	private static final long serialVersionUID = -1911436328355288719L;
	private XMPPService xmppService;
	private Cache cache;

	@Override
	public void init() {
		this.xmppService = XMPPServiceFactory.getXMPPService();
		this.cache=CacheService.getCache();
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
		processMessage(message, res);
	}

	public void processMessage(Message message, HttpServletResponse res)
			throws IOException {
		JID fromId = message.getFromJid();
		Presence presence = xmppService.getPresence(fromId);
		String presenceString = presence.isAvailable() ? "" : "not ";
		SendResponse response = xmppService.sendMessage(new MessageBuilder()
				.withBody(
						message.getBody() + " (you are " + presenceString
								+ "available)").withRecipientJids(fromId)
				.build());

		for (Map.Entry<JID, SendResponse.Status> entry : response
				.getStatusMap().entrySet()) {
			res.getWriter().println(
					entry.getKey() + "," + entry.getValue() + "<br>");
		}

		res.getWriter().println("processed");
	}
}