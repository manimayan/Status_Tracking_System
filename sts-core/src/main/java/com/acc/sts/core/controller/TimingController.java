package com.acc.sts.core.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acc.sts.core.service.EmployeeService;
import com.acc.sts.core.service.TimingService;
import com.acc.sts.model.Employee;
import com.acc.sts.model.Timereport;
import com.acc.sts.model.Timing;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/timing")
public class TimingController {
    @Autowired
    private TimingService service;

    @PostMapping("/in-time")
    public @ResponseBody ResponseEntity<Timing> saveIntime(@RequestBody Timing intime) {
        log.info("Save  details");
        System.out.println("in core controller" +intime);
         HttpStatus status = HttpStatus.BAD_REQUEST;
        Timing time = null;
        try {
            time = service.saveIntime(intime);
            System.out.println("in core controller try bolck");
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Employee timing details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(time, status);
    }

    @PostMapping("/outTime")
    public @ResponseBody ResponseEntity<Timing> saveTiming(@RequestBody Timing outtime) {
        log.info("Save timing details");

        System.out.println("in web controller"+outtime);
        
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Timing time = null;
        try {
            time = service.saveTiming(outtime);
            System.out.println("in core controller try bolck");
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving time details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(time, status);
    }
    
   @GetMapping("/getname")
    public @ResponseBody ResponseEntity<List<Employee>> getname() {
        log.info("Retrieving list of Employees");
       System.out.println("in timing core controller");
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Employee> list = null;
        try {
            list = service.getname();
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.info("Error while Fetching list of Employees", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status);
    }
   
 /* @GetMapping("/timereport/{employeeId}/{fdate}/{todate}")
      public @ResponseBody ResponseEntity<List<Timereport>> submitEmpdetails(@PathVariable("employeeId") String employeeId,
    		  @PathVariable("fdate") String fdate,@PathVariable("todate") String todate) {
          HttpStatus status = HttpStatus.NOT_FOUND;
          List<Timereport> empDetails = null;
          try {
        	  System.out.println("in core controller");
        	  empDetails = service.submitEmpdetails(employeeId,fdate,todate);
        	  System.out.println("in core controller try");
        	  System.out.println("core checking"+empDetails);
        	  status = HttpStatus.OK;
        	  Timereport t = new Timereport();
        	  t.getInTime();
          } catch (Exception e) {
        	  e.printStackTrace();
              log.error("Error in retrieving Data", e);
              status = HttpStatus.INTERNAL_SERVER_ERROR;
          }
          return new ResponseEntity<>(empDetails, status);
      }
*/
   
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
