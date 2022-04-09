package com.acc.sts.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "Security")
public class Security {
	private static final long serialVersionUID = 40586895471103592L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Security_ID")
	private int securityId;

	@Column(name = "Web_Browser")
	private String webBrowser;

	@Column(name = "Social_Media")
	private String socialMedia;
	
	@Column(name = "Father_Lastname")
	private String fatherLastname;
	
	@Column(name = "First_Teacher")
	private String firstTeacher;
	
	@Column(name = "Role_Model")
	private String roleModel;
	
	@Column(name = "Leader")
	private String leader;

	@Column(name = "First_School")
	private String firstSchool;
	
	@Column(name = "High_School")
	private String highSchool;
	
	@Column(name = "Vacation_Spot")
	private String vacationSpot;
	
	@Column(name = "Birth_City")
	private String birthCity;
	
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID")
	private Employee employee;
	




	
}