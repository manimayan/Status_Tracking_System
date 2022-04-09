

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
public class DeleteTicket {
	 private static final long serialVersionUID = 40586895471103592L;
	
	private String ticketId;
	private String ticketDescription; 
	private String applicationName;
	    public DeleteTicket(String ticketid,String ticketdesc,String appname){
	    	this.ticketId=ticketid;
	    	this.ticketDescription=ticketdesc;
	    	this.applicationName=appname;
	    	
	    }
	
} 