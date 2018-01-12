package br.com.lelo.mycrawlers.engine;

import static javax.xml.xpath.XPathConstants.STRING;

import java.io.IOException;
import java.io.StringReader;

import javax.annotation.PostConstruct;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;

import br.com.lelo.mycrawlers.exception.SescspInvalidXpahExpression;

@Component
public class SescspOportunidadesCrawler {

	@Autowired
	private SescspOportunidadesParameters parameters;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	public void start() {
		logger.info("\n\n*****start*****");
		try {
			this.filter(this.getContent());
		} catch (XPathExpressionException e) {
			logger.error("Xpression invalida");
		} catch (ClientProtocolException e) {
			logger.error("url invalida ou indisponível");
		} catch (SescspInvalidXpahExpression e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("*****finish*****\n\n");
	}

	private void filter(String resultStringContent) throws XPathExpressionException, SescspInvalidXpahExpression {
		logger.info("Filtering with expression: " + parameters.getXpathExpression());

		InputSource source = new InputSource(new StringReader(resultStringContent));
		XPath xPath = XPathFactory.newInstance().newXPath();
		XPathExpression expression = xPath.compile(parameters.getXpathExpression());
		String result = expression.evaluate(source, STRING).toString().trim();
		if (result.isEmpty()) {
			throw new SescspInvalidXpahExpression();
		}
		logger.info(result);
	}

	private String getContent() throws ClientProtocolException, IOException {
		logger.info("Searching in: " + parameters.getUrl());
		String resultStringContent = Request.Get(parameters.getUrl()).execute().returnContent().asString();
		logger.info("Result length: " + resultStringContent.length());
		return resultStringContent;
	}

}
