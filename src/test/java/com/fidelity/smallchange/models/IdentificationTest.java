package com.fidelity.smallchange.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.Identification;
import com.fidelity.smallchange.models.Identification;

class IdentificationTest {

	private Identification identification;
	@BeforeEach
	void init() {
		identification=new Identification("Adhaar","2097642357958");
	}
	@Test
	void testIdentityCreation_success() {
		assertNotNull(identification);
		
	}
	@Test
	void testIdentityCreation_failureEmptyType() {
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			Identification identification=new Identification("","2097642357958");});
		assertEquals("Valid identification not provided",e.getMessage());
		}
	@Test
	void testIdentityCreation_failureEmptyValue() {
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			Identification identification=new Identification("Adhaar","");});
		assertEquals("Valid identification not provided",e.getMessage());
		}
			
}
