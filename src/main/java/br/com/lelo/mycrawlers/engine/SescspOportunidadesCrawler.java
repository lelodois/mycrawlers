package br.com.lelo.mycrawlers.engine;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.xerces.dom.NodeImpl;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import br.com.lelo.mycrawlers.exception.SescspInvalidXpahExpression;

@Component
public class SescspOportunidadesCrawler {

	@Autowired
	private SescspOportunidadesParameters parameters;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	public void start() throws Exception {
		logger.info("\n\n*****start*****");
		this.filter(this.getContent());
		logger.info("*****finish*****\n\n");
	}

	private void filter(InputStream inputStream)
			throws XPathExpressionException, SescspInvalidXpahExpression, SAXException, IOException, ParserConfigurationException {
		logger.info("Filtering with expression: " + parameters.getXpathExpression());

		CleanerProperties cleanerProperties = new CleanerProperties();
		cleanerProperties.setCharset("UTF-8");
		TagNode tagNode = new HtmlCleaner(cleanerProperties).clean(inputStream);
		Document dom = new DomSerializer(cleanerProperties).createDOM(tagNode);

		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeImpl result = (NodeImpl) xPath.evaluate(parameters.getXpathExpression(), dom, XPathConstants.NODE);
		if (result == null) {
			throw new SescspInvalidXpahExpression();
		}
		logger.info(result.getNodeName());
	}

	private InputStream getContent() throws ClientProtocolException, IOException {
		logger.info("Searching in: " + parameters.getUrl());
		InputStream inputStream = Request.Get(parameters.getUrl()).execute().returnContent().asStream();
		logger.info("Result length: " + inputStream);
		return inputStream;
	}

}
