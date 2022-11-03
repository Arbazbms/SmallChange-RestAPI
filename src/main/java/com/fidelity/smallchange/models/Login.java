package com.fidelity.smallchange.models;

import java.util.Objects;

public class Login {
	
	public String email;
	public String password;
	public Login(String email, String password) {
		this.email = email;
		this.password = password;
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
	public String getEmail() {
		return email;
	}

}
