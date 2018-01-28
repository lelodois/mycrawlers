package br.com.lelo.mycrawlers.sescsp;

import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.xerces.dom.NodeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import br.com.lelo.mycrawlers.commons.EmailService;
import br.com.lelo.mycrawlers.commons.GetRequest;
import br.com.lelo.mycrawlers.commons.InputStreamDocumentConverter;
import br.com.lelo.mycrawlers.commons.KeyValueNodeConverter;
import br.com.lelo.mycrawlers.commons.XPathDocumentFilter;
import br.com.lelo.mycrawlers.model.MessageConsumerItem;

@Component
public class SescSpOportunidadesScheduled {

	@Autowired
	private SescSpOportunidadesParameters parameters;

	@Autowired
	private InputStreamDocumentConverter domConverter;

	@Autowired
	private KeyValueNodeConverter keyValueConverter;

	@Autowired
	private XPathDocumentFilter xpathFilter;

	@Autowired
	private GetRequest getRequest;

	@Autowired
	private ApplicationContext context;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	public void init() throws Exception {
		start();
	}

	@Scheduled(cron = "0 0 9-18 * * MON-FRI")
	public void start() throws Exception {
		logger.info("\n\n*****start*****");

		InputStream inputStream = getRequest.get(parameters.getUrl());
		Document dom = domConverter.convert(inputStream);
		NodeImpl node = xpathFilter.filter(dom, parameters.getXpathExpression());
		this.sendMail(keyValueConverter.convert(node));
	}

	private void sendMail(List<MessageConsumerItem> itens) {
		logger.info("sending email");

		for (String destinatario : parameters.getDestinatarios()) {
			String mailText = MessageConsumerItem.getMailText(itens);
			if (mailText.equalsIgnoreCase(parameters.getLastMailKey()) == false) {
				context.getBean(EmailService.class).send(destinatario, "[Oportunidades Sesc]", mailText);
				parameters.setLastMailKey(mailText);
			}
		}
		logger.info("\n\n*****fim*****");
	}

}
