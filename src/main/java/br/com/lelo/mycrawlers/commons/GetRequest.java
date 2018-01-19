package br.com.lelo.mycrawlers.commons;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetRequest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public InputStream get(String url) throws IOException {
		logger.info("Searching in: " + url);
		InputStream inputStream = Request.Get(url).execute().returnContent().asStream();
		logger.info("Result length: " + inputStream);
		return inputStream;
	}

}
