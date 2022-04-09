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
public class Homeadmin implements Serializable {
    private static final long serialVersionUID = 40586895471103592L;

    private String ticketId;

    private String ticketType;

    private String ticketDescription;	
    
    private String applicationName;
    private int priority;
    private String activity;
    private String status;
    private String devComment;
    private String tester;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private String testComment;
    private String developerName;
    
   
    
    public Homeadmin(String ticketId,String ticketType,String descrip,String app,int priority,String activity,String status,String devComment,String tester,LocalDate sdate,String testcomment){
    	this.ticketId=ticketId;
    	this.ticketType=ticketType;
    	this.ticketDescription=descrip;
    	this.applicationName=app;
    	this.priority=priority;
    	this.activity=activity;
    	this.status=status;
    	this.devComment=devComment;
    	this.tester=tester;
    	this.startDate=sdate;
    	this.testComment=testcomment;
    }
    public Homeadmin(String ticketId,String ticketType,String descrip,String app,int priority,String activity,String status,String devComment,LocalDate sdate,String testcomment,String developerName){
    	this.ticketId=ticketId;
    	this.ticketType=ticketType;
    	this.ticketDescription=descrip;
    	this.applicationName=app;
    	this.priority=priority;
    	this.activity=activity;
    	this.status=status;
    	this.devComment=devComment;
    //	this.tester=tester;
    	this.startDate=sdate;
    	this.testComment=testcomment;
    	this.developerName=developerName;
    }
}
