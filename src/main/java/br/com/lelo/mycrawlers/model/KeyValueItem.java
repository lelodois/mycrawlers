package br.com.lelo.mycrawlers.model;

public class KeyValueItem {

	private String title;
	private Object value;

	public KeyValueItem() {

	}

	public KeyValueItem(String title, Object value) {
		super();
		this.title = title;
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public Object getValue() {
		return value;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		KeyValueItem other = (KeyValueItem) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
		return "KeyValueItem [title=" + title + ", value=" + value + "]";
	}

}
