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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "employee_details")
public class NotReportEmployee implements Serializable{	
	private static final long serialVersionUID = 40586895471103592L;
	
	private String employeeName;
	private String employeeId;
	/*private String emailId;*/
	private String designation; 
	
	public NotReportEmployee(String employeeName,String employeeId,/*String emailId,*/String designation){
    	this.employeeName=employeeName;
    	this.employeeId=employeeId;
 /*   	this.emailId=emailId;*/
    	this.designation=designation;
    		
    }
}
