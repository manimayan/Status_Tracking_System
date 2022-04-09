package com.acc.sts.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "ticket_details")
public class Ticket implements Serializable{	
	private static final long serialVersionUID = 40586895471103592L;
	
	@Id
	@Column(name = "Ticket_ID")
	private String ticketId;

	@Column(name = "Ticket_Desc")
	private String ticketDescription;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID")
	private Employee employee;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "Application_ID")
	private Application application;
	
	
	@Column(name = "Ticket_type")
	private String ticketType;

	@Column(name = "Priority", nullable = false, columnDefinition = "int default 0")
	private int priority;

	@Column(name = "Worked_on_today")
	private String workedOnToday;
	
	@Column(name = "Status")
	private String status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "Updated_On")
	private LocalDate updatedOn;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "Start_date")
	private LocalDate startDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "End_date")
	private LocalDate endDate;

	@Column(name = "Release_ticket",nullable = false, columnDefinition = "varchar(255) default 'No'")
	private String releaseTicket;

	@Column(name = "Flag")
	private int flag;
	
	@Column(name = "Tester")
	private String tester;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "ticket", fetch = FetchType.LAZY)
	private Set<Clarification> clarification;	 
 
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ticket", fetch = FetchType.LAZY)
	private Set<Dev_comment> dev_comment; 
 

	public Ticket(String ticketId)
	{
		
		this.ticketId=ticketId;
	
	}

	public Ticket() {
		
	}

}
