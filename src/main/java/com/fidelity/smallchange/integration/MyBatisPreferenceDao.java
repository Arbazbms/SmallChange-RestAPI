package com.fidelity.smallchange.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.smallchange.integration.mapper.PreferenceMapper;
import com.fidelity.smallchange.models.Preference;

@Repository
public class MyBatisPreferenceDao implements PreferenceDao{

	@Autowired
	private PreferenceMapper mapper;
	@Override
	public Preference getPreference(String id) {
		// TODO Auto-generated method stub
		return mapper.getPreference(id);
	}

	@Override
	public int insertPreference(Preference preference) {
		// TODO Auto-generated method stub
		return mapper.insertPreference(preference);
	}

}
