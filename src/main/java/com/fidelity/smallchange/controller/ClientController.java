package com.fidelity.smallchange.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;

import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.Login;
import com.fidelity.smallchange.models.Preference;
import com.fidelity.smallchange.service.ClientServiceImpl;
import com.fidelity.smallchange.service.DatabaseRequestResult;
import com.fidelity.smallchange.service.PortfolioBusinessService;


@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	Logger logger;

	@Autowired
	ClientServiceImpl service;
	
	private static final String DB_ERROR_MSG = 
			"Error communicating with the Smallchange database";
	
	@PostMapping(value="/register",
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> registerClient(@RequestBody Client client) {
		
	logger.debug(client.toString());	
	logger.warn(client.getClientId(), "kjkjkj");
	try {
		client =service.registerClient(client);
	} 
	catch (Exception e) {
		throw new ServerErrorException(DB_ERROR_MSG, e);
	}
	if (client.getClientId()=="") {
		logger.debug(client.toString());	
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}
	ResponseEntity<Client> res=ResponseEntity.ok(client);
	return res;
}
	
	
	
	@PostMapping(value="/login",
			 produces=MediaType.APPLICATION_JSON_VALUE,
			 consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> clientLogin(@RequestBody Login credentials) {
		
//	logger.warn(client.getClientId(), "kjkjkj");
	logger.debug(credentials.toString());
	Client client;
	try {
		logger.debug("try");
		client =service.clientLogin(credentials);
	} 
	catch (Exception e) {
		throw new ServerErrorException(DB_ERROR_MSG, e);
	}
	if (client.getClientId()==""|| client.getToken()==""||client.getToken()==null) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}
	ResponseEntity<Client> res=ResponseEntity.ok(client);
	return res;
}

}
