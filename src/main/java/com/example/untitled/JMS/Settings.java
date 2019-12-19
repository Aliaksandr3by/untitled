package com.example.untitled.JMS;

public class Settings {

	final static String ConnectionFactory = "java:/ConnectionFactory";

	final static String QueueExpiry = "java:/jms/queue/ExpiryQueue";

	/*
	 * DLQ (Dead Letter Queue) – это локальная очередь, называемая инача как очередь недоставленных сообщений.
	 * Такую очередь создают для каждого менеджера очередей, чтобы отлавливать неотправленные сообщения,
	 * связанные с проблемами в сети или у получателя
	 * */
	final static String QueueDLQ = "java:/jms/queue/DLQ";

}
