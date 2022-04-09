package com.acc.sts.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acc.sts.model.Employee;
import com.acc.sts.model.Timereport;
import com.acc.sts.model.Timing;
import com.acc.sts.web.service.EmployeeService;
import com.acc.sts.web.service.TimingService;
import com.fasterxml.jackson.databind.DeserializationFeature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/timing")
@CrossOrigin(value = "http://localhost:4200")
public class TimingController {
    @Autowired
    private TimingService service;
    
 
    @PostMapping("/in-time")
    public @ResponseBody ResponseEntity<Timing> saveIntime(@RequestBody Timing intime) {
        log.info("Save Intime details");
     
        System.out.println("in web controller");
        
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Timing time=null;
        System.out.println("in web controller"+intime); 
        try {
        	
            time = service.saveIntime(intime);
            System.out.println("in web controller try bolck");
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Intime details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(time, status);
    }

    
    
    
    @PostMapping("/outTime")
    public @ResponseBody ResponseEntity<Timing> saveTiming(@RequestBody Timing outtime) {
        log.info("Save  details");
        System.out.println(outtime);
       HttpStatus status = HttpStatus.BAD_REQUEST;
        Timing time = null;
        try {
        	System.out.println("in web controller");
        	time = service.saveTiming(outtime);
            System.out.println(time.getOutTime());
            System.out.println("in webcontroller try");
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Employee timing details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(time, status);
    }
    
    
    @GetMapping("/getname" )
    public @ResponseBody ResponseEntity<List<Employee>> getEmployeeName() {
        log.info("Edit Employee details");
      
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<Employee> emp = null;
        try {
        	System.out.println("in web controller");
            emp = service.getname();
            System.out.println("in webcontroller try");
            status = HttpStatus.CREATED;
            System.out.println(emp);
        } catch (Exception e) {
            log.error("Error while saving Employee details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(emp, status);
    }
    
    
   /* @GetMapping("/timereport/{employeeId}/{fdate}/{todate}")
    public @ResponseBody ResponseEntity<List<Timereport>> submitEmpdetails(@PathVariable("employeeId") String employeeId,
    		                @PathVariable("fdate") String fdate,@PathVariable("todate") String todate)
    {
    	System.out.println("in web");
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Timereport> empDetails = null;
        System.out.println("emp name : " + employeeId);
		System.out.println("from date: " + fdate);
		System.out.println("to date: " + todate);
        try {
        	System.out.println("in web controller");
        	empDetails = service.submitEmpdetails(employeeId,fdate,todate);
        	System.out.println("in web controllers try");
        	System.out.println(empDetails);
        	 status = HttpStatus.OK;
            if (!CollectionUtils.isEmpty(empDetails)) {
                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            log.error("Error in retrieving part factors", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(empDetails, status);
    }*/
 
    @PostMapping("/timereport")
    public @ResponseBody ResponseEntity<List<Timereport>> submitEmpdetails(@RequestBody Timereport report)
    {
        log.info("Save  details");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<Timereport> empDetails = null;
        try {
        	System.out.println("in web controller");
        	empDetails = service.submitEmpdetails(report);
        	System.out.println("in web controllers try");
        	System.out.println(empDetails);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Employee timing details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(empDetails, status);
    }
 
}
