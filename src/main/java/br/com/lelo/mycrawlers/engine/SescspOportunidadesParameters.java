package br.com.lelo.mycrawlers.engine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SescspOportunidadesParameters {

	@Value("${myproperties.sescsp.url}")
	private String url;

	@Value("${myproperties.sescsp.xpath.expression}")
	private String xpathExpression;

	public String getUrl() {
		return url;
	}

	public String getXpathExpression() {
		return xpathExpression;
	}
}
