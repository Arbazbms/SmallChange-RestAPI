package com.fidelity.smallchange.models;


import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;



@JsonAutoDetect()
public class Client {
	private String clientId;
	private String email;
	private String password;
	private String date_of_birth;
	private String country;
	private String postal;
	private List<ClientIdentification> id;
	private String token;
	
	public Client() {
	}

		public Client(String clientId, String email,String password, String date_of_birth, String country,String postal, String token) {
		this.clientId=clientId;
		this.email=email;
		this.date_of_birth=date_of_birth;
		this.postal=postal;
		this.country = country;
		this.token=token;
		this.password=password;
	}
	public Client(String clientId, String email,String password, String date_of_birth, String country,String postal, String type,String value,String token) {
		if(email==""||email==null)
			throw new IllegalArgumentException("email cannot be empty");
		if(country==""||country==null)
			throw new IllegalArgumentException("country cannot be empty");
		if(postal==""||postal==null)
			throw new IllegalArgumentException("postalcannot be empty");
		if(date_of_birth==null)
			throw new IllegalArgumentException("date cannot be null");
		if(type==null && value==null)
			throw new IllegalArgumentException("id cannot be null");
		
		this.clientId = clientId;
		this.email=email;
		this.password=password;
		this.date_of_birth = date_of_birth;
		this.postal=postal;
		this.country = country;
		this.id = List.of(new ClientIdentification(type,value)) ;
		this.token=token;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public List<ClientIdentification> getId() {
		return id;
	}

	public void setId(List<ClientIdentification> id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, country, date_of_birth, email, id, password, postal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(clientId, other.clientId) && Objects.equals(country, other.country)
				&& Objects.equals(date_of_birth, other.date_of_birth) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) 
				&& Objects.equals(postal, other.postal);
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", email=" + email + ", password=" + password + ", date_of_birth="
				+ date_of_birth + ", country=" + country + ", postal=" + postal + ", id=" + id + ", token=" + token
				+ "]";
	}

	
	
}
