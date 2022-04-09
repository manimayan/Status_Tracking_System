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

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "tester_comment")
public class Tester_comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Comment_ID")
	private int commentId;

	@ManyToOne
	@JoinColumn(name = "Ticket_ID")
	private Ticket ticket;

	@Column(name = "Testcomment_Desc")
	private String testComment;

	@Column(name = "Status")
	private String status;

	@Column(name = "Activity")
	private String activity;

	
	@Column(name = "Date")
	private LocalDate  date;

	
}
