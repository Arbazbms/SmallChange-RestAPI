package com.fidelity.smallchange.integration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.Identification;
import com.fidelity.smallchange.models.Login;

public interface ClientMapper {
    public Client getClientByID(String clientId);
	public int insertClient(@Param("client")Client client);
	public int insertIdentification(@Param("client_id")String client_id,@Param("clientIdentification")Identification clientIdentification);
	public int updateClient(@Param("client")Client client);
	public int updateIdentification(@Param("clientIdentification")Identification clientIdentification,@Param("client_id")String client_id);
}
