package com.fidelity.smallchange.integration;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.smallchange.models.Preference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class PreferenceDaoTest {

	@Autowired
	private PreferenceDao dao;

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Test
	void testGetPreference() {
		Preference pref = dao.getPreference("C101");
		
		// verify the Widgets from the database 
		assertNotNull(pref);
	}

}
