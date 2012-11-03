package ru.epam.testtask;

import java.util.ArrayList;
import java.util.List;


public class EmailSender {

	/** @param preparedmessage Заготовка отсылаемого сообщения */
	private String preparedmessage = ", сегодня прекрасная погода.";
	/** @param curindex Номер SMTP адреса из списка для следующего сообщения */
	private int curindex = 0;
	/** @param smtpaddresses Список SMTP адресов */
	private List<String> smtpaddresses = new ArrayList<String>();
	
	public EmailSender() {
		smtpaddresses.add("10.20.133.134");
		smtpaddresses.add("10.20.134.135");
		smtpaddresses.add("10.21.135.136");
	}
	
	/**
	 * @param name Имя получателя
	 * @param address Адрес электронной почты
	 * @return Строку с получившемся сообщением
	 */
	public void sendMessage (String name, String address) {
		System.out.println("E-mail to: " + address +" using SMTP: " + getCurSmtpAddress() + " | " + name + preparedmessage + '\n');
	}

	/**
	 * @return SMTP адрес для текущего сообщения
	 */
	private String getCurSmtpAddress() {
		String temp = smtpaddresses.get(curindex);
		curindex = ++curindex % smtpaddresses.size();
		return temp;
	}
}
