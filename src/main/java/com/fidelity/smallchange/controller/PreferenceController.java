package com.fidelity.smallchange.controller;


import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.fidelity.smallchange.integration.PreferenceDao;
import com.fidelity.smallchange.integration.PreferenceDao;
import com.fidelity.smallchange.models.Preference;
import com.fidelity.smallchange.service.DatabaseRequestResult;
import com.fidelity.smallchange.service.PreferenceService;


@RestController
@RequestMapping("/api")
public class PreferenceController {

	@Autowired
	private Logger logger;
	
	@Autowired
	private PreferenceService service;
	
	private static final String DB_ERROR_MSG = 
			"Error communicating with the Smallchange database";

	/**
	 * Endpoint: /api/{id}
	 * 
	 * @return a single preference object based on id
	 */
	@GetMapping("{id}")
	public ResponseEntity<Preference> queryForPreferneceById(@PathVariable String id) {
		logger.debug("getting Preference by ID" + id);

		try {
			Preference preference = service.getPreferenceById(id);
			
			ResponseEntity<Preference> responseEntity;
			
			if (preference != null) {
		
				responseEntity = ResponseEntity.ok(preference); 
			}
			else {
				// If there is no preference with the given id, the response should have an empty
				// body and HTTP status 204
				responseEntity = ResponseEntity.noContent().build();
			}
			return responseEntity;
		} 
		catch (Exception e) {
			logger.error("Exception while getting preference by ID " + id + ": " + e);
			throw new ServerErrorException("Backend issue", e);
		}
	}
	
	
		@PostMapping(value="/preference",
				 produces=MediaType.APPLICATION_JSON_VALUE,
				 consumes=MediaType.APPLICATION_JSON_VALUE)
		public DatabaseRequestResult insertPreference(@RequestBody Preference p) {
			
		logger.debug("inside POSt", p.getLengthOfInvestment());
		logger.warn(p.getClientId(), "kjkjkj");
		int count = 0;
		try {
			count = service.insertPreference(p);
		} 
		catch (Exception e) {
			throw new ServerErrorException(DB_ERROR_MSG, e);
		}
		if (count == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return new DatabaseRequestResult(count);
	}
	
		
		@PutMapping(value="/preference",
				 produces=MediaType.APPLICATION_JSON_VALUE,
				 consumes=MediaType.APPLICATION_JSON_VALUE)
		public DatabaseRequestResult updatePreference(@RequestBody Preference p) {
			logger.debug("IN UPDATE STARTING", p.getIncomeCategory());
			logger.warn(p.getClientId(), "put client");
			
		int count = 0;
		try {
			count = service.updatePreference(p);
		} 
		catch (Exception e) {
			throw new ServerErrorException(DB_ERROR_MSG, e);
		}
		if (count == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return new DatabaseRequestResult(count);
	}
}
