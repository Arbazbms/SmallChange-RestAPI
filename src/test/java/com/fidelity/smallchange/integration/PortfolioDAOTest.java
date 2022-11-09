package com.fidelity.smallchange.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.smallchange.models.PortfolioItem;


@Transactional
@SpringBootTest
class PortfolioDAOTest {

	@Autowired
	private	PortfolioDAOMyBatisImp dao;
	
	private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	private List<PortfolioItem> allPortfolioItemsOnly = Arrays.asList(
		new PortfolioItem("C101", "item21","instrument1","trade21",new BigDecimal(100.00),1000)
//		new PortfolioItem("C101", "item22","instrument2","trade32",2000),
//		new PortfolioItem("C101", "item23","instrument3","trade33",2000),
//		new PortfolioItem("C101", "item24","instrument2","trade34",2000)
	);
	@Test
	void testGetAllPortfolioItems() {
		List<PortfolioItem> PortfolioItems = dao.getPortfolio("C101");
		// getAllPortfolioItems() doesn't select Employees, so all PortfolioItems employees lists are null
		assertEquals(1, PortfolioItems.size());
	}
	@Test
	void testInsertPortfolio() {
		// ARRANGE
		var expectedRowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "sc_client_portfolio") + 1;
		var newitem = new PortfolioItem("C101", "item23","instrument1","a62375d7-bcb4-46a1-b2f0-5ba6719ae9b5",new BigDecimal(100.00),1000);

		// ACT
		boolean success = dao.addToPortfolio(newitem);

		// ASSERT
		assertTrue(success);

		assertEquals(expectedRowCount, JdbcTestUtils.countRowsInTable(jdbcTemplate, "sc_client_portfolio"));
//		Map<String, Object> itemFromDb = jdbcTemplate.queryForMap(
//			"select client_id, portfolio_item_id, instrument_id,trade_id from sc_client_portfolio where client_id = " + newitem.getClient_id()
//		);
		// Next, create a Map of the new row's expected column values.
//		Map<String, Object> expecteditem = Map.of(
//			"client_id", newitem.getClient_id(),
//			"portfolio_item_id", newitem.getPortfolio_item_id(),
//			"instrument_id", newitem.getInstrument_id(),
//			"trade_id",newitem.getQuantity()
//		);
//		assertTrue(DbTestUtils.mapsAreEqual(expecteditem, itemFromDb));
	}
	@Test
	void testUpdatePortfolioItem() {
		// ARRANGE
		var expected = JdbcTestUtils.countRowsInTable(jdbcTemplate, "sc_client_portfolio");
		var updateitem = new PortfolioItem("C101", "item22","instrument1","trade21",new BigDecimal(100.00),1);

		var success = dao.updatePortfolioItem(updateitem);
		
		// ASSERT
		assertTrue(success);
		// verify the number of PortfolioItem records has not changed
		assertEquals(expected, JdbcTestUtils.countRowsInTable(jdbcTemplate, "SC_Client_portfolio"));
		// verify all PortfolioItem properties were updated correctly
	}

	@Test
	void testDeletePortfolioItem() {
		List<PortfolioItem> PortfolioItems = dao.getPortfolio("C101");
		int expected = PortfolioItems.size() - 1;

		assertTrue(dao.removeFromPortfolio("item22"));
		
		PortfolioItems = dao.getPortfolio("C101");
		assertEquals(expected, PortfolioItems.size());
	}
}
