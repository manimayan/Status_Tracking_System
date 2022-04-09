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
public class Application implements Serializable{
	
	 private static final long serialVersionUID = 40586895471103592L;
	 
	    private String applicationId;
		private String applicationName;
		private String applicationShortname;
		private Set<Ticket> ticket;	
		//private Set<Employee> employee;
}
