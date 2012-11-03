package ru.epam.testtask;


public class SmsSender {
	
	/** @param preparedmessage Заготовка отсылаемого сообщения */
	private String preparedmessage = ", сегодня прекрасная погода.";
	
	/**
	 * @param name Имя получателя
	 * @param number Номер телефона
	 * @return Строку с получившемся сообщением
	 */
	public void sendMessage (String name, String number) {
		System.out.println("SMS на номер: " + number + " | Уважаемый "+ name + preparedmessage + '\n');
	}
}
