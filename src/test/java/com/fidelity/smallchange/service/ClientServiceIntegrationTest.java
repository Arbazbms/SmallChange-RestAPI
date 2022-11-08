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
import com.fidelity.smallchange.models.Login;


@SpringBootTest
@Transactional
class ClientServiceIntegrationTest {
	
	Client client01=new Client("","ashhar@gmail.com","password123","2020-2-2","India","6656565","ssn","97654","hello");
	@Autowired
	ClientServiceImpl cs;		
	@Test
	void testRegister() {
		try {
			System.out.println(client01);
			assertEquals(1,cs.registerClient(client01));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testLogin() {
		try {
			System.out.println(client01);
			assertEquals(1,cs.clientLogin(new Login("asharrrg@gmil.com","hello123")));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
