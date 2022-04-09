package com.acc.sts.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Dailystatus implements Serializable {
    private static final long serialVersionUID = 40586895471103592L;

    private String employeeName;
    private String ticketId;
    private String ticketType;
    private String ticketDescription;
    private String applicationName;
    private int priority;
    private String activity;
    private String status;
    private String tester;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate updatedOn;
   private String  testComment;
    private String devComment;
	private String workedOnToday;
   
   
 
    
    public Dailystatus(String employeeName,String ticketId,String ticketType,String ticketDescription,String applicationName,int priority,String activity,String status,String devComment,LocalDate updatedOn,String tester,String  testComment,String workedOnToday){
    	this.employeeName=employeeName;
    	this.ticketId=ticketId;
    	this.ticketType=ticketType;
    	this.ticketDescription=ticketDescription;
    	this.applicationName=applicationName;
    	this.priority=priority;
    	this.activity=activity;
    	this.status=status;
    	this.devComment=devComment;
    	this.updatedOn=updatedOn; 	
    	this.tester=tester;
    	this.testComment=testComment; 
    	this.workedOnToday=workedOnToday; 
    }
    public Dailystatus(String employeeName)
    {
    	this.employeeName=employeeName;
    }
}
