package com.acc.sts.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadTicket {
	
	private String ticketId;
	private String ticketDescription;
	private String applicationName;
	private int flag;
	private String createdBy;
	private LocalDateTime createdOn;
}
