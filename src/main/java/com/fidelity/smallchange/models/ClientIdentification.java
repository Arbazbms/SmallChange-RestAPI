package com.fidelity.smallchange.models;

import java.util.Objects;

public class ClientIdentification {
	
	private String type;
	private String value;
	public ClientIdentification() {}
	public ClientIdentification(String type, String value) {
		if(type==""||value=="")
			throw new IllegalArgumentException("Valid identification not provided");
		this.type = type;
		this.value = value;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		return Objects.hash(type, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientIdentification other = (ClientIdentification) obj;
		return Objects.equals(type, other.type) && Objects.equals(value, other.value);
	}
	public String getType() {
		return type;
	}
	public String getValue() {
		return value;
	}
	@Override
	public String toString() {
		return "ClientIdentification [type=" + type + ", value=" + value + "]";
	}
	

}
