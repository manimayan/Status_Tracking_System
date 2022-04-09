package com.acc.sts.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "clarification")
public class Clarification {
	@Id
	@Column(name = "Clarification_ID")
	@Autowired
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int clarificationId;

	@Column(name = "Clarification_Desc")
	private String clarificationDescription;

	@Column(name = "Employee_Res")
	private String employeeResponse;

	@Column(name = "Flag", nullable = false, columnDefinition = "int default 0")
	private int flag;

	@Temporal(TemporalType.DATE)
	@Column(name = "Date")
	private Date date;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "Ticket_ID")
	private Ticket ticket;

	

}
