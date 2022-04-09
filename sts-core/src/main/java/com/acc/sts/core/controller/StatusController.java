package com.acc.sts.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.acc.sts.core.service.StatusService;
import com.acc.sts.model.Application;
import com.acc.sts.model.Clarification;
import com.acc.sts.model.Clarify;
import com.acc.sts.model.Dailystatus;
import com.acc.sts.model.Dayticket;
import com.acc.sts.model.Employee;
import com.acc.sts.model.Nochange;
import com.acc.sts.model.NotReportEmployee;
import com.acc.sts.model.Reportsummary;
import com.acc.sts.model.Ticket;
import com.acc.sts.model.Tickethistory;
import com.acc.sts.model.Ticketstatus;
import com.acc.sts.model.Timereport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/report")
public class StatusController {
	   @Autowired
	    private StatusService service;
	   
	   @GetMapping("/clarify")
	    public @ResponseBody ResponseEntity<List<Clarify>> Listclarify() {
		 log.info("Retrieving list of Employees");
	        HttpStatus status = HttpStatus.NOT_FOUND;
	        List<Clarify> list = null;
	        try {
	            list = service.listclarify();
	            status = HttpStatus.OK;
	        } catch (Exception e) {
	            log.info("Error while Fetching list of tickets", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(list, status);
	    }
	   
	 
	   @GetMapping("/timelistnotrep")
	    public @ResponseBody ResponseEntity<List<Timereport>> timelistnotreportdetails() {
	        log.info("Retrieving list of timereportdetails");
	
	        HttpStatus status = HttpStatus.NOT_FOUND;
	        List<Timereport> list = null;
	        try {
	            list = service.timelistnotreportdetails();
	            status = HttpStatus.OK;
	        } catch (Exception e) {
	            log.info("Error while Fetching list of timereport", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(list, status);
	    }
	    
	    
	    
	    @GetMapping("/timelistnotrepabsence")
	    public @ResponseBody ResponseEntity<List<Timereport>> timelistnotreportabsencedetails() {
	        log.info("Retrieving list of timereportdetails");
	
	        HttpStatus status = HttpStatus.NOT_FOUND;
	        List<Timereport> list = null;
	        try {
	            list = service.timelistnotreportabsencedetails();
	            status = HttpStatus.OK;
	        } catch (Exception e) {
	            log.info("Error while Fetching list of timereport", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(list, status);
	    }

	    
	    @GetMapping("/pendingresponse/{empId}")
	    public @ResponseBody ResponseEntity<List<Clarify>> listPendingResponse(@PathVariable("empId") String empId) {
	    	log.info("Retrieving list ofPending response for clarification");
	 
	        HttpStatus status = HttpStatus.NOT_FOUND;
	        List<Clarify> list = null;
	        try {
	        	 list = service.listPendingResponse(empId);
	            status = HttpStatus.OK;
	        } catch (Exception e) {
	        	log.info("Error while Fetching list of Pending response for clarification", e);
	            
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(list, status);
	    }
	    
	    @PostMapping("/search" )
	    public @ResponseBody ResponseEntity< List<Clarify>> searchTicket(@RequestBody Ticket ticket1) {
	        log.info("searching ticket details");
	        HttpStatus status = HttpStatus.BAD_REQUEST;
	        List<Clarify> tick = null;    
	        try {
	        	
	            tick = service.searchTicket(ticket1);
	            status = HttpStatus.CREATED;
	        } catch (Exception e) {
	            log.error("Error while searching the ticket details ", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(tick, status);

	    }
	    
	   @PostMapping("/updateComment" )
	    public @ResponseBody ResponseEntity<Clarification> search(@RequestBody Clarification clarify) {
	        log.info("Edit Employee details");
	      
	        HttpStatus status = HttpStatus.BAD_REQUEST;
	        Clarification tick = null;
	        try {
	        	
	            tick = service.updateComment(clarify);
	            status = HttpStatus.CREATED;
	        } catch (Exception e) {
	            log.error("Error while updating supervisor details ", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(tick, status);

	   }
	   
	 
	   @PostMapping("/deleteclarify/{ticketId}/{comment}" )
	    public @ResponseBody ResponseEntity<List<Clarify>> deleteclarify(@PathVariable("ticketId") String ticketId,@PathVariable("comment") String comment) {
	        log.info("Edit Employee details");
	      
	        HttpStatus status = HttpStatus.BAD_REQUEST;
	       List<Clarify> tick=null ;
	        try {
	        	
	            tick = service.deleteclarify(ticketId,comment);
	            status = HttpStatus.CREATED;
	        } catch (Exception e) {
	            log.error("Error while updating supervisor details ", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(tick, status);
	    }

@GetMapping("/gettickname")
    public @ResponseBody ResponseEntity<List<Ticket>> getTicket() {
        log.info("Retrieving list of tickets");
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Ticket> list = null;
        try {
            list = service.gettickname();
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.info("Error while Fetching list of Employees", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status);
    }
    
	   @GetMapping("/getname")
	    public @ResponseBody ResponseEntity<List<String>> getname() {
	        log.info("Retrieving list of Employees");
	        HttpStatus status = HttpStatus.NOT_FOUND;
	        List<String> list = null;
	        try {
	            list = service.getname();
	            status = HttpStatus.OK;
	        } catch (Exception e) {
	            log.info("Error while Fetching list of Employees", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(list, status);
	    }
	    @GetMapping("/Tickethistory/{ticketId}/{employeeName}/{days}")
	    public @ResponseBody ResponseEntity<List<Tickethistory>> submit(@PathVariable("ticketId") String ticketId,@PathVariable("employeeName") String employeeName,
	  		@PathVariable("days") int days){
	        HttpStatus status = HttpStatus.NOT_FOUND;
	        List<Tickethistory> empDetails = null;
	        try {
	      	  empDetails = service.submit(ticketId,employeeName,days);
	      	  status = HttpStatus.OK;
	        } catch (Exception e) {
	            log.error("Error in retrieving Data", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(empDetails, status);
	    }
	    
	    @GetMapping("/notreportlist")
	    public @ResponseBody ResponseEntity<List<NotReportEmployee>> listNotReportEmployee() {
	        log.info("Retrieving list of Tickets");
	       
	        HttpStatus status = HttpStatus.NOT_FOUND;
	        List<NotReportEmployee> list = null;
	        try {
	            list = service.listNotReportEmployee();
	            
	            status = HttpStatus.OK;
	        } catch (Exception e) {
	            log.info("Error while Fetching list of Ticketss", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        
	        return new ResponseEntity<>(list, status);
	    }
	    @GetMapping("/listrep")
	    public @ResponseBody ResponseEntity<List<Dailystatus>> listreportdetails() {
	        log.info("Retrieving list of statusdetails");
	
	        HttpStatus status = HttpStatus.NOT_FOUND;
	        List<Dailystatus> list = null;
	        try {
	            list = service.listreportdetails();
	            status = HttpStatus.OK;
	        } catch (Exception e) {
	            log.info("Error while Fetching list of status", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(list, status);
	    }
	    @GetMapping("/listnotrep")
	    public @ResponseBody ResponseEntity<List<Dailystatus>> listnotreportdetails() {
	        log.info("Retrieving list of statusdetails");
	
	        HttpStatus status = HttpStatus.NOT_FOUND;
	        List<Dailystatus> list = null;
	        try {
	            list = service.listnotreportdetails();
	            status = HttpStatus.OK;
	        } catch (Exception e) {
	            log.info("Error while Fetching list of status", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(list, status);
	    }
	    @GetMapping("/ticketstatus/{ticketId}/{employeeName}")
		   public @ResponseBody ResponseEntity<List<Ticketstatus>> ticketstatus(@PathVariable("ticketId") String ticketId,@PathVariable("employeeName") String employeeName) {
		       log.info("Retrieving list of Employees");

		       HttpStatus status = HttpStatus.NOT_FOUND;
		       List<Ticketstatus>list =null;
		       try {
		           list = service.ticketstatus(ticketId,employeeName);
		           status = HttpStatus.OK;
		           System.out.println(list);
		       } catch (Exception e) {
		           log.info("Error while Fetching list of Employees", e);
		           status = HttpStatus.INTERNAL_SERVER_ERROR;
		       }
		       return new ResponseEntity<>(list, status);
		   }
		   @GetMapping("/listNo")
		   public @ResponseBody ResponseEntity<List<Nochange>> listNochange() {
		       log.info("Retrieving list ");

		       HttpStatus status = HttpStatus.NOT_FOUND;
		       List<Nochange> list = null;
		       System.out.println("in core controller...");
		       try {
		           list = service.listNochange();
		           System.out.println(list);
		           status = HttpStatus.OK;
		       } catch (Exception e) {
		           log.info("Error while Fetching list ", e);
		           status = HttpStatus.INTERNAL_SERVER_ERROR;
		       }
		       return new ResponseEntity<>(list, status);
		   }
		   @GetMapping("/daylist")
		    public @ResponseBody ResponseEntity<List<Dayticket>> listDayticket() {
		        log.info("Retrieving list of Tickets");
		        HttpStatus status = HttpStatus.NOT_FOUND;
		        List<Dayticket> list = null;
		        try {
		            list = service.listDayticket();
		            status = HttpStatus.OK;
		        } catch (Exception e) {
		            log.info("Error while Fetching list of Ticketss", e);
		            status = HttpStatus.INTERNAL_SERVER_ERROR;
		        }
		        return new ResponseEntity<>(list, status);
		    }
		   
		   @GetMapping("/getempname")
		    public @ResponseBody ResponseEntity<List<Employee>> getempname() {
		        log.info("Retrieving list of Employees");
		        HttpStatus status = HttpStatus.NOT_FOUND;
		        List<Employee> emp = null;
		        try {
		        	emp = service.getempname();
		            status = HttpStatus.OK;
		        } catch (Exception e) {
		            log.info("Error while Fetching list of Employees", e);
		            status = HttpStatus.INTERNAL_SERVER_ERROR;
		        }
		        return new ResponseEntity<>(emp, status);
		    }
		    
		    @GetMapping("/getappname")
		    public @ResponseBody ResponseEntity<List<Application>> getappname() {
		        log.info("Retrieving list of Application");
		        HttpStatus status = HttpStatus.NOT_FOUND;
		        List<Application> app = null;
		        try {
		        	app = service.getappname();
		            status = HttpStatus.OK;
		        } catch (Exception e) {
		            log.info("Error while Fetching list of Application", e);
		            status = HttpStatus.INTERNAL_SERVER_ERROR;
		        }
		        return new ResponseEntity<>(app, status);
		    }
		    
		    @GetMapping("/gettestername")
		    public @ResponseBody ResponseEntity<List<Employee>> getTestername() {
		        log.info("Retrieving list of Employees");
		        HttpStatus status = HttpStatus.NOT_FOUND;
		        List<Employee> lists = null;
		        try {
		            lists = service.getTestername();
		            status = HttpStatus.OK;
		        } catch (Exception e) {
		            log.info("Error while Fetching list of Employees", e);
		            status = HttpStatus.INTERNAL_SERVER_ERROR;
		        }
		        return new ResponseEntity<>(lists, status);
		    }
		   
		    @PostMapping("/reportlist")
		    public @ResponseBody ResponseEntity<List<Reportsummary>> listreportTicket(@RequestBody Reportsummary report) {
		          log.info("Retrieving list of Tickets");
		        HttpStatus status = HttpStatus.BAD_REQUEST;
		        List<Reportsummary> list = null;
		        try {
		      	list = service.listreportTicket(report);
		        System.out.println(list);
		      	  status = HttpStatus.CREATED;
		        } catch (Exception e) {
		            log.error("Error in retrieving Data", e);
		            status = HttpStatus.INTERNAL_SERVER_ERROR;
		        }
		        return new ResponseEntity<>(list, status);
		    }
}
