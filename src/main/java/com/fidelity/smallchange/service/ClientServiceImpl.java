package com.fidelity.smallchange.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fidelity.smallchange.integration.ClientDao;
import com.fidelity.smallchange.integration.ClientDaoMyBatisImpl;
import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.ClientFmtsView;
import com.fidelity.smallchange.models.ClientIdentification;
import com.fidelity.smallchange.models.Login;



@Service
@Transactional
public class ClientServiceImpl {

	@Autowired
	private ClientDaoMyBatisImpl dao;
	@Autowired
	Logger log;
	
	
	public Client getClientByID(String clientId) {
		Client client=null;
		try {
			client = dao.getClientByID(clientId);
		} catch (Exception e) {
			log.error("error msg from getclientbyid "+e);
			String msg = String.format("Error querying For client with id = %d ", clientId);
			throw new DatabaseException(msg, e);
		}
		return client;
	}

//	@Override
	public int insertClient(Client client) {
		int count = 0;
		try {
			count = dao.insertClient(client);
			dao.insertIdentification(client.getClientId(), client.getId().get(0));
		} catch (Exception e) {
			String msg = "Error inserting client";
			throw new DatabaseException(msg, e);
		}
		return count;
	}

//	@Override
//	public int insertIdentification(String client_id, ClientIdentification clientIdentification) {
//		
//		int count = 0;
//		try {
//			count = dao.insertIdentification(client_id,clientIdentification);
//		} catch (Exception e) {
//			String msg = "Error inserting clientIdentification";
//			throw new DatabaseException(msg, e);
//		}
//		return count;
//	}

//	@Override
	public int updateClient(Client client) {
		
		int count = 0;
		try {
			count = dao.updateClient(client);
			dao.updateIdentification(client.getId().get(0), client.getClientId());
		} catch (Exception e) {
			String msg = "Error updating client";
			throw new DatabaseException(msg, e);
		}
		return count;
	}

//	@Override
	public int updateIdentification(List<ClientIdentification> clientIdentification, String client_id) {
		
		int count = 0;
		try {
			count = dao.updateIdentification(clientIdentification.get(0),client_id);
		} catch (Exception e) {
			String msg = "Error updating clientIdentification";
			throw new DatabaseException(msg, e);
		}
		return count;
	}
	
	
//	@Override
	public Client registerClient(Client new_client) throws JsonProcessingException {
		Client client;
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
//	    headers.setAccept((List<MediaType>) MediaType.APPLICATION_JSON);
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
	    //convert java object to JSON
	    String json=mapper.writeValueAsString(new_client);
	    HttpEntity<String> request = 
	    	      new HttpEntity<String>(json, headers);
		RestTemplate template =new RestTemplate();
		ResponseEntity<String> fmts_response=template.postForEntity("http://localhost:3000/fmts/client",request, String.class);
		if(fmts_response.hasBody())
		{	
			 client = mapper.readValue(fmts_response.getBody(), Client.class);
			System.out.println(fmts_response);
//			 client=fmts_response.getBody();
			new_client.setClientId(client.getClientId());
			new_client.setToken(client.getToken());
			System.out.println(new_client);
			dao.insertClient(new_client);
			return new_client;
		}
		else
		{
			throw new RuntimeException();
		}
//			
	}
	

}
