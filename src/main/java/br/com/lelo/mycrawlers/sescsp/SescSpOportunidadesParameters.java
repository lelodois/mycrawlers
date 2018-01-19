package br.com.lelo.mycrawlers.sescsp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SescSpOportunidadesParameters {

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
