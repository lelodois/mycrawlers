package br.com.lelo.mycrawlers.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class BeansMailConfigurer {

	@Bean
	public SimpleMailMessage templateSimpleMessage() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("-- Start --");
		sqlBuilder.append(" Email com as oportunidades do Sescsp ");
		sqlBuilder.append("\n\t").append("%s").append("\t\n");
		sqlBuilder.append("-- Finish --");
		mailMessage.setText(sqlBuilder.toString());
		return mailMessage;
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("leoeduar@gmail.com");
		mailSender.setPassword("");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}
}
