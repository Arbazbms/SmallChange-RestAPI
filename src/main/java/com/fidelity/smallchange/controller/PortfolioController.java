package com.fidelity.smallchange.controller;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.server.ServerWebInputException;

import com.fidelity.smallchange.models.Portfolio;
import com.fidelity.smallchange.models.PortfolioItem;
import com.fidelity.smallchange.models.Preference;
import com.fidelity.smallchange.service.DatabaseRequestResult;
import com.fidelity.smallchange.service.PortfolioBusinessService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PortfolioController {

	@Autowired
	Logger logger;

	@Autowired
	PortfolioBusinessService service;
	
	private static final String DB_ERROR_MSG = 
			"Error communicating with the Smallchange database";


	@GetMapping("portfolio/{id}")
	public ResponseEntity<List<PortfolioItem>> queryForPreferneceById(@PathVariable String id) {
		logger.debug("getting Portfolio for client ID" + id);
//		 If the id in the request is less than or equal to zero, the response should have
//		 HTTP status 400
//		if (id <= 0) {
//			throw new ServerWebInputException("id must be greater than 0");
//		}
		try {
			List<PortfolioItem> portfolio = service.getPortfolio(id);

			ResponseEntity<List<PortfolioItem>> responseEntity;

			if (portfolio != null) {

				responseEntity = ResponseEntity.ok(portfolio); 
			}
			else {
				// If there is no preference with the given id, the response should have an empty
				// body and HTTP status 204
				responseEntity = ResponseEntity.noContent().build();
			}
			return responseEntity;
		} 
		catch (Exception e) {
			logger.error("Exception while getting portfolio for ID " + id + ": " + e);
			throw new ServerErrorException("Backend issue", e);
		}
	}

	@PostMapping(value="/portfolio",
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 consumes=MediaType.APPLICATION_JSON_VALUE)
	public boolean insertPreference(@RequestBody PortfolioItem p) {
		
	logger.debug("inside Post of portfolio add:", p.getPortfolio_item_id());

	boolean flag = false;
	try {
		flag = service.addToPortfolio(p);
	} 
	catch (Exception e) {
		logger.error("Exception while getting portfolio for ID " + ": " + e);
	}
	if (!flag) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}
	return flag;
}

	@PutMapping(value="/portfolio",
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 consumes=MediaType.APPLICATION_JSON_VALUE)
	public boolean updatePreference(@RequestBody PortfolioItem p) {
		logger.debug("IN PORTFOLIO UPDATE STARTING", p.getPortfolio_item_id());
		boolean flag = false;
	try {
		flag = service.updatePortfolioItem(p);
	} 
	catch (Exception e) {
		throw new ServerErrorException(DB_ERROR_MSG, e);
	}
	if (!flag) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}
	return flag;
}
	@DeleteMapping(value="/portfolio/{id}")
	public boolean deletePreference(@PathVariable String id) {
		logger.debug("IN PORTFOLIO delete operation STARTING",id);
		boolean flag = false;
	try {
		flag = service.removeFromPortfolio(id);
	} 
	catch (Exception e) {
		throw new ServerErrorException(DB_ERROR_MSG, e);
	}
	if (!flag) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}
	return flag;
}
}