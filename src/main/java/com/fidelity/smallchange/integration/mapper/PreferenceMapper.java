package com.fidelity.smallchange.integration.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fidelity.smallchange.models.Preference;

@Mapper
public interface PreferenceMapper {
	Preference getPreference(String id);
	int insertPreference(Preference preference);
}
