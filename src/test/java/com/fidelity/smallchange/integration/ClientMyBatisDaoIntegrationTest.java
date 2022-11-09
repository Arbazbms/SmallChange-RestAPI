package com.fidelity.smallchange.integration;
import static org.junit.jupiter.api.Assertions.*;
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
import com.fidelity.smallchange.models.ClientIdentification;
import com.fidelity.smallchange.models.Login;



@SpringBootTest
@Transactional
public class ClientMyBatisDaoIntegrationTest {
	
	@Autowired
	private ClientDaoMyBatisImpl dao;

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private static List<Client> allClients = Arrays.asList(
//			new Client("C101","ashr@gmail.com",	LocalDate.of(2022,1,22),"India","678987","SSN","123456734"),
//			new Client("C109","ashr@gmail.com",	LocalDate.of(2022,1,22),"India","678987","SSN","123456789")
			new Client("C101","ashr@gmail.com","password123","2022-1-22","India","678987","SSN","123456734",""),
			new Client("C109","ashr@gmail.com","password123","2022-1-22"	,"India","678987","SSN","123456789","")
			
		);
	
	@Test
	void TestGetClientByID() {
		Client c1=new Client("1079467900","vishal@gamil.com","1111111","2020-03-27","India",	"600040",	"Adhaar","123123123456"	,"");
		Client c=dao.getClientByID("1079467900");
		//assertNotNull(c);
		assertEquals(c, c1);
	}
	@Test
	void TestInsertClient() {
        //String id="C277";
		// verify that Widget with id = 42 is NOT in the database
        assertEquals(0, 
    			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_client", "client_id = '1079467901'"));


		Client newClient = new Client("1079467901","vishal@gamil.com","1111111","2020-03-27","India","600040","");

		int rows = dao.insertClient(newClient);
		
		// verify that 1 row was inserted
		assertThat(rows, is(equalTo(1)));
		
		// verify that Widget with id = 42 IS in the database
		assertEquals(1, 
			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_client", "client_id = '1079467901'"));
	
	}
	@Test
	void TestInsertIdentification() {
		String id="C277";
		// verify that Widget with id = 42 is NOT in the database
		assertThat(0, is(equalTo(
			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_client_identification", "client_id ='1079467901' " ))));

		ClientIdentification newClientIdentification = new ClientIdentification("Adhaar","123123123456");
		Client newClient = new Client("1079467901","vishal@gamil.com","1111111","2020-03-27","India",	"600040","");
dao.insertClient(newClient);
		int rows = dao.insertIdentification("1079467901",newClientIdentification);
		
		// verify that 1 row was inserted
		assertThat(rows, is(equalTo(1)));
		
		// verify that Widget with id = 42 IS in the database
		assertThat(1, is(equalTo(
			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_client_identification", "client_id ='1079467901' " ))));
	}
	@Test
	void TestUpdateClient() {
         
		
		// load the original Widget from the database
		Client originalClient = loadClientFromDb("1079467900");
		
		// modify the local Widget
		originalClient.setPostal("600040");

		int rows = dao.updateClient(originalClient);
		
		// verify that 1 row was updated
		assertThat(rows, is(equalTo(1)));
		
		// reload widget from database 
		Client updatedClient = loadClientFromDb("1079467900");
		
		// verify that only the price was updated in the database
		assertThat(originalClient, is(equalTo(updatedClient)));
	}
	@Test
	void TestUpdateIdentification() {
		// load the original Widget from the database
		        ClientIdentification originalClientIdentification = loadClientIdentificationFromDb("1079467900");
				
				// modify the local Widget
		        originalClientIdentification.setValue("123123123456");

				int rows = dao.updateIdentification(originalClientIdentification,"1079467900");
				
				// verify that 1 row was updated
				assertThat(rows, is(equalTo(1)));
				
				// reload widget from database 
				ClientIdentification updatedClientIdentification = loadClientIdentificationFromDb("1079467900");
				
				// verify that only the price was updated in the database
				assertThat(originalClientIdentification, is(equalTo(updatedClientIdentification)));
	}
	
	
	
	private Client loadClientFromDb(String id) {
		String sql = "select * from sc_client where client_id = ?";
		
		return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> 
			new Client(rs.getString("client_id"),rs.getString("email"),rs.getString("password"),rs.getString("date_of_birth") ,rs.getString("country"),rs.getString("postal_code"),rs.getString("token")),new Object[]{id});
	}
	
	private ClientIdentification loadClientIdentificationFromDb(String id) {
		String sql = "select * from sc_client_identification where client_id = ?";
		
		return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> 
			new ClientIdentification(rs.getString("type"), rs.getString("value")),new Object[]{id});
	}
	
}
