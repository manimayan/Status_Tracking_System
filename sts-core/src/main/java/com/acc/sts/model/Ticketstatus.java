
package com.acc.sts.model;

	import java.io.Serializable;
import java.time.LocalDate;



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
	public class Ticketstatus implements Serializable {
	    private static final long serialVersionUID = 40586895471103592L;

	    private String ticketId;
	    
	    private String ticketDescription;
	    private String ticketType;

	    private String employeeName;
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	    private LocalDate updatedOn;
	    
	    private String status;
	    
	    private String devComment;
	    
	    public Ticketstatus(String ticketId,String ticketDescription,String ticketType,String employeeName,LocalDate updatedOn,String status,String devComment ){
	    	this.ticketId=ticketId;
	    	this.ticketDescription=ticketDescription;
	    	this.ticketType=ticketType;
	    	this.employeeName=employeeName;
	    	this.updatedOn=updatedOn;
	    	this.status=status;
	    	this.devComment=devComment;
	    	
	    
	    }
	}