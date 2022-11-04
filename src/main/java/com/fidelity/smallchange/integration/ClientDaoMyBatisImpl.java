package com.fidelity.smallchange.integration;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.smallchange.integration.mapper.ClientMapper;
import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.Identification;
import com.fidelity.smallchange.models.Login;



@Repository("clientDao")
public class ClientDaoMyBatisImpl implements ClientDao {
	@Autowired
	private ClientMapper mapper;
	
	@Autowired
	Logger log;
	
	@Override
	public Client getClientByID(String clientId) {
		log.debug("inside getclientid");
		Client c=mapper.getClientByID(clientId);
		return c;
	}

	@Override
	public int insertClient(Client client) {
		return mapper.insertClient(client);
	}

	@Override
	public int insertIdentification(String client_id, Identification clientIdentification) {
		return mapper.insertIdentification(client_id, clientIdentification);
	}

	@Override
	public int updateClient(Client client) {
		return mapper.updateClient(client);
	}

	@Override
	public int updateIdentification(Identification clientIdentification, String client_id) {
		return mapper.updateIdentification(clientIdentification, client_id);
	}

	
}
