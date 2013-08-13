package com.hico.vish.util;

import java.util.Random;

import com.google.appengine.api.datastore.Email;

public class EmailUtil {

	private final static String MESS_STRING_SEED="*";
	
	public static String messEmail(Email email) {
		if(email!=null) {
			String semail=email.getEmail();
			return messEmail(semail);
		}
		return "";
	}

	public static String messEmail(String semail) {
		int atPosition = semail.lastIndexOf('@');
		String emailAccount=semail.substring(0,atPosition);
		String eamilSuffix=semail.substring(atPosition);
		int emailLength = emailAccount.length();
		if(emailLength<3) {
			return emailAccount+getMessString(emailAccount)+eamilSuffix;
		}
		return emailAccount.substring(0,3)+getMessString(emailAccount)+emailAccount.substring(emailLength-2,emailLength)+eamilSuffix;
	}
	
	private static String getMessString(String s) {
		int size=new Random().nextInt(s.length());
		StringBuilder ss=new StringBuilder();
		for (int i = 0; i < size; i++) {
			ss.append(MESS_STRING_SEED);
		}
		return ss.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(messEmail("fdfsaf2@fdd.com"));
	}

}
