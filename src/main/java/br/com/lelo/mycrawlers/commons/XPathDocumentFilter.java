package br.com.lelo.mycrawlers.commons;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;

import org.apache.xerces.dom.NodeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

@Component
public class XPathDocumentFilter {

	@Autowired
	private XPath xPath;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public NodeImpl filter(Document dom, String expression) throws XPathException {

		logger.info("Filtering with expression: " + expression);
		NodeImpl result = (NodeImpl) xPath.evaluate(expression, dom, XPathConstants.NODE);

		if (result == null) {
			throw new XPathException("Nada localizado coa expressão: " + expression);
		}

		return result;
	}

}
