package com.fidelity.smallchange.integration;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.ClientIdentification;
import com.fidelity.smallchange.models.Login;


@Repository
public interface ClientDao {
	public Client getClientByID(String clientId);
	public int insertClient(Client client);
	public int insertIdentification(String client_id,ClientIdentification id);
	public int updateClient(Client client);
	public int updateIdentification(ClientIdentification clientIdentification,String client_id);
}
