
	package com.acc.sts.model;

	import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
	@Getter
	@Setter
	@ToString
	@JsonIgnoreProperties(ignoreUnknown = true)

	public class Tester_Comment {
				private int commentId;

		
		private Ticket ticket;

	
		private String testComment;

	
		private String status;

	
		private String activity;

		
		private Date date;

		
	} 
