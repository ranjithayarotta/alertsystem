package com.example.Alertsystem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Alertsystem.exception.SourceNotfoundException;
import com.example.Alertsystem.model.Developer;
import com.example.Alertsystem.model.Team;
import com.example.Alertsystem.utility.Utility;

@Service
public class DeveloperService {

	@Autowired
	private TeamService teamservice;
	// @Autowired
	// private DeveloperRepo developerRepo;

	public Map<Integer, List<String>> getMapVal;

	@Autowired
	private RestTemplate restTemplate;

	private static final String MESSAGE_URL = "https://run.mocky.io/v3/fd99c100-f88a-4d70-aaf7-393dbbd5d99f";

	@PostConstruct
	private void init() {

		initiliazeMap();
	}

	public String triggerAlert(Integer teamId) throws SourceNotfoundException {
		// initiliazeMap();
		List<String> developedIds = getMapVal.get(teamId);
		if (!developedIds.isEmpty()) {
			int randomNumber = Utility.getTheRandomNumber(0, developedIds.size() - 1);
			String number = developedIds.get(randomNumber);
			String msg = sentMessage(number);
			developedIds.remove(randomNumber);
			getMapVal.put(teamId, developedIds);
			return msg;
		} else {
			throw new SourceNotfoundException("Empty phoneNumber");
		}
	}

	private String sentMessage(String number) {
		JSONObject personJsonObject = new JSONObject();
		personJsonObject.put("phone_number", number);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(personJsonObject.toString(), headers);
		String personResultAsJsonStr = restTemplate.postForObject(MESSAGE_URL, entity, String.class);
		return personResultAsJsonStr;
	}

	private void initiliazeMap() {
		if (getMapVal == null) {
			getMapVal = new HashMap<>();
		}
		Iterable<Team> Iteam = teamservice.getAll();
		Iteam.forEach(team -> {
			Set<Developer> developerInfo = team.getDeveloper();
			List<String> developerIds = developerInfo.stream().map(Developer::getPhoneNumber)
					.collect(Collectors.toList());
			getMapVal.put(team.getId(), developerIds);
		});
	}

}
