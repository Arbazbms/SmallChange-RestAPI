package com.fidelity.smallchange.models;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.smallchange.models.Login;
import com.fidelity.smallchange.models.PortfolioItem;

class PortfolioItemTest {

	private PortfolioItem pi;
	@BeforeEach
	void init()
	{
		pi=new PortfolioItem ("C101", "item21","instrument1","trade31",new BigDecimal(100.00),1000);
	}
	@Test
	void testPortfolioItemCreation() {
		assertNotNull(pi);
	}
	@Test
	void testPortfolioItemQuantityupdate() {
		pi.updateQuantity(10);
		assertEquals(10,pi.getQuantity());
	}

}
