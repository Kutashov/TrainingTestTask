package ru.epam.testtask;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Консольное приложение, моделирующее взаимодействие нескольких подсистем,
 * рассылающее информацию адресатам
 * @author Куташов Александр
 * @version 0.9
 */
public class ConsoleInput {

	/** @param smssender Подсистема отправки смс */
	private static SmsSender smssender = new SmsSender();
	/** @param mailsender Подсистема отправки обычных почтовых сообщений */
	private static MailSender mailsender = new MailSender();
	/** @param emailsender Подсистема отправки e-mail */
	private static EmailSender emailsender = new EmailSender();

	
	public static void main(String[] args) {
		
		String lineinput = "";
		String [] name;
		String [] attributes;
	
		Pattern index = Pattern.compile(("[0-9]{6}"));
		Pattern address = Pattern.compile("[^ ]([ ]|[^ ])*");
		Pattern phonenum = Pattern.compile("[+][0-9]{11}");
		Pattern email = Pattern.compile("[^ ]+[@][^ ]+");
		BufferedReader fileinput = null;
		Scanner consoleinput = new Scanner(System.in);
		
		System.out.println("Программа рассылает информацию адресатам из списка");
		
		while (true) {
			try {
				System.out.println("Введите путь к файлу:");
				lineinput = consoleinput.nextLine();
				fileinput = new BufferedReader(new InputStreamReader(new FileInputStream(lineinput), "windows-1251"));
				
				System.out.println("Открываем файл..\n");
				
				while ((lineinput = fileinput.readLine()) != null) {
					
					attributes = lineinput.split("[;]");
					
					if (!checkname(attributes[0])) {
						continue;
					}
					
					name = attributes[0].split("[ ]");	
					if (index.matcher(attributes[1]).matches()) {
						if (address.matcher(attributes[2]).matches()) {
							mailsender.sendMessage(name[1]+" "+name[2], attributes[1], attributes[2]);
							continue;
						}
						
					} else if (phonenum.matcher(attributes[1]).matches()) {
						smssender.sendMessage(name[1]+" "+name[2], attributes[1]);
						continue;
					} else if (email.matcher(attributes[1]).matches()) {
						emailsender.sendMessage(name[1], attributes[1]);
						continue;
					} else {
						System.out.println("Ошибка: " + attributes[0] + " - Контактные данные не распознаны" + '\n');
					}
				}
				
			} catch (FileNotFoundException e) {
				System.out.println("Ошибка: Файл " + lineinput + " не найден" + '\n');
				
			} catch (UnsupportedEncodingException e) {
				System.out.println("Ошибка: Какие-то проблемы с кодировкой..." + '\n');
			
			} catch (IOException e) {
				System.out.println("Ошибка: Произошли проблемы при чтении файла" + '\n');
			} finally {
			
				fileinput = null;
				System.out.println("Хотите повторить попытку?(Yes/No))");
			
				if (consoleinput.nextLine().toLowerCase().equals("yes")) {
					continue;
				} else {
					System.out.println("Закрываемся..");
					System.exit(0);
				}
			}
			
		}
	
	}


	/**
	 * 
	 * @param fullname Проверяемое ФИО пользователя
	 * @return true, если нет ошибок, и false, если ошибки присутствуют
	 */
	private static boolean checkname(String fullname) {
		
		String [] name = fullname.split("[ ]");	
		
		for (String word : name) {
			if (!Character.isUpperCase(word.charAt(0))) {
				System.out.println("Ошибка: " + fullname + " - проблема с заглавными буквами" + '\n');
				return false;
			}
			char [] charset = word.toCharArray();
			for (char symb :  charset) {
				if (!(symb >= 'а' && symb <= 'я' || symb >= 'А' && symb <= 'Я')) {
					System.out.println("Ошибка: " + fullname + " - присутствует нерусский символ" + '\n');
					return false;
				}
			}
		}	
		
		return true;
	}
	

}
