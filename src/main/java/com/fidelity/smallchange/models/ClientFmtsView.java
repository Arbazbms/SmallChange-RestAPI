package com.fidelity.smallchange.models;

import java.time.LocalDate;
import java.util.List;

public class ClientFmtsView {
	private String clientId;
	private String dateOfBirth;
	private String country;
	private String postalCode;
	private String token;
	private List<ClientIdentification> clientIdentification;
	
	public ClientFmtsView(Client client) {
		this.clientId=client.getClientId();
		this.dateOfBirth=client.getDate_of_birth();
		this.country=client.getCountry();
		this.postalCode=client.getPostal();
		this.token=client.getToken();
				
	}
	
	public ClientFmtsView() {}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<ClientIdentification> getClientIdentification() {
		return clientIdentification;
	}

	public void setClientIdentification(List<ClientIdentification> clientIdentification) {
		this.clientIdentification = clientIdentification;
	}
	
}
