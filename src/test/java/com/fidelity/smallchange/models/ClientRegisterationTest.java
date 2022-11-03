package com.fidelity.smallchange.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.ClientRegisterationValidation;
import com.fidelity.smallchange.models.Identification;

class ClientRegisterationTest {

	Client new_client;
	@BeforeEach
	void init() {
		new_client= new Client("Ashhar","ashh@gmail.com","hello",LocalDate.of(2011, 10, 10),"India","38100",new Identification("adhaar","208979677"));
		
	}
	
	@Test
	void testClientRegisteration() {
		Client new_client2= new Client("Ahhar","ashh@gmail.com","hello",LocalDate.of(2011, 10, 10),"India","2334",new Identification("adhaar","208979677"));
		ClientRegisterationValidation.clientExists(new_client);
		Client result=ClientRegisterationValidation.getClient(new_client.getClientId());
		assertEquals(new_client,result);
//		assertNotEquals(new_client2,result);
	}
	@Test
	void testClientRegisteration_failure_already_exists() {
		Client new_client2= new Client("Ashhara","asaahh@gmail.com","hello",LocalDate.of(2011, 10, 10),"Indiaaa","87751",new Identification("adhaaaar","2081979677"));
		ClientRegisterationValidation.clientExists(new_client2);
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			ClientRegisterationValidation.clientExists(new_client2);});
		assertEquals("Client Already Exists",e.getMessage());
	}

		

}
