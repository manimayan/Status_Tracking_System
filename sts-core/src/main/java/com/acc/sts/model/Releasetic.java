

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
	public class Releasetic implements Serializable {
	    private static final long serialVersionUID = 40586895471103592L;

	    private String ticketId;

	    private String ticketDescription;

	    private String applicationName;
	    
	    private String employeeName;
	    
	    private String releaseTicket;
	    
	    public Releasetic(String ticketId,String ticketDescription,String applicationName,String employeeName,String releaseTicket){
	    	this.ticketId=ticketId;
	    	this.ticketDescription=ticketDescription;
	    	this.applicationName=applicationName;
	    	this.employeeName=employeeName;
	    	this.releaseTicket=releaseTicket;
	    	
	    
	    }
	}
	
	
