package com.acc.sts.model;

import java.io.Serializable;
import java.util.Date;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReassignTicket {
	
	private String ticketId;
	private String ticketDescription;
	private String employeeName;
}
