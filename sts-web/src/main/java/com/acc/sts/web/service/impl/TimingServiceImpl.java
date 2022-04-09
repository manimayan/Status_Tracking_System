package com.acc.sts.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.acc.sts.model.Employee;
import com.acc.sts.model.Timereport;
import com.acc.sts.model.Timing;
import com.acc.sts.web.client.TimingClient;
import com.acc.sts.web.client.config.ApiOptions;
import com.acc.sts.web.service.TimingService;



@Service
public class TimingServiceImpl implements TimingService {

    @Autowired
    private TimingClient timeClient;

    @Autowired
    @Qualifier("CoreOptions")
    private ApiOptions coreOptions;

    @Autowired
    @Qualifier("coreRestTemplate")
    private RestTemplate coreRestTemplate;


    @Override
    public Timing saveIntime(Timing intime) {
    	System.out.println("in web serviceimpl"+intime); 
        return timeClient.saveIntime(intime);
    }
    
	@Override
	public Timing saveTiming(Timing outtime) {
		System.out.println("in service impl");
		return timeClient.saveTiming(outtime);
	}

	@Override
	public List<Employee> getname() {
		// TODO Auto-generated method stub
		System.out.println("in serviceimpl");
		return timeClient.getname();
	}

	
	/* @Override
	    public List<Timereport> submitEmpdetails(String employeeId,String fdate,String todate) {
		 System.out.println("in web service");
		 List<Timereport> res =  timeClient.submitEmpdetails(employeeId,fdate,todate);
		 System.out.println(res);
	         return res;
	    }*/
	
	@Override
    public List<Timereport> submitEmpdetails(Timereport report) {
	 System.out.println("in web service");
	 List<Timereport> res =  timeClient.submitEmpdetails(report);
	 System.out.println(res);
         return res;
    }
}
