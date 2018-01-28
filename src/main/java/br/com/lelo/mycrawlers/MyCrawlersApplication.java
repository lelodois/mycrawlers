package br.com.lelo.mycrawlers;

import java.nio.charset.Charset;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.htmlcleaner.CleanerProperties;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyCrawlersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCrawlersApplication.class, args);
	}

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
