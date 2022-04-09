package com.acc.sts.model;





import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

public class Nochange implements Serializable{
	private static final long serialVersionUID = 40586895471103592L;

	private String ticketId;
	
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
	 
 public Nochange(String ticketId,String employeeName,String ticketType,String ticketDescription,String applicationId,int priority, String activity,String status,LocalDate updatedOn,String devComment){
      this.ticketId=ticketId;
      this.employeeName=employeeName;
      this.ticketType=ticketType;
      this.ticketDescription=ticketDescription;
      this.applicationId=applicationId;
      this.priority=priority;
      this.activity=activity;
      this.status=status;
      this.updatedOn=updatedOn;
      this.devComment=devComment;
       }
 }



