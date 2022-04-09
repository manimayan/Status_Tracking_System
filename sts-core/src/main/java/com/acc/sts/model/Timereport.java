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
public class Timereport implements Serializable {

	private static final long serialVersionUID = 40586895471103592L;

    private String employeeId;

    private String employeeName;

  /* private String emailId;*/
    
   private String supervisorName;
    
    
   @Temporal(TemporalType.TIME)
   @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=" HH:mm:ss")
   // @JsonIgnore
    private Date inTime;

    
   @Temporal(TemporalType.TIME)
   @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=" HH:mm:ss")
   // @JsonIgnore
    private Date outTime;
	
    private String difference;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private LocalDate  date;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private LocalDate  fdate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private LocalDate  todate;
    
    public Timereport(String employeeId,String employeeName,/*String emailId,*/String supervisorName,Date inTime,Date outTime,String difference,LocalDate date){
    	this.employeeId=employeeId;
    	this.employeeName=employeeName;
    	/*this.emailId=emailId;*/
        this.supervisorName=supervisorName;
    	this.inTime= inTime;
    	this.outTime= outTime;
    	this.difference=difference;
    	this.date=date;
    }
    public Timereport(String employeeName)
    {
    	this.employeeName=employeeName;
    }
    public Timereport(String employeeId,String employeeName,Date inTime,Date outTime)
    {
    	this.employeeId=employeeId;
    	this.employeeName=employeeName;
    	this.inTime= inTime;
    	this.outTime= outTime;
    }
}
