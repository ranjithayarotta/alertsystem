package com.example.Alertsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Alertsystem.exception.SourceNotfoundException;
import com.example.Alertsystem.model.Team;
import com.example.Alertsystem.service.TeamService;

@RestController
@RequestMapping("/api/team")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@PostMapping("/add")
	public long addTeam(@RequestBody Team team) throws SourceNotfoundException {
		return teamService.addTeam(team);
	}

}
