package com.fidelity.smallchange.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fidelity.smallchange.models.Preference;

class PreferenceTest {
	private Preference p1;
	@BeforeEach
	void init() throws Exception {
		p1 = new Preference("A123", "Education", "AGGRESSIVE", "1000-2000", "5-8 years");
	}
	
	
	@Test
	void testPreferenceNotNull() {
		assertNotNull(p1);
	}
	
//	@Test
//	void testPreferenceWithZero() {
//		
//		Exception ex = assertThrows(IllegalArgumentException.class,  ()->{
//			p1 =  new Preference("", "Education", "", "1000-2000", "5-8 years");
//		});
//		assertEquals("preferences cannot be empty", ex.getMessage());
//	}
//	
//	@Test
//	void testPreferenceWithNull() {
//		
//		Exception ex = assertThrows(NullPointerException.class,  ()->{
//			p1 =  new Preference(null, "Education", "", "1000-2000", null);
//		});
//		assertEquals("preferences cannot be null", ex.getMessage());
//	}

}
