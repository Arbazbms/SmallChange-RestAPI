package com.fidelity.smallchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidelity.smallchange.integration.PortfolioDAOMyBatisImp;
import com.fidelity.smallchange.integration.PreferenceDao;
import com.fidelity.smallchange.integration.mapper.PortfolioMapper;
import com.fidelity.smallchange.models.PortfolioItem;
import com.fidelity.smallchange.models.Preference;

import java.util.List;

@Service()
public class PreferenceService {
	
	@Autowired
	private PreferenceDao dao;
	
	public Preference getPreferenceById(String id) {
		return dao.getPreference(id);
	}
}
