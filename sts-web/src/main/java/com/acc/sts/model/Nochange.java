package com.acc.sts.model;
import java.time.LocalDate;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nochange {
	private String ticketId;
	 //private String emp_name;
	
	
	
	private String employeeName;
	private String ticketType;
	private String ticketDescription;
	
	private String applicationId;
	private int priority;
	private String activity;
	private String status;
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	 private LocalDate updatedOn;
	 private String devComment;
}
