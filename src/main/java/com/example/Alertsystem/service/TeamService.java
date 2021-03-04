package com.example.Alertsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Alertsystem.exception.SourceNotfoundException;
import com.example.Alertsystem.model.Developer;
import com.example.Alertsystem.model.Team;
import com.example.Alertsystem.repo.TeamRepo;

@Service
public class TeamService {

	@Autowired
	private TeamRepo repository;
	@Autowired
	private DeveloperService developerService;

	public Integer addTeam(Team team) throws SourceNotfoundException {
		Team teamInfo = repository.save(team);
		if (teamInfo != null) {
			Set<Developer> developer = teamInfo.getDeveloper();
			List<String> developerIds = developer.stream().map(Developer::getPhoneNumber)
					.collect(Collectors.toList());
			developerService.getMapVal.put(teamInfo.getId(), developerIds);
			return teamInfo.getId();
		} else {
			throw new SourceNotfoundException("Failure in insertion in DB");
		}
	}

	public Team getByTeamId(Long teamId) throws SourceNotfoundException {
		return repository.findById(teamId).orElseThrow(() -> new SourceNotfoundException("Not found"));
	}

	public Iterable<Team> getAll()  {
		return repository.findAll();
	}

}
