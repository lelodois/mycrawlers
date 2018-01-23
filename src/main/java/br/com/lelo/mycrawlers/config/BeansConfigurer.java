package br.com.lelo.mycrawlers.config;

import java.nio.charset.Charset;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.CleanerProperties;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeansConfigurer {

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public XPath xPath() {
		return XPathFactory.newInstance().newXPath();
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public CleanerProperties cleanerProperties() {
		CleanerProperties cleaner = new CleanerProperties();
		cleaner.setCharset(Charset.forName("utf-8").name());
		return cleaner;
	}

}
