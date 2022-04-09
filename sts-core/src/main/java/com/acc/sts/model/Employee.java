package com.acc.sts.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "employee_details")
public class Employee implements Serializable {
    private static final long serialVersionUID = 40586895471103592L;

    @Id
   // @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Employee_ID ")
    private String employeeId;

    @Column(name = "Employee_Name")
    private String employeeName;

    @Column(name = "Designation")
    private String designation;
    
    @Column(name = "Supervisor_Name")
    private String supervisorName;
    
    @Column(name = "role")
    private String role;
 
    @Column(name = "is_active")
    private String isActive ;
    
    @Column(name = "is_include")
    private String isInclude ;
    
    @Column(name = "Password")
    private String password ;
    
    @Column(name = "is_admin")
    private String isAdmin ;
    
    @Column(name = "is_supervisor")
    private String isSupervisor ;
    
    @Column(name = "Nick_Name")
    private String nickName ;
    
    @Column(name = "Report")
    private String report ;
    
    @Column(name = "Flag")
    private int flag ;
    
    @Column(name = "Skills")
	private String skills;
	
    
    //for security reason this two field commented (Kavitha Naganathan)
   /* @Column(name = "Contact_Number")
	private long contactNumber; 
    
    @Column(name = " Email")
    private String emailId;*/
      
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "employee", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Ticket> ticket;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "employee", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Vacation> vacation;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
   	@JsonIgnore
   	 private Set<Timing> timing; 
    
}