package com.fidelity.smallchange.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.ClientRegisterationValidation;
import com.fidelity.smallchange.models.ClientIdentification;

class ClientTest {

	private Client client;
	@BeforeEach
	void init() {
		client=new Client("C101","ashr@gmail.com","2020-2-2","India","678987","adhaar","208979677");
	}
	@Test
	void testClientCreation_success() {
		assertNotNull(client);
		
	}
	@Test
	void testClientCreation_failure_emptystring() {
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			Client client2=new Client("","ashh@gmail.com","2020-2-2","India","33","adhaar","208979677");});
		assertEquals("client id cannot be empty",e.getMessage());
		
	}
	@Test
	void testClientCreation_failure_emptycountry() {
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			Client client2=new Client("Ashhar","ashh@gmail.com","2020-2-2","","232","adhaar","208979677");});
		assertEquals("country cannot be empty",e.getMessage());
		
	}
	@Test
	void testClientCreation_failure_emptyemail() {
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			Client client2=new Client("Ashhar",null,"2020-2-2","","22","adhaar","208979677");});
		assertEquals("email cannot be empty",e.getMessage());
		
	}
	@Test
	void testClientCreation_failure_nulldate() {
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			Client client2=new Client("Ashhar","ashh@gmail.com",null,"india","22","adhaar","208979677");});
		assertEquals("date cannot be null",e.getMessage());
		
	}
	@Test
	void testClientCreation_failure_nullid() {
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			Client client2=new Client("Ashhar","@gmail.com","2020-2-2","India","222",null,null);});
		assertEquals("id cannot be null",e.getMessage());
		
	}
	


}
