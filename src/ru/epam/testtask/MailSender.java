package ru.epam.testtask;


public class MailSender {

	/** @param mailnumber ���������� ����� ����������� ������ */
	static private int mailnumber;
	/** @param preparedmessage ��������� ����������� ��������� */
	private String preparedmessage = ", ������� ���������� ������.";
	
	MailSender () {
		mailnumber = 1;
	}
	
	/**
	 * @param name ��� ����������
	 * @param index ������
	 * @param address �����
	 * @return ������ � ������������ ����������
	 */
	public void sendMessage (String name, String index, String address) {
		System.out.println("��������� ������ � " + mailnumber);
		++mailnumber;
		System.out.println("�����: " + index + address);
		System.out.println("��������� " + name + preparedmessage + '\n');

	}
}
