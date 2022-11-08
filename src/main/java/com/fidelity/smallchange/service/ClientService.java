package com.fidelity.smallchange.service;

import java.util.List;

import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.ClientIdentification;
import com.fidelity.smallchange.models.Login;

public interface ClientService {
	public Client getClientByID(String clientId);
	public int insertClient(Client client);
	//public int insertIdentification(String client_id,ClientIdentification clientIdentification);
	public int updateClient(Client client);
	public int updateIdentification(ClientIdentification clientIdentification,String client_id);
	boolean registerClient(Client new_client);
}
