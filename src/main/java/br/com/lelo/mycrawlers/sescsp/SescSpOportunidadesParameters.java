package br.com.lelo.mycrawlers.sescsp;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.lelo.mycrawlers.model.MessageConsumerItem;

@Component
public class SescSpOportunidadesParameters {

	@Value("${myproperties.sescsp.url}")
	private String url;

	@Value("${myproperties.sescsp.xpath.expression}")
	private String xpathExpression;

	@Value("${myproperties.sescsp.emptymessage}")
	private String emptyMessage;

	@Value("#{'${myproperties.sescsp.mail.destinatarios}'.split(';')}")
	private List<String> destinatarios;

	private String lastMailKey;

	public String getUrl() {
		return url;
	}

	public String getXpathExpression() {
		return xpathExpression;
	}

	public boolean isNotEmpty(List<MessageConsumerItem> convert) {
		return convert.isEmpty() || (convert.size() == 1 && emptyMessage.equalsIgnoreCase(convert.get(0).getValue()));
	}

	public List<String> getDestinatarios() {
		return destinatarios;
	}

	public String getLastMailKey() {
		return lastMailKey;
	}

	public void setLastMailKey(String lastMailKey) {
		this.lastMailKey = lastMailKey;
	}
}
