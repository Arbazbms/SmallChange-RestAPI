package com.fidelity.smallchange.integration;

import java.util.List;

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
	
	@Override
	public Client getClientByID(String clientId) {
		return mapper.getClientByID(clientId);
	}

	@Override
	public int insertClient(Login login, Client client) {
		return mapper.insertClient(login, client);
	}

	@Override
	public int insertIdentification(String client_id, Identification clientIdentification) {
		return mapper.insertIdentification(client_id, clientIdentification);
	}

	@Override
	public int updateClient(Login login, Client client) {
		return mapper.updateClient(login, client);
	}

	@Override
	public int updateIdentification(Identification clientIdentification, String client_id) {
		return mapper.updateIdentification(clientIdentification, client_id);
	}

	
}
