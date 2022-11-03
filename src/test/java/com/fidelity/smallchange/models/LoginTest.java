package com.fidelity.smallchange.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.smallchange.models.Login;

class LoginTest {

	Login l1;
	@BeforeEach
	void init()
	{
		l1=new Login("ashhar","hello@123");
	}
	@Test
	void testLoginCreation() {
		assertNotNull(l1);
	}

}
