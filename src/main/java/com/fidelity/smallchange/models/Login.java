package com.fidelity.smallchange.models;

import java.util.Objects;

public class Login {
	
	private String email;
	private String password;
	public Login(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}


	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}


}
