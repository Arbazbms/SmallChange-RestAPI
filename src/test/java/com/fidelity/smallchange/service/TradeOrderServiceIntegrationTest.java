package com.fidelity.smallchange.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.smallchange.models.Order;
import com.fidelity.smallchange.models.Trade;

import org.junit.jupiter.api.Test;

@SpringBootTest
@Transactional
class TradeOrderServiceIntegrationTest {

	@Autowired
	TradeOrderService service;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    private static List<Trade> tListExpected=Arrays.asList(
			new Trade("Q123",3,new BigDecimal("1200.76"),	"B","C101",	"PQR",	"a62375d7-bcb4-46a1-b2f0-5ba6719ae9b5",	new BigDecimal("1200"),new BigDecimal(1000.76),"09-FEB-22 07.00.00.000000000 AM"),
			new Trade("Q123",	3,new BigDecimal("1200.76")	,"S","C101","rst","a62375d7-bcb4-46a1-b2f0-5ba6719ae987",new BigDecimal("1200"),new BigDecimal(1200.76),"11-FEB-22 07.00.00.000000000 AM")
			   );
	
	
	@Test
	void basicSanityTest() {
		assertNotNull(service);
	}

	@Test
	void testFindTradeByOrderId() {
		String id = "PQR";
		
		
		List<Trade> orderActual = service.queryTradeByOrderId(id);
		
		// verify the Widget
		assertThat(tListExpected.get(0), is(orderActual.get(0)));
	}
	
	@Test
	void testFindTradeByClientId() {
		String id = "C101";
		
		List<Trade> tradeActual = service.queryTradeByClientId(id);
		
		// verify the Widget
		assertThat(tListExpected, is(tradeActual));
	}
	@Test
	void testInsertTrade() {
		  assertEquals(0, 
	    			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_trade", "trade_id = 'a77777d7-bcb4-46a1-b2f0-5ba6719ae9b5'"));
	        
		Trade newTrade=new Trade("Q123",3,new BigDecimal(31200.76),	"B","C101","ijk",
				"a77777d7-bcb4-46a1-b2f0-5ba6719ae9b5",new BigDecimal(1200));
		
		int rows = service.insertTrade(newTrade);
		
		// verify that 1 row was inserted		
		assertThat(rows, is(equalTo(1)));
		
		assertEquals(1, 
				JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_trade", "trade_id = 'a77777d7-bcb4-46a1-b2f0-5ba6719ae9b5'"));
		
	}
	@Test
	void testInsertOrder() {
		String id = "ijk";
		
		assertEquals(0, 
    			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_order", "order_id = 'ijk'"));

		Order newOrder=new Order("Q123",3,new BigDecimal(1000.76),"B","C101","ijk","2022/02/11T07:00:00");
		
		int rows = service.insertOrder(newOrder);
		
		// verify that 1 row was inserted		
		assertThat(rows, is(equalTo(1)));
		
		assertEquals(1, 
				JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_order", "order_id = 'ijk'"));
		
	}
}
