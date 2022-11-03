package com.fidelity.smallchange.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.Identification;
import com.fidelity.smallchange.models.Login;



@SpringBootTest
@Transactional
public class ClientMyBatisDaoIntegrationTest {
	@Autowired
	private ClientDao dao;

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private static List<Client> allClients = Arrays.asList(
			new Client("C101","ashr@gmail.com"," ",	LocalDate.of(2022,1,22),"India","678987",new Identification("SSN","123456734")),
			new Client("C109","ashr@gmail.com"," ",	LocalDate.of(2022,1,22),"India","678987",new Identification("SSN","123456789"))
			
		);
	
	@Test
	void TestGetClientByID() {
		Client c=dao.getClientByID("C101");
		assertThat(c, is(equalTo(allClients.get(0))));
	}
	//@Test
	void TestInsertClient() {
        String id="C277";
		// verify that Widget with id = 42 is NOT in the database
		assertThat(0, is(equalTo(
			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_client", "client_id = " + id))));

		Client newClient = new Client(id,"ranj@gmail.com"," ",	LocalDate.of(2000,10,27),"India","57004",new Identification("SSN","123456000"));

		int rows = dao.insertClient(new Login("ranj@gmail.com"," "),newClient);
		
		// verify that 1 row was inserted
		assertThat(rows, is(equalTo(1)));
		
		// verify that Widget with id = 42 IS in the database
		assertThat(1, is(equalTo(
			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_client", "client_id = " + id))));
	
	}
	//@Test
	void TestInsertIdentification() {
		String id="C277";
		// verify that Widget with id = 42 is NOT in the database
		assertThat(0, is(equalTo(
			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_client_identification", "client_id = " + id))));

		Identification newClientIdentification = new Identification("SSN","123456001");

		int rows = dao.insertIdentification("C277",newClientIdentification);
		
		// verify that 1 row was inserted
		assertThat(rows, is(equalTo(1)));
		
		// verify that Widget with id = 42 IS in the database
		assertThat(1, is(equalTo(
			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_client_identification", "client_id = " + id))));
	}
	//@Test
	void TestUpdateClient() {
         
		
		// load the original Widget from the database
		Client originalClient = loadClientFromDb("C101");
		
		// modify the local Widget
		originalClient.setPostal("570008");

		int rows = dao.updateClient(new Login("ashr@gmail.com"," "),originalClient);
		
		// verify that 1 row was updated
		assertThat(rows, is(equalTo(1)));
		
		// reload widget from database 
		Client updatedClient = loadClientFromDb("C101");
		
		// verify that only the price was updated in the database
		assertThat(originalClient, is(equalTo(updatedClient)));
	}
	//@Test
	void TestUpdateIdentification() {
		// load the original Widget from the database
		        Identification originalClientIdentification = loadClientIdentificationFromDb("C101");
				
				// modify the local Widget
		        originalClientIdentification.setValue("123456711");

				int rows = dao.updateIdentification(originalClientIdentification,"C101");
				
				// verify that 1 row was updated
				assertThat(rows, is(equalTo(1)));
				
				// reload widget from database 
				Identification updatedClientIdentification = loadClientIdentificationFromDb("C101");
				
				// verify that only the price was updated in the database
				assertThat(originalClientIdentification, is(equalTo(updatedClientIdentification)));
	}
	
	
	
	private Client loadClientFromDb(String id) {
		String sql = "select * from sc_client where client_id = " + id;
		
		return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> 
			new Client(rs.getString("client_id"),rs.getString("email"),"",rs.getDate("date_of_birth").toLocalDate() ,rs.getString("country"),rs.getString("postal_code"),this.loadClientIdentificationFromDb(id)));
	}
	
	private Identification loadClientIdentificationFromDb(String id) {
		String sql = "select * from sc_client_identification where client_id = " + id;
		
		return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> 
			new Identification(rs.getString("type"), rs.getString("value")));
	}
	
}
