package com.acc.sts.model;

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
public class Edit {
	 private static final long serialVersionUID = 40586895471103592L;
	
	private String ticketId;
	
	private String ticketType;
	private String ticketDescription;
	private String applicationName;
	private int priority;
	private int newPriority;
	

	    
	    
	    public Edit(String ticketId,String ticketType,String ticketDescription,String applicationName,int priority){
	    	this.ticketId=ticketId;
	    	
	    	this.ticketType=ticketType;
	    	this.ticketDescription=ticketDescription;
	    	this.applicationName=applicationName;
	    	this.priority=priority;
	    	
	    }
	
}
