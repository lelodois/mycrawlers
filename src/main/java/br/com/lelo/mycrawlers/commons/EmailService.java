package br.com.lelo.mycrawlers.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Scope(scopeName = "prototype")
public class EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	public SimpleMailMessage message;

	public void send(String to, String subject, String text) {
		message.setText(String.format(message.getText(), text));
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}
}