package com.acc.sts.model;

import java.time.LocalDate;
import java.util.Date;

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

public class Tickethistory {
	
	private String employeeName;
	private String ticketId;
private String ticketType;
//@JsonFormat(shape = JsonFormat.Shape.NATURAL, pattern = "yyyy/mm/dd") 


@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 

  
 

	private LocalDate date;
	
	private String status;
	 private String devComment;

}
