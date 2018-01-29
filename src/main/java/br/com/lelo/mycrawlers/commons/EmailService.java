package br.com.lelo.mycrawlers.commons;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.lelo.mycrawlers.model.MessageConsumerItem;

@Service
@Scope(scopeName = "prototype")
public class EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	public SimpleMailMessage message;

	public String send(List<MessageConsumerItem> itens, List<String> destinatarios, String lastMailSended) {
		String mailText = MessageConsumerItem.getMailText(itens);
		for (String destinatario : destinatarios) {
			if (mailText.equalsIgnoreCase(lastMailSended) == false) {
				send(destinatario, "[Oportunidades Sesc]", mailText);
			}
		}
		return mailText;
	}

	private void send(String to, String subject, String text) {
		message.setText(String.format(message.getText(), text));
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

}