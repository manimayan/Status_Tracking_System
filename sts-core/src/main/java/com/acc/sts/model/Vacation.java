package com.acc.sts.model;

import java.io.Serializable;
import java.time.LocalDate;

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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "vacation")
public class Vacation implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "VACATION_ID")
    private int vacationId;
    
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID")
	private Employee employee;
	
	@Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern= "MM/dd/yyyy")
    @Column(name = "VACATION_FROM")
    private LocalDate vacationDateFrom;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern= "MM/dd/yyyy")
    @Column(name = "VACATION_TO")
    private LocalDate vacationDateTo;
    
    @Column(name = "VACATION_TYPE")
    private String vacationType;
    
    @Column(name = "VACATION_COMMENTS")
    private String vacationComments;

}
