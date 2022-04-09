package com.acc.sts.model;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Updatestatus implements Serializable {

	private static final long serialVersionUID = 40586895471103592L;

	private String ticketId;
	
    private String ticketType;
	
    private String ticketDescription;
    
    private String applicationName;

	 private int priority;
	 
	 private String activity;
	 
	 private String status;
	 
	 //private String tester;
	 
	 private String devComment;
	 private String testComment;
	 private String  workedOnToday;
    
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
	 private LocalDate date;
	 
	 private String testerName;
	 
    public Updatestatus(String ticketId,String ticketType,String ticketDescription,String applicationName,int priority,String activity,String status/*,String tester*/,String devComment,String testComment,String  workedOnToday,LocalDate date,String tester){
    	this.ticketId=ticketId;
    	this.ticketType=ticketType;
    	this.ticketDescription=ticketDescription;
    	this.applicationName=applicationName;
    	this.priority= priority;
    	this.activity= activity;
    	this.status=status;
    	//this.tester=tester;
    	this.devComment=devComment;
    	this.testComment=testComment;
    	this.workedOnToday=workedOnToday;
    	this.date=date;
    	this.testerName=tester;
    }
}
