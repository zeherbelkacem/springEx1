package com.fms.springEx1.Email;

public interface EmailService {
	public void sendSimpleMessage(String to, String subject, String text);
}
