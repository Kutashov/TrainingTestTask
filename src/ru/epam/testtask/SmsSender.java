package ru.epam.testtask;


public class SmsSender {
	
	/** @param preparedmessage ��������� ����������� ��������� */
	private String preparedmessage = ", ������� ���������� ������.";
	
	/**
	 * @param name ��� ����������
	 * @param number ����� ��������
	 * @return ������ � ������������ ����������
	 */
	public void sendMessage (String name, String number) {
		System.out.println("SMS �� �����: " + number + " | ��������� "+ name + preparedmessage + '\n');
	}
}
