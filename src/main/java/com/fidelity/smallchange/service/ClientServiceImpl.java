package com.fidelity.smallchange.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fidelity.smallchange.integration.ClientDao;
import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.ClientFmtsView;
import com.fidelity.smallchange.models.ClientIdentification;
import com.fidelity.smallchange.models.Login;



@Service
@Transactional
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientDao dao;
	@Autowired
	Logger log;
	
	@Override
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

	@Override
	public int insertClient(Client client) {
		int count = 0;
		try {
			count = dao.insertClient(client);
			dao.insertIdentification(client.getClientId(), client.getId());
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

	@Override
	public int updateClient(Client client) {
		
		int count = 0;
		try {
			count = dao.updateClient(client);
			dao.updateIdentification(client.getId(), client.getClientId());
		} catch (Exception e) {
			String msg = "Error updating client";
			throw new DatabaseException(msg, e);
		}
		return count;
	}

	@Override
	public int updateIdentification(ClientIdentification clientIdentification, String client_id) {
		
		int count = 0;
		try {
			count = dao.updateIdentification(clientIdentification,client_id);
		} catch (Exception e) {
			String msg = "Error updating clientIdentification";
			throw new DatabaseException(msg, e);
		}
		return count;
	}
	
	public boolean registerClient(Client new_client) {
		ClientFmtsView fmts_client=new ClientFmtsView(new_client);
		ClientFmtsView client=null;
		RestTemplate template =new RestTemplate();
		ResponseEntity<ClientFmtsView> fmts_response=template.postForEntity("localhost:3000/fmts/client", template, null);
		if(fmts_response.hasBody())
		{	
			client=fmts_response.getBody();
			new_client.setClientId(client.getClientId());
			return dao.insertClient(new_client)==1;
		}
		else
		{
			throw new RuntimeException();
		}
			
	}
	

}
