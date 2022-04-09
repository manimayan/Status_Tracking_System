package com.acc.sts.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "timing")
public class Timing implements Serializable {
    private static final long serialVersionUID = 40586895471103592L;

    @Id
    @Column(name = "Time_Id")
    private int timeId;
    
 /*  @Column(name = "Employee_ID")
    private int employeeId;
*/
    @Temporal(TemporalType.TIME)
    @Column(name = "inTime")
    private Date inTime;

    @Temporal(TemporalType.TIME)
   @Column(name = "outTime")
    private Date outTime;
       
    @Column(name = "Difference")
    private String difference;
   
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    @Column(name = "currentDate")
    private LocalDate date;
    
   
   @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID")
    private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}

	 	public void setEmployee(Employee employee) {
		this.employee = employee;
	} 
}
