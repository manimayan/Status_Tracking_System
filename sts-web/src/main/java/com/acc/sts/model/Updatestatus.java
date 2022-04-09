package com.acc.sts.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

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
public class Updatestatus {

     private String ticketId;
	
     private String ticketType;
	
     private String ticketDescription;
     
     private String applicationName;
 
	 private int priority;
	 
	 private String activity;
	 
	 private String status;
	 
	 //private String tester;
	 
	private String testerName;
	 
	 private String testComment;
	 
	 private String devComment;
	 private String workedOnToday;
	 
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
	 private LocalDate date;
	 
	 private String tester;
}
