package ru.epam.testtask;

import java.util.ArrayList;
import java.util.List;


public class EmailSender {

	/** @param preparedmessage ��������� ����������� ��������� */
	private String preparedmessage = ", ������� ���������� ������.";
	/** @param curindex ����� SMTP ������ �� ������ ��� ���������� ��������� */
	private int curindex = 0;
	/** @param smtpaddresses ������ SMTP ������� */
	private List<String> smtpaddresses = new ArrayList<String>();
	
	public EmailSender() {
		smtpaddresses.add("10.20.133.134");
		smtpaddresses.add("10.20.134.135");
		smtpaddresses.add("10.21.135.136");
	}
	
	/**
	 * @param name ��� ����������
	 * @param address ����� ����������� �����
	 * @return ������ � ������������ ����������
	 */
	public void sendMessage (String name, String address) {
		System.out.println("E-mail to: " + address +" using SMTP: " + getCurSmtpAddress() + " | " + name + preparedmessage + '\n');
	}

	/**
	 * @return SMTP ����� ��� �������� ���������
	 */
	private String getCurSmtpAddress() {
		String temp = smtpaddresses.get(curindex);
		curindex = ++curindex % smtpaddresses.size();
		return temp;
	}
}
