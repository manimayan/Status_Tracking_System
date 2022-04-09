package com.acc.sts.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
	
	private String employeeName;
	private String employeeId;
	private String designation;
	private String supervisorName;
	private String isAdmin;
	private String isSupervisor;
	private String role;
	private String isActive;
	private String isInclude;
	private String password;
	private String nickName;
	private String report;
	private int flag;
	private String skills;
	private Set<Timing> timing;
	
	
    //for security reason this two field commented (Kavitha Naganathan)
	/*private long contactNumber;
	private String emailId;*/

}
