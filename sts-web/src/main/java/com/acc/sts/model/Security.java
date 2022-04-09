package com.acc.sts.model;


import java.io.Serializable;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Security implements Serializable{
	
	 private static final long serialVersionUID = 40586895471103592L;
	    
		private int securityId;
		private String webBrowser;
		private String socialMedia;
		private String fatherLastname;
		private String firstTeacher;
		private String roleModel;
		private String leader;
		private String firstSchool;
		private String highSchool;
		private String vacationSpot;
		private String birthCity;
		private Employee employee;
}
