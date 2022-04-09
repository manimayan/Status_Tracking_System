package com.acc.sts.model;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vacation {

	 private int vacationId;
	 private Employee employee;
	 private String employeeName;
	 @JsonFormat(shape = JsonFormat.Shape.STRING,pattern= "MM/dd/yyyy")
	 private LocalDate vacationDateFrom;
	 @JsonFormat(shape = JsonFormat.Shape.STRING,pattern= "MM/dd/yyyy")
	 private LocalDate vacationDateTo;
	 private String vacationType;
	 private String vacationComments;
	

}
