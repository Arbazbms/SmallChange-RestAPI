package com.fidelity.smallchange.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.smallchange.models.ClientRegisterationValidation;
import com.fidelity.smallchange.models.Login;
import com.fidelity.smallchange.models.Portfolio;
import com.fidelity.smallchange.models.PortfolioItem;

class PortfolioTest {

	Portfolio myportfolio;
	PortfolioItem pi1;
	
	@BeforeEach
	void init()
	{
		myportfolio=new Portfolio("ashhar123");
		pi1=new PortfolioItem ("C101","item21","instrument1","trade31",1000);
	}
	@Test
	void testPortfolioCreation() {
		assertNotNull(myportfolio);
	}

	@Test
	void testAddingItem_success() {
		myportfolio.addToPortfolio(pi1);
		assertEquals(pi1,myportfolio.getPortfolioItembyId(pi1.getPortfolio_item_id()));
	}
	@Test
	void testRemovingItem_success() {
		myportfolio.addToPortfolio(pi1);
		myportfolio.removeAllFromPortfolio(pi1.getPortfolio_item_id());
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			myportfolio.getPortfolioItembyId(pi1.getPortfolio_item_id());});
		assertEquals("No matching item found for id provided",e.getMessage());
		
	}
	@Test
	void testAddingItem_FailureNullItem() {
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			myportfolio.addToPortfolio(null);});
		assertEquals("null reference passed",e.getMessage());
	}
	@Test
	void testRemovingItem_FailureNullId() {
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			myportfolio.removeAllFromPortfolio(null);});
		assertEquals("null or empty reference passed",e.getMessage());
	}
	@Test
	void testRemovingItem_FailureNoMatch() {
		Exception e= assertThrows(IllegalArgumentException.class,()->{
			myportfolio.removeAllFromPortfolio("ashhar");});
		assertEquals("no match found",e.getMessage());
	}
}
