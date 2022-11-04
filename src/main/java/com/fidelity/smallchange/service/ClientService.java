package com.fidelity.smallchange.service;

import java.util.List;

import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.Identification;
import com.fidelity.smallchange.models.Login;

public interface ClientService {
	public Client getClientByID(String clientId);
	public int insertClient(Client client);
	//public int insertIdentification(String client_id,Identification clientIdentification);
	public int updateClient(Client client);
	public int updateIdentification(Identification clientIdentification,String client_id);
}
