package com.fidelity.smallchange.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.ClientRegisterationValidation;
import com.fidelity.smallchange.models.ClientIdentification;

class ClientRegisterationTest {

	Client new_client;
	@BeforeEach
	void init() {
		new_client= new Client("Ashhar","ashh@gmail.com","password123","2020-2-2","India","38100","adhaar","208979677","");
		
	}
	
	@Test
	void testClientRegisteration() {
		Client new_client2= new Client("Ahhar","ashh@gmail.com","password123","2020-2-2","India","2334","adhaar","208979677","");
		ClientRegisterationValidation.clientExists(new_client);
		Client result=ClientRegisterationValidation.getClient(new_client.getClientId());
		assertEquals(new_client,result);
     	assertNotEquals(new_client2,result);
	}
	@Test
	void testClientRegisteration_failure_already_exists() {
		Client new_client2= new Client("Ashhara","asaahh@gmail.com","password123","2020-2-2","Indiaaa","87751","adhaaaar","2081979677","");
		ClientRegisterationValidation.clientExists(new_client2);
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			ClientRegisterationValidation.clientExists(new_client2);});
		assertEquals("Client Already Exists",e.getMessage());
	}

		

}
