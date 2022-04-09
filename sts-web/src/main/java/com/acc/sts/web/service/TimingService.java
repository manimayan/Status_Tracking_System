package com.acc.sts.web.service;

import java.util.Date;
import java.util.List;

import com.acc.sts.model.Employee;
import com.acc.sts.model.Timereport;
import com.acc.sts.model.Timing;

public interface TimingService {

	Timing saveTiming(Timing outtime);

	Timing saveIntime(Timing intime);

	List<Employee> getname();

	//List<Timereport> submitEmpdetails(String employeeId, String fdate, String todate);

	
	List<Timereport> submitEmpdetails(Timereport report);
	

	

}
