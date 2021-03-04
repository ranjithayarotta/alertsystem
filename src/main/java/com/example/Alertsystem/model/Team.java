package com.example.Alertsystem.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "team")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "team_id")
	private Integer id;
	@NotNull
	@NotBlank
	private String teamName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "team_fid", referencedColumnName = "team_id")
	@NotNull
	private Set<Developer> developer;

	Team() {

	}

	public Team(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamName() {
		return teamName;
	}

	public Integer getId() {
		return id;
	}

	public Set<Developer> getDeveloper() {
		return developer;
	}

	public void setDeveloper(Set<Developer> developer) {
		this.developer = developer;
	}

}
