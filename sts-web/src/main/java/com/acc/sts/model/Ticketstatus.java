
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
	public class Ticketstatus {

		
		private String ticketId;
		private String ticketDescription;
		private String ticketType;
		private String employeeName;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		private LocalDate updatedOn;
		private String status;
		 private String devComment;

	}
