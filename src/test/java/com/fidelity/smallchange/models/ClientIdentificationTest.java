package com.fidelity.smallchange.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.ClientIdentification;
import com.fidelity.smallchange.models.ClientIdentification;

class ClientIdentificationTest {

	private ClientIdentification clientIdentification;
	@BeforeEach
	void init() {
		clientIdentification=new ClientIdentification("Adhaar","2097642357958");
	}
	@Test
	void testIdentityCreation_success() {
		assertNotNull(clientIdentification);
		
	}
	@Test
	void testIdentityCreation_failureEmptyType() {
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			ClientIdentification clientIdentification=new ClientIdentification("","2097642357958");});
		assertEquals("Valid identification not provided",e.getMessage());
		}
	@Test
	void testIdentityCreation_failureEmptyValue() {
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			ClientIdentification clientIdentification=new ClientIdentification("Adhaar","");});
		assertEquals("Valid identification not provided",e.getMessage());
		}
			
}
