package com.acc.sts.model;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;



import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Timereport {

	private String employeeId;
	
    private String employeeName;
    
   /* private String emailId;*/
    
    private String supervisorName;
     
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    private Date inTime;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
	private Date outTime;  
 
	private String difference;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private LocalDate date;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private LocalDate  fdate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private LocalDate  todate;
}
