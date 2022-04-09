package com.acc.sts.model;
//import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Edit {
	private String ticketId;
	 private String ticketType;
	 private String ticketDescription;
	 private int priority;
	 private String applicationName;
	 //private String newPriority;
}
