package com.acc.sts.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "application")
public class Application {
	private static final long serialVersionUID = 40586895471103592L;
	@Id
	@Column(name = "Application_ID")
	private String applicationId;

	@Column(name = "Application_Name")
	private String applicationName;

	@Column(name = "Application_Shortname")
	private String applicationShortname;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "application", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Ticket> ticket;	


	/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "application", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Employee> employee;*/


	
}