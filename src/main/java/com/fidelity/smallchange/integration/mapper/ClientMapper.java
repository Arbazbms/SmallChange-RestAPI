package com.fidelity.smallchange.integration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fidelity.smallchange.models.Client;
import com.fidelity.smallchange.models.ClientIdentification;
import com.fidelity.smallchange.models.Login;


@Mapper
public interface ClientMapper {
    public Client getClientByID(String clientId);
	public int insertClient(@Param("client")Client client);
	public int insertIdentification(@Param("client_id")String client_id,@Param("clientIdentification")ClientIdentification clientIdentification);
	public int updateClient(@Param("client")Client client);
	public int updateIdentification(@Param("clientIdentification")ClientIdentification clientIdentification,@Param("client_id")String client_id);
	public Client getClientByEmailAndPassword(Login credentials);
}
