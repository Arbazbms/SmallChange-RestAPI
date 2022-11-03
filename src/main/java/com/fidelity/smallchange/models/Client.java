package com.fidelity.smallchange.models;

import java.time.LocalDate;
import java.util.Objects;

public class Client {
	private String clientId;
	private Login login_credentials;
	private LocalDate date_of_birth;
	private String country;
	private String postal;
	private Identification id;
	private ClientRegisterationValidation crv;
	public Client(String clientId, String email,String password, LocalDate date_of_birth, String country,String postal, Identification id) {
		if(clientId==""||clientId==null)
			throw new IllegalArgumentException("client id cannot be empty");
		if(email==""||email==null)
			throw new IllegalArgumentException("email cannot be empty");
		if(country==""||country==null)
			throw new IllegalArgumentException("country cannot be empty");
		if(postal==""||postal==null)
			throw new IllegalArgumentException("postalcannot be empty");
		if(date_of_birth==null)
			throw new IllegalArgumentException("date cannot be null");
		if(id==null)
			throw new IllegalArgumentException("id cannot be null");
		
		this.clientId = clientId;
		this.login_credentials=new Login(email,password);
		this.date_of_birth = date_of_birth;
		this.postal=postal;
		this.country = country;
		this.id = id;
	}
	public Login getLogin_credentials() {
		return login_credentials;
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
	public Identification getId() {
		return id;
	}
	public ClientRegisterationValidation getCrv() {
		return crv;
	}
	@Override
	public int hashCode() {
		return Objects.hash(clientId, login_credentials, id);
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
		return Objects.equals(clientId, other.clientId) || Objects.equals(login_credentials, other.login_credentials)
				|| Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", email=" + this.login_credentials.getEmail()+ ", date_of_birth=" + date_of_birth + ", country="
				+ country + ", id=" + id + ", crv=" + crv + "]";
	}
	public String getClientId() {
		return this.clientId;
	}
	public void register() {
		crv.clientExists(this);
	}
	public boolean authenticate(String email_to_test, String password_to_test) {
	if(this.login_credentials.equals(new Login(email_to_test,password_to_test)))
		return true;
	else
		return false;
}
}
