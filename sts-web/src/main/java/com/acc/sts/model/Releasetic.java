package com.acc.sts.model;
	import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

	import lombok.Getter;
	import lombok.Setter;
	import lombok.ToString;

	@Getter
	@Setter
	@ToString
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Releasetic {

		
		private String ticketId;
		private String ticketDescription;
		private String applicationName;
		private String employeeName;
		private String releaseTicket;
		

	}



