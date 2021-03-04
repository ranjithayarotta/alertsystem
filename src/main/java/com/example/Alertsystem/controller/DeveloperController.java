package com.example.Alertsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Alertsystem.exception.SourceNotfoundException;
import com.example.Alertsystem.service.DeveloperService;

@RestController
@RequestMapping("/api/developer")
public class DeveloperController {

	@Autowired
	private DeveloperService developerService;

	@PostMapping("/{team_id}/alert")
	public String postAlert(@PathVariable("team_id") Integer teamId) throws SourceNotfoundException {
		return developerService.triggerAlert(teamId);

	}

}
