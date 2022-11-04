package com.fidelity.smallchange.controller;


import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.server.ServerWebInputException;

import com.fidelity.smallchange.integration.PreferenceDao;
import com.fidelity.smallchange.integration.PreferenceDao;
import com.fidelity.smallchange.models.Preference;


@RestController
@RequestMapping("/api")
public class PreferenceController {

	@Autowired
	private Logger logger;
	
	@Autowired
	private PreferenceDao dao;
	

	/**
	 * Endpoint: /api/{id}
	 * 
	 * @return a single preference object based on id
	 */
	@GetMapping("{id}")
	public ResponseEntity<Preference> queryForPreferneceById(@PathVariable String id) {
		logger.debug("getting Preference by ID" + id);

		try {
			Preference preference = dao.getPreference(id);
			
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
	
}
