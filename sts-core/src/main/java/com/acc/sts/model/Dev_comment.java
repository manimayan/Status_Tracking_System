package com.acc.sts.model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@Entity
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

@Table(name = "dev_comment")
public class Dev_comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_ID")
	private int commentId;
	 
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "Ticket_ID")
	private Ticket ticket;

	@Column(name = "Devcomment_Desc")
	private String devComment;

	@Column(name = "Status")
	private String status;

	@Column(name = "Activity")
	private String activity;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	
	@Column(name = "Date")
	private LocalDate date;

	

}