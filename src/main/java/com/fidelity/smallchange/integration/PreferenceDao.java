package com.fidelity.smallchange.integration;

import com.fidelity.smallchange.models.Preference;

public interface PreferenceDao {
	
	Preference getPreference(String id);
	int insertPreference(Preference preference);
}
