package com.acc.sts.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acc.sts.core.repository.TimingRepository;
import com.acc.sts.core.service.TimingService;
import com.acc.sts.model.Employee;
import com.acc.sts.model.Timereport;
import com.acc.sts.model.Timing;

@Service
public class TimingServiceImpl implements TimingService {

    @Autowired
    TimingRepository repository;

    
    @Override
    public Timing saveIntime(Timing intime) {
        return repository.saveIntime(intime);
    }

    @Override
    public Timing saveTiming(Timing outtime) {
        return repository.saveTiming(outtime);
    }

    @Override
	public List<Employee> getname() {
		// TODO Auto-generated method stub
    	System.out.println("in core service");
		return repository.getname();
	}

    
   /* @Override
    public List<Timereport> submitEmpdetails(String employeeId,String fdate,String todate) {
    	System.out.println("in core serviceimpl");
         
        List<Timereport> a=		repository.submitEmpdetails(employeeId,fdate,todate);
        System.out.println("checking service"+a);
        return a;
    }*/
    
    @Override
    public List<Timereport> submitEmpdetails(Timereport report) {
    	System.out.println("in core serviceimpl");
         
        List<Timereport> a=		repository.submitEmpdetails(report);
        System.out.println("checking service"+a);
        return a;
    }
}
