package com.acc.sts.model;


import java.io.Serializable;
import java.util.Date;

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
public class Clarification implements Serializable{
	
	 private static final long serialVersionUID = 40586895471103592L;
	 
	 private int clarificationId;
	 private String clarificationDescription;
	 private String employeeResponse;
	 private int flag;
	 private Date date;
	 private Ticket ticket;
	
	

}
