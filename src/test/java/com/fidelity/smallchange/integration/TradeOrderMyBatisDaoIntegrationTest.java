package com.fidelity.smallchange.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.fidelity.smallchange.models.Order;
import com.fidelity.smallchange.models.Trade;



@SpringBootTest
@Transactional
class TradeOrderMyBatisDaoIntegrationTest {
	
	@Autowired
	private TradeOrderDao dao;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static List<Trade> tListExpected=Arrays.asList(
			new Trade("Q123",3,new BigDecimal("1200.76"),	"B","C101",	"PQR",	"a62375d7-bcb4-46a1-b2f0-5ba6719ae9b5",	new BigDecimal("1200"),new BigDecimal(1000.76),LocalDateTime.of(2022, 2, 9,7,0,0)),
					new Trade("Q123",	3,new BigDecimal("1200.76")	,"S","C101","rst","a62375d7-bcb4-46a1-b2f0-5ba6719ae987",new BigDecimal("1200"),new BigDecimal(1200.76),LocalDateTime.of(2022, 2, 11,7,0,0))
	);
	
	
	@Test
	void TestInsertTrade() {
		
		
        assertEquals(0, 
    			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_trade", "trade_id = 'a77777d7-bcb4-46a1-b2f0-5ba6719ae9b5'"));
        Order newOrder=new Order("Q123",3,new BigDecimal(1000.76),"B","C101","xyz", LocalDateTime.of(2022, 2, 11,7,0,0));
		dao.insertOrder(newOrder);
		Trade newTrade=new Trade("Q123",3,new BigDecimal(31200.76),	"B","C101",newOrder.getOrderId(),
				"a77777d7-bcb4-46a1-b2f0-5ba6719ae9b5",new BigDecimal(1200));
		
		int rows = dao.insertTrade(newTrade);
		
		
		assertThat(rows, is(equalTo(1)));
		
		
		assertEquals(1, 
			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_trade", "trade_id = 'a77777d7-bcb4-46a1-b2f0-5ba6719ae9b5'"));
	
		 
	}
	@Test
	void TestInsertOrder() {
		
		
        assertEquals(0, 
    			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_order", "order_id = 'ijk'"));


		Order newOrder=new Order("Q123",3,new BigDecimal(1000.76),"B","C101","ijk", LocalDateTime.of(2022, 2, 11,7,0,0));
		int rows = dao.insertOrder(newOrder);
		
		
		assertThat(rows, is(equalTo(1)));
		
		
		assertEquals(1, 
			JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "sc_order", "order_id = 'ijk'"));
	
		 
	}
	@Test
	void TestQueryTradeByClientId(){
		//trade history
		
		List<Trade> tListActual=dao.queryTradeByClientId("C101");
		assertNotNull(tListActual);
		assertTrue(tListActual.contains(tListExpected.get(0)));
		 
	}
	@Test
	void TestQueryTradeByTradeId(){
		
		
		List<Trade> tListActual=dao.queryTradeByTradeId("a62375d7-bcb4-46a1-b2f0-5ba6719ae9b5");
		assertNotNull(tListActual);
		assertThat(tListActual.get(0), is(tListExpected.get(0)));
		 
	}
	@Test
	void TestQueryTradeByOrderId(){
		
		List<Trade> tListActual=dao.queryTradeByOrderId("PQR");
		assertNotNull(tListActual);
		assertThat(tListActual.get(0), is(tListExpected.get(0)));
		 
	}

}
