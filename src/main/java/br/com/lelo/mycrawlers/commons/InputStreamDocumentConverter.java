package br.com.lelo.mycrawlers.commons;

import java.io.InputStream;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

@Component
public class InputStreamDocumentConverter implements Converter<InputStream, Document> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CleanerProperties cleaner;

	@Override
	public Document convert(InputStream source) {
		try {
			logger.info("Converting input: " + source);
			TagNode tagNode = new HtmlCleaner(cleaner).clean(source);
			return new DomSerializer(cleaner).createDOM(tagNode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
