package com.acc.sts.core.repository;

import java.util.Date;
import java.util.List;

import com.acc.sts.model.Employee;
import com.acc.sts.model.Timereport;
import com.acc.sts.model.Timing;

public interface TimingRepository {

    Timing saveTiming(Timing outtime);

	Timing saveIntime(Timing intime);

	List<Employee> getname();

	//List<Timereport> submitEmpdetails(String empId, String fdate, String todate);
	
	List<Timereport> submitEmpdetails(Timereport report);

}
