package com.acc.sts.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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
public class Reportsummary implements Serializable {
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
    private String devComment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private LocalDate updatedOn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private LocalDate endDate;
    private String remedy;
    private String documentationDescription;
   private String releaseTicket;
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
   private LocalDate startdateFrom;    
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
   private LocalDate startdateTo;    
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
   private LocalDate enddateFrom;    
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
   private LocalDate enddateTo;  
   
 
    
    public Reportsummary(String employeeName,String ticketId,String ticketType,String ticketDescription,String applicationName,int priority,String activity,String status,String tester,String devComment,LocalDate startDate,LocalDate updatedOn,LocalDate endDate,String remedy,String documentationDescription){
    	this.employeeName=employeeName;
    	this.ticketId=ticketId;
    	this.ticketType=ticketType;
    	this.ticketDescription=ticketDescription;
    	this.applicationName=applicationName;
    	this.priority=priority;
    	this.activity=activity;
    	this.status=status;
    	this.tester=tester;
    	this.devComment=devComment;
    	this.startDate=startDate; 	
    	this.updatedOn=updatedOn; 
    	this.endDate=endDate; 
    	this.remedy=remedy;
    	this.documentationDescription=documentationDescription;
    }


} 