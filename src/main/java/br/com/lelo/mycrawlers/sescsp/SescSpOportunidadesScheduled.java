package br.com.lelo.mycrawlers.sescsp;

import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.xerces.dom.NodeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import br.com.lelo.mycrawlers.commons.GetRequest;
import br.com.lelo.mycrawlers.commons.InputStreamDocumentConverter;
import br.com.lelo.mycrawlers.commons.KeyValueNodeConverter;
import br.com.lelo.mycrawlers.commons.XPathDocumentFilter;

@Component
public class SescSpOportunidadesScheduled {

	@Autowired
	private SescSpOportunidadesParameters parameters;

	@Autowired
	private GetRequest request;

	@Autowired
	private InputStreamDocumentConverter domConverter;

	@Autowired
	private KeyValueNodeConverter stringConverter;

	@Autowired
	private XPathDocumentFilter xpathFilter;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	@Scheduled(cron = "0 0 10-16 ? * MON,TUE,WED,THU,FRI *")
	public void start() throws Exception {
		logger.info("\n\n*****start*****");

		InputStream inputStream = request.get(parameters.getUrl());
		Document dom = domConverter.convert(inputStream);
		NodeImpl node = xpathFilter.filter(dom, parameters.getXpathExpression());
		System.out.println(stringConverter.convert(node));

		logger.info("\n\n*****fim*****");
	}

}
