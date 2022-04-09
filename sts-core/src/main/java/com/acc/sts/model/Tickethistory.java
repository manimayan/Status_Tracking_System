

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
public class Tickethistory implements Serializable {
    private static final long serialVersionUID = 40586895471103592L;

	private String employeeName;
	private String ticketId;
    private String ticketType;
 //   @JsonFormat(shape = JsonFormat.Shape.NATURAL, pattern = "yyyy/mm/dd") 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    

    private LocalDate date;
	
	private String status;
	private String devComment;
   
    public Tickethistory(String employeename,String ticketId,String ticketType,LocalDate date,String status,String devComment){
    	this.employeeName=employeename;
    	this.ticketId=ticketId;
        this.ticketType=ticketType;
    	this.date=date;
    	this.status=status;
    	this.devComment=devComment;
    	
    	
    }
}
