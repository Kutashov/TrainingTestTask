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
 * ���������� ����������, ������������ �������������� ���������� ���������,
 * ����������� ���������� ���������
 * @author ������� ���������
 * @version 0.9
 */
public class ConsoleInput {

	/** @param smssender ���������� �������� ��� */
	private static SmsSender smssender = new SmsSender();
	/** @param mailsender ���������� �������� ������� �������� ��������� */
	private static MailSender mailsender = new MailSender();
	/** @param emailsender ���������� �������� e-mail */
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
		
		System.out.println("��������� ��������� ���������� ��������� �� ������");
		
		while (true) {
			try {
				System.out.println("������� ���� � �����:");
				lineinput = consoleinput.nextLine();
				fileinput = new BufferedReader(new InputStreamReader(new FileInputStream(lineinput), "windows-1251"));
				
				System.out.println("��������� ����..\n");
				
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
						System.out.println("������: " + attributes[0] + " - ���������� ������ �� ����������" + '\n');
					}
				}
				
			} catch (FileNotFoundException e) {
				System.out.println("������: ���� " + lineinput + " �� ������" + '\n');
				
			} catch (UnsupportedEncodingException e) {
				System.out.println("������: �����-�� �������� � ����������..." + '\n');
			
			} catch (IOException e) {
				System.out.println("������: ��������� �������� ��� ������ �����" + '\n');
			} finally {
			
				fileinput = null;
				System.out.println("������ ��������� �������?(Yes/No))");
			
				if (consoleinput.nextLine().toLowerCase().equals("yes")) {
					continue;
				} else {
					System.out.println("�����������..");
					System.exit(0);
				}
			}
			
		}
	
	}


	/**
	 * 
	 * @param fullname ����������� ��� ������������
	 * @return true, ���� ��� ������, � false, ���� ������ ������������
	 */
	private static boolean checkname(String fullname) {
		
		String [] name = fullname.split("[ ]");	
		
		for (String word : name) {
			if (!Character.isUpperCase(word.charAt(0))) {
				System.out.println("������: " + fullname + " - �������� � ���������� �������" + '\n');
				return false;
			}
			char [] charset = word.toCharArray();
			for (char symb :  charset) {
				if (!(symb >= '�' && symb <= '�' || symb >= '�' && symb <= '�')) {
					System.out.println("������: " + fullname + " - ������������ ��������� ������" + '\n');
					return false;
				}
			}
		}	
		
		return true;
	}
	

}
