package br.com.lelo.mycrawlers.commons;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import br.com.lelo.mycrawlers.model.MessageConsumerItem;

@Component
public class KeyValueNodeConverter implements Converter<Node, List<MessageConsumerItem>> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<MessageConsumerItem> convert(Node node) {
		logger.info("Converting node: " + node.getNodeName());

		List<MessageConsumerItem> resultList = new ArrayList<MessageConsumerItem>();
		for (int index = 0; index < node.getChildNodes().getLength(); index++) {
			addValidItem(resultList, node.getChildNodes().item(index));
		}
		return resultList;
	}

	private void addValidItem(List<MessageConsumerItem> resultList, Node node) {
		if ("tbody".equalsIgnoreCase(node.getNodeName())) {
			String realText = this.getRealText(node.getTextContent());
			if (StringUtils.isNotBlank(realText)) {
				resultList.add(new MessageConsumerItem(realText));
			}
		}
	}

	private String getRealText(String dirtyText) {
		return StringUtils.normalizeSpace(dirtyText);
	}

}
