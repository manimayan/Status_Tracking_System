package com.acc.sts.model;


import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket{	
	private static final long serialVersionUID = 40586895471103592L;
	
	private String ticketId;
	private String ticketDescription;
    private Employee employee;
	private Application application;
	private String ticketType;
	private int priority;
	private String status;
	private String workedOnToday;
	private LocalDate updatedOn;
	private LocalDate startDate;
	private LocalDate endDate;
	private String releaseTicket;
	private int flag;
	private String tester;
	
}  