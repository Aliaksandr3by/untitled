package com.example.untitled.presentation;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint("/websocketchat")
public class WebSocketChatEndpoint {

	@Inject
	private transient Logger logger;


	@OnOpen
	public void connectionOpened() {

		logger.log(Level.INFO, "connection opened");
	}

	@OnMessage
	public synchronized void processMessage(Session session, String message) {

		logger.log(Level.INFO, "received message: {0}", message);

		try {
			for (Session sess : session.getOpenSessions()) {
				if (sess.isOpen()) {
					sess.getBasicRemote().sendText(message);
				}
			}
		} catch (IOException ioe) {
			logger.error(ioe.getCause().getMessage(), ioe);
		}
	}

	@OnClose
	public void connectionClosed() {

		logger.log(Level.INFO, "connection closed");
	}
}