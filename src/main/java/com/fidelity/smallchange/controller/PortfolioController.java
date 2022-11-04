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

import com.fidelity.smallchange.models.PortfolioItem;
import com.fidelity.smallchange.models.Preference;
import com.fidelity.smallchange.service.PortfolioBusinessService;


@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

	@Autowired
	Logger logger;

	@Autowired
	PortfolioBusinessService service;

	@GetMapping("{id}")
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


}