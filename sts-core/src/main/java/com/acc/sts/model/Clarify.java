package com.acc.sts.model;

import java.io.Serializable;

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
public class Clarify implements Serializable {
    private static final long serialVersionUID = 40586895471103592L;

    private String ticketId;

    private String ticketType;
    
    private String employeeName;

    private String ticketDescription;
    
    private String  applicationName;
    
    private String status;
    
    private String clarificationDescription;
    
    private String response;
    
    private int flag;
    
    public Clarify(String ticketId,int flag,String ticketType,String employeeName,String descrip,String applicationName,String status,String clarificationDescription,String response){
    	this.flag=flag;
    	this.ticketId=ticketId;
    	this.ticketType=ticketType;
    	this.employeeName=employeeName;
    	this.ticketDescription=descrip;
    	this.applicationName=applicationName;
    	this.status=status;
    	this.clarificationDescription=clarificationDescription;
    	this.response=response;
     }
    public Clarify(String ticketId,String ticketType,String employeeName,String descrip,String applicationName,String status){
    
    	this.ticketId=ticketId;
    	this.ticketType=ticketType;
    	this.employeeName=employeeName;
    	this.ticketDescription=descrip;
    	this.applicationName=applicationName;
    	this.status=status;
   
     }
    /*public Clarify(String ticketId,String ticketType,String employeeName,String descrip,String applicationName,String status){
    	this.ticketId=ticketId;
    	this.ticketType=ticketType;
    	this.employeeName=employeeName;
    	this.clarificationDescription=descrip;
    	this.applicationName=applicationName;
    	this.status=status;
  
     }*/
    }

