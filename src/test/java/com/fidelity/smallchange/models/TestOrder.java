package com.fidelity.smallchange.models;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.smallchange.models.Order;
//import com.fidelity.models.SellTrade;
//import com.fidelity.models.BuyTrade;
import com.fidelity.smallchange.models.Trade;

class TestOrder {
	
	 private Order or;
	 private BigDecimal zeroTwoDps;
	 private Trade sell;
	 private Trade buy;
	 private List<Order> o;
	@BeforeEach
	void setUp() {
		zeroTwoDps = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_EVEN);
		or=new Order("12er",5,new BigDecimal("10000.00"),"S","34ty","56re",LocalDateTime.of(2022,02,21,11,30));
		o=new ArrayList<>();
		o.add(or);
//		sell=new SellTrade(new BigDecimal("1000.00"),o);
//		buy=new BuyTrade(new BigDecimal("1000.00"),o);
	}
    //test functionalities
//	  @Test
//	    void testExecuteTradeForSell() {
//		  assertEquals(sell,or.executeTrade(new BigDecimal("1000.00"),o));
//	  }
//	  @Test
//	    void testExecuteTradeForBuy() {
//		  Order or1=new Order("12er",new BigDecimal("5.00"),new BigDecimal("10000.00"),"B","34ty","56re",LocalDateTime.of(2022,02,21,11,30));
//		  assertEquals(buy,or1.executeTrade(new BigDecimal("1000.00"),o));
//	  }
	
	
	    @Test
	    void testNegativeQuantity() {
	    	Exception e = assertThrows(IllegalArgumentException.class, () -> {
	    		new Order("12er",-5,new BigDecimal("10000.00"),"S","34ty","56re",LocalDateTime.of(2022,02,21,11,30));
	    	});
	    	assertEquals("quantity must be greater than or equal to 0", e.getMessage());
	    }

	    @Test
	    void testNullTargetPrice() {
	    	Exception e = assertThrows(NullPointerException.class, () -> {
	    		new Order("12er",5,null,"S","34ty","56re",LocalDateTime.of(2022,02,21,11,30));
	    	});
	    	assertEquals("targetPrice can't be null", e.getMessage());
	    }


	    @Test
	    void testNegativeTargetPrice() {
	    	Exception e = assertThrows(IllegalArgumentException.class, () -> {
	    		new Order("12er",5,new BigDecimal("-10000.00"),"S","34ty","56re",LocalDateTime.of(2022,02,21,11,30));
	    	});
	    	assertEquals("targetPrice must be greater than or equal to 0", e.getMessage());
	    }
	

	//string exception
	@Test
	    void testNullDirection() {
	    	Exception e = assertThrows(NullPointerException.class, () -> {
	    		new Order("12er",5,new BigDecimal("10000.00"),null,"34ty","56re",LocalDateTime.of(2022,02,21,11,30));
	    	});
	    	assertEquals("direction can't be null", e.getMessage());
	    }

	    @Test
	    void testEmptyDirection() {
	    	Exception e = assertThrows(IllegalArgumentException.class, () -> {
	    		new Order("12er",5,new BigDecimal("10000.00"),"","34ty","56re",LocalDateTime.of(2022,02,21,11,30));
	    	});
	    	assertEquals("direction can't be empty", e.getMessage());
	    }



	//date exception
	@Test
	    void testNullDateTime() {
	    	Exception e = assertThrows(NullPointerException.class, () -> {
	    		new Order("12er",5,new BigDecimal("10000.00"),"S","34ty","56re",null);
	    	});
	    	assertEquals("dateTime can't be null", e.getMessage());
	    }

}
