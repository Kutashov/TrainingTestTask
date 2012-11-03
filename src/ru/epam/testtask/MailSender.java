package ru.epam.testtask;


public class MailSender {

	/** @param mailnumber Порядковый номер отсылаемого письма */
	static private int mailnumber;
	/** @param preparedmessage Заготовка отсылаемого сообщения */
	private String preparedmessage = ", сегодня прекрасная погода.";
	
	MailSender () {
		mailnumber = 1;
	}
	
	/**
	 * @param name Имя получателя
	 * @param index Индекс
	 * @param address Адрес
	 * @return Строку с получившемся сообщением
	 */
	public void sendMessage (String name, String index, String address) {
		System.out.println("Исходящее письмо № " + mailnumber);
		++mailnumber;
		System.out.println("Адрес: " + index + address);
		System.out.println("Уважаемый " + name + preparedmessage + '\n');

	}
}
