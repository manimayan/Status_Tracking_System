package com.acc.sts.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

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

public class ReassignTicket implements Serializable{	
	private static final long serialVersionUID = 40586895471103592L;
	
	@Id
	@Column(name = "Ticket_ID")
	private String ticketId;

	@Column(name = "Ticket_Desc")
	private String ticketDescription;

	@Column(name = "Employee_Name")
	private String employeeName;

	
	 public ReassignTicket(String ticketId,String ticketDescription,String employeeName){
	    	this.ticketId=ticketId;
	    	this.ticketDescription=ticketDescription;
	    	this.employeeName=employeeName;
	    	 	
	    }
	
}
