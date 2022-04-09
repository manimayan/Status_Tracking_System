package com.acc.sts.model;

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
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "documentation")
public class Documentation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Documentation_ID")
	private int documentationId;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "Ticket_ID")
	private Ticket ticket;

	@Column(name = "Doc_Comment")
	private String documentationComment;

	@Column(name = "Remedy")
	private String remedy;

	@Column(name = "Document_Desc")
	private String documentationDescription;

	
}
