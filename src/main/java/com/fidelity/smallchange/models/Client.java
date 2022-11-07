package com.fidelity.smallchange.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


//@JsonAutoDetect()
public class Client {
	private String clientId;
	private String email;
//	@JsonDeserialize(using = LocalDateDeserializer.class)  
//	@JsonSerialize(using = LocalDateSerializer.class) 
	@JsonIgnore
	private LocalDate date_of_birth;
	private String country;
	private String postal;
	private List<ClientIdentification> id;
	private String token;
//	private ClientRegisterationValidation crv;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		token = token;
	}

	public Client() {
	}

		public Client(String clientId, String email, LocalDate date_of_birth, String country,String postal) {
		this.clientId=clientId;
		this.email=email;
		this.date_of_birth=date_of_birth;
		this.postal=postal;
		this.country = country;
	}
	public Client(String clientId, String email, LocalDate date_of_birth, String country,String postal, String type,String value) {
//		if(clientId==""||clientId==null)
//			throw new IllegalArgumentException("client id cannot be empty");
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
		//this.login_credentials=new Login(email,password);
		this.date_of_birth = date_of_birth;
		this.postal=postal;
		this.country = country;
		this.id = List.of(new ClientIdentification(type,value)) ;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public void setId(List<ClientIdentification> id) {
		this.id = id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	
	public String getClientId() {
		return this.clientId;
	}
	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}
	public String getCountry() {
		return country;
	}
	public String getPostal() {
		return postal;
	}
	public List<ClientIdentification> getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, country, date_of_birth, email, id, postal);
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
				&& Objects.equals(id, other.id) && Objects.equals(postal, other.postal);
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", email=" + email + ", date_of_birth=" + date_of_birth + ", country="
				+ country + ", postal=" + postal + ", id=" + id + "]";
	}
	
	
}
