package com.example.Alertsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "developer")
public class Developer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "developer_id")
	private Integer developerId;
	@NotNull
	@NotBlank
	private String name;
	@NotNull
	@NotBlank
	@Column(unique = true)
	private String phoneNumber;

	Developer() {

	}

	Developer(String name, String phoneNumber) {

		this.name = name;
		this.phoneNumber = phoneNumber;

	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Integer getDeveloperId() {
		return developerId;
	}

}
