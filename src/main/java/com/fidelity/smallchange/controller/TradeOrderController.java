package com.fidelity.smallchange.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.fidelity.smallchange.models.Order;
import com.fidelity.smallchange.models.Trade;
import com.fidelity.smallchange.service.DatabaseRequestResult;
import com.fidelity.smallchange.service.TradeOrderService;

/*POST http://localhost:8080/trades/trade
 *POST http://localhost:8080/trades/order
 *GET  http://localhost:8080/trades/tradeClient/C101
 *GET  http://localhost:8080/trades/trade/a62375d7-bcb4-46a1-b2f0-5ba6719ae9b5
 *GET  http://localhost:8080/trades?orderId=PQR
*/
@RestController
@RequestMapping("/trades")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TradeOrderController {
	@Autowired
	private Logger logger;
	
	@Autowired
	private TradeOrderService service;
	
	private static final String DB_ERROR_MSG = 
			"Error communicating with the Smallchange database";

	
	@PostMapping(value="/trade",
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 consumes=MediaType.APPLICATION_JSON_VALUE)
	public DatabaseRequestResult insertTrade(@RequestBody Trade t) {
		logger.debug("inside POSt trade", t.getTradeId());
		int count = 0;
		try {
			count = service.insertTrade(t);
		} 
		catch (Exception e) {
			
			
			throw new ServerErrorException(DB_ERROR_MSG, e);
		}
		if (count == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return new DatabaseRequestResult(count);
	}
	
	@PostMapping(value="/order",
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 consumes=MediaType.APPLICATION_JSON_VALUE)
	public DatabaseRequestResult insertOrder(@RequestBody Order order) {
		logger.debug("inside post order", order.getOrderId());
		int count = 0;
		try {
			count = service.insertOrder(order);
		} 
		catch (Exception e) {
			throw new ServerErrorException(DB_ERROR_MSG, e);
		}
		if (count == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return new DatabaseRequestResult(count);
	}
	
	@PostMapping(value="/orderAndTrade",
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Trade>> insertOrderAndTrade(@RequestBody ObjectNode objectNode) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper()
				   .registerModule(new ParameterNamesModule())
				   .registerModule(new Jdk8Module())
				   .registerModule(new JavaTimeModule()); 
		Order o=objectMapper.treeToValue(objectNode.get("order"),Order.class);
		Trade t=objectMapper.treeToValue(objectNode.get("trade"),Trade.class);
		logger.debug("inside POSt order",o.getOrderId());
		
		try {
			List<Trade> tListActual = service.insertOrderAndTrade(o,t);
			ResponseEntity<List<Trade>> responseEntity;
			if (tListActual.size() >0) {
				
				responseEntity = ResponseEntity.ok(tListActual); 
			}
			else {
				// If there is no preference with the given id, the response should have an empty
				// body and HTTP status 204
				responseEntity = ResponseEntity.noContent().build();
			}
			return responseEntity;
		} 
		catch (Exception e) {
			logger.error("Exception while getting trade with orderID " + o.getOrderId() + ": " + e);
			throw new ServerErrorException("Backend issue", e);
		}
	}
	
	
	@GetMapping(value="/tradeClient/{clientId}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trade>> queryTradeByClientId(@PathVariable String clientId) {
		logger.debug("getting trade with client ID" + clientId);

		try {
			List<Trade> tListActual = service.queryTradeByClientId(clientId);
			
			ResponseEntity<List<Trade>> responseEntity;
			
			if (tListActual.size() >0) {
		
				responseEntity = ResponseEntity.ok(tListActual); 
			}
			else {
				// If there is no preference with the given id, the response should have an empty
				// body and HTTP status 204
				responseEntity = ResponseEntity.noContent().build();
			}
			return responseEntity;
		} 
		catch (Exception e) {
			logger.error("Exception while getting trade with clientID " + clientId + ": " + e);
			throw new ServerErrorException("Backend issue", e);
		}
	}
	
	@GetMapping(value="/trade/{tradeId}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trade>> queryTradeByTradeId(@PathVariable String tradeId) {
		logger.debug("getting trade with tradeId" + tradeId);

		try {
			List<Trade> tListActual = service.queryTradeByTradeId(tradeId);
			
			ResponseEntity<List<Trade>> responseEntity;
			
			if (tListActual.size() >0) {
		
				responseEntity = ResponseEntity.ok(tListActual); 
			}
			else {
				// If there is no preference with the given id, the response should have an empty
				// body and HTTP status 204
				responseEntity = ResponseEntity.noContent().build();
			}
			return responseEntity;
		} 
		catch (Exception e) {
			logger.error("Exception while getting trade with tradeID " + tradeId + ": " + e);
			throw new ServerErrorException("Backend issue", e);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Trade>> queryTradeByOrderId(@RequestParam(value="orderId")String orderId) {
		logger.debug("getting trade with orderId " + orderId);

		try {
			List<Trade> tListActual = service.queryTradeByOrderId(orderId);
			
			ResponseEntity<List<Trade>> responseEntity;
			
			if (tListActual.size() >0) {
		
				responseEntity = ResponseEntity.ok(tListActual); 
			}
			else {
				// If there is no preference with the given id, the response should have an empty
				// body and HTTP status 204
				responseEntity = ResponseEntity.noContent().build();
			}
			return responseEntity;
		} 
		catch (Exception e) {
			logger.error("Exception while getting trade with orderID " + orderId + ": " + e);
			throw new ServerErrorException("Backend issue", e);
		}
	}
	
}
