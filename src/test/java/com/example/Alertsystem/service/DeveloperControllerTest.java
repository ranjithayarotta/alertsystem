package com.example.Alertsystem.service;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import com.example.Alertsystem.AbstractTest;

public class DeveloperControllerTest extends AbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void postAlert() throws Exception {

		MvcResult mvcResult = mvc.perform(put("/{team_id}/alert", 1)).andExpect(status().isOk()).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
		JSONObject expected = new JSONObject();
		expected.put("success", "alert sent");
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, expected.toString());

	};

}
