package br.com.lelo.mycrawlers.model;

import java.util.Date;
import java.util.List;

public class MessageConsumerItem {

	private Date execution;
	private String value;

	public MessageConsumerItem() {

	}

	public MessageConsumerItem(String value) {
		super();
		this.value = value;
		this.execution = new Date();
	}

	public Date getExecution() {
		return execution;
	}

	public void setExecution(Date execution) {
		this.execution = execution;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((execution == null) ? 0 : execution.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageConsumerItem other = (MessageConsumerItem) obj;
		if (execution == null) {
			if (other.execution != null)
				return false;
		} else if (!execution.equals(other.execution))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KeyValueItem [value=" + value + ", execution=" + execution + "]";
	}

	public static String getMailText(List<MessageConsumerItem> itens) {
		return itens.toString();
	}

}