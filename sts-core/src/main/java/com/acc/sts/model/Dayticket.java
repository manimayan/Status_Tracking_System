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
public class Dayticket implements Serializable {
    private static final long serialVersionUID = 40586895471103592L;

    private String employeeName;
    private String ticketId;
    private String ticketType;
    private String ticketDescription;
    private String application;
    private int priority;
    private String activity;
    private String status;
    private String devComment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private LocalDate startDate;
  
   
 
    
    public Dayticket(String employeeName,String ticketId,String ticketType,String ticketDescription,String application,int priority,String activity,String status,String devComment,LocalDate startDate){
    	this.employeeName=employeeName;
    	this.ticketId=ticketId;
    	this.ticketType=ticketType;
    	this.ticketDescription=ticketDescription;
    	this.application=application;
    	this.priority=priority;
    	this.activity=activity;
    	this.status=status;
    	this.devComment=devComment;
    	this.startDate=startDate; 	
    }
}
