package com.fidelity.smallchange.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fidelity.smallchange.models.Order;
import com.fidelity.smallchange.models.Trade;
import com.fidelity.smallchange.service.TradeOrderService;

import org.junit.jupiter.api.Test;

@AutoConfigureMybatis
@WebMvcTest(controllers=TradeOrderController.class)
class TradeOrderServiceWebLayerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	TradeOrderService service;
	
	static List<Trade> tListExpected;
	
	@BeforeAll
	public static void init() {
	   tListExpected=Arrays.asList(
			new Trade("Q123",3,new BigDecimal("1200.76"),	"B","C101",	"PQR",	"a62375d7-bcb4-46a1-b2f0-5ba6719ae9b5",	new BigDecimal("1200"),new BigDecimal(1000.76),"09-FEB-22 07.00.00.000000000 AM"),
			new Trade("Q123",	3,new BigDecimal("1200.76")	,"S","C101","rst","a62375d7-bcb4-46a1-b2f0-5ba6719ae987",new BigDecimal("1200"),new BigDecimal(1200.76),"11-FEB-22 07.00.00.000000000 AM")
			   );
	}
	
	@Test
	public void testqueryTradeByClientId() throws Exception {
		String id = "C101";
		
		when(service.queryTradeByClientId(id)).thenReturn(tListExpected);
		
		mockMvc.perform(get("/trades/tradeClient/C101"))
			   .andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$[0].orderId").value("PQR"));		
	}
	@Test
	public void testQueryTradeByClientId_DaoReturnsEmptyList() throws Exception {
		String id = "C111";
		when(service.queryTradeByClientId(id)).thenReturn(new ArrayList<Trade>());
		
		mockMvc.perform(get("/trades/tradeClient/C111"))
			   .andDo(print())
			   .andExpect(status().isNoContent())
			   .andExpect(content().string(is(emptyOrNullString())));
	}
	@Test
	public void testQueryTradeByClientId_DaoThrowsException() throws Exception {
		
		String id = "C111";
		when(service.queryTradeByClientId(id)).thenThrow(new RuntimeException());
		
		mockMvc.perform(get("/trades/tradeClient/C111"))
			   .andDo(print())
			   .andExpect(status().is5xxServerError())
			   .andExpect(content().string(is(emptyOrNullString())));
	}
	
	@Test
	public void testInsertOrder() throws Exception {
		
		Order newOrder=new Order("Q123",3,new BigDecimal(1000.76),"B","C101","ijk","2022/02/11T07:00:00");
		
		when(service.insertOrder(newOrder)).thenReturn(1);
		
		// Creating the ObjectMapper object
		ObjectMapper mapper = new ObjectMapper();
		// Converting the Object to JSONString
		String jsonString = mapper.writeValueAsString(newOrder);
		
		mockMvc.perform(post("/trades/order")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(jsonString))
			   .andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.rowCount").value(1));
	}
	
	@Test
	public void testInsertTrade() throws Exception {
		
		Trade newTrade=new Trade("Q123",3,new BigDecimal(31200.76),	"B","C101","ijk",
				"a77777d7-bcb4-46a1-b2f0-5ba6719ae9b5",new BigDecimal(1200));
		
		when(service.insertTrade(newTrade)).thenReturn(1);
		
		// Creating the ObjectMapper object
		ObjectMapper mapper = new ObjectMapper();
		// Converting the Object to JSONString
		String jsonString = mapper.writeValueAsString(newTrade);
		
		mockMvc.perform(post("/trades/trade")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(jsonString))
			   .andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.rowCount").value(1));
	}
	
	@Test
	public void testqueryTradeByOrderId() throws Exception {
		String id = "PQR";
		
		when(service.queryTradeByOrderId(id)).thenReturn(tListExpected);
		
		mockMvc.perform(get("/trades?orderId=PQR"))
			   .andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$[0].orderId").value("PQR"));		
	}
	@Test
	public void testQueryTradeByOrderId_DaoReturnsEmptyList() throws Exception {
		String id = "PRR";
		when(service.queryTradeByOrderId(id)).thenReturn(new ArrayList<Trade>());
		
		mockMvc.perform(get("/trades?orderId=PRR"))
			   .andDo(print())
			   .andExpect(status().isNoContent())
			   .andExpect(content().string(is(emptyOrNullString())));
	}
	@Test
	public void testQueryTradeByOrderId_DaoThrowsException() throws Exception {
		
		String id = "PRR";
		when(service.queryTradeByOrderId(id)).thenThrow(new RuntimeException());
		
		mockMvc.perform(get("/trades?orderId=PRR"))
			   .andDo(print())
			   .andExpect(status().is5xxServerError())
			   .andExpect(content().string(is(emptyOrNullString())));
	}

}
