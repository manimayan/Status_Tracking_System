package com.acc.sts.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "security_details")
public class Questioncheck implements Serializable {
    private static final long serialVersionUID = 40586895471103592L;

   
    @Id
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "Question_ID")
    private Question question;
    
    @Id
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID") 
    private Employee employee;
    
    @Column(name = "Answers")
    private String answers;
    
    @Override
	public String toString() {
		return "Questioncheck [questionId=" + question + ", employeeId=" + employee + ", answers=" + answers + "]";
	}
    
}
	