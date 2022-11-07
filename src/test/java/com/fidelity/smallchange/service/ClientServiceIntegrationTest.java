package com.fidelity.smallchange.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.ClientIdentification;


@SpringBootTest
@Transactional
class ClientServiceIntegrationTest {
	
	Client client01=new Client("","ashhar@gmail.com",LocalDate.of(2020,10,10),"India","6656565","ssn","97654");
	@Autowired
	ClientServiceImpl cs;		
	@Test
	void test() {
		try {
			assertTrue(cs.registerClient(client01));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
