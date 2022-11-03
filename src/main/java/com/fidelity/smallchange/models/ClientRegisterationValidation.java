package com.fidelity.smallchange.models;

import java.util.ArrayList;
import java.util.List;

public class ClientRegisterationValidation {
	
	private static List<Client> users= new ArrayList<Client>();
	public static void clientExists(Client new_client) {
		
		for(Client cl:users) {
			if(cl.equals(new_client))
				throw new IllegalArgumentException("Client Already Exists");
		
		}
		users.add(new_client);
			
		
	}
	public static Client getClient(String clientId) {
		if(clientId==""||clientId==null)
			throw new IllegalArgumentException("Client id cannot be null");
		for(Client cl:users) {
			if(cl.getClientId().equals(clientId))
				return cl;
				
		
	}
		throw new IllegalArgumentException("Client id cannot be found");
		
	}
	

}
