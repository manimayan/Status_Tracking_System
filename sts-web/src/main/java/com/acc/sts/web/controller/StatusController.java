package com.acc.sts.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
import com.acc.sts.web.service.StatusService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/report")
@CrossOrigin(value = "http://localhost:4200")
public class StatusController {
    @Autowired
    private StatusService service;
    
    @GetMapping("/clarify")
    public @ResponseBody ResponseEntity<List<Clarify>> listEmployee() {
        log.info("Retrieving list of Employees");
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Clarify> list = null;
        try {
            list = service.listclarify();
         
            status = HttpStatus.OK;
        } catch (Exception e) {
        	log.info("Error while Fetching list of Employees", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status);
    }
    
    @GetMapping("/listnotrepabsence")
    public @ResponseBody ResponseEntity<List<Timereport>> listNotReporttimeEmpabsence() {
        log.info("Retrieving list of timereports");
        
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Timereport> list = null;
        try {
            list = service.listNotReporttimeEmpabsence();
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.info("Error while Fetching list of Tickets", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
       
        
        
        return new ResponseEntity<>(list, status);
    }
    

    
    
    @GetMapping("/notreporttimelist")
    public @ResponseBody ResponseEntity<List<Timereport>> listNotReporttimeEmployee() {
        log.info("Retrieving list of timereports");
        
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Timereport> list = null;
        try {
            list = service.listNotReporttimeEmployee();
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.info("Error while Fetching list of Tickets", e);
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
    
    
    @PostMapping("/search")
    public @ResponseBody ResponseEntity<List<Clarify>> searchTicket(@RequestBody Ticket ticket) {
        log.info("Searching ticket id");
        
       
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Clarify> list = null;
        try {
            list = service.searchTicket(ticket);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.info("Error while Fetching list of Employees", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status);
    }

    @PostMapping("/updateComment" )
    public @ResponseBody ResponseEntity<Clarification> search(@RequestBody Clarification clarification) {
        log.info("Edit Employee details");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        System.out.println(clarification);
      Clarification tick = null;
        try {
        	
            tick = service.updateComment(clarification);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Employee details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(tick, status);
    }
    

    @PostMapping("/deleteclarify/{ticketId}/{comment}" )
    public @ResponseBody ResponseEntity<List<Clarify>> deleteclarify(@PathVariable("ticketId") String ticketId,@PathVariable("comment") String comment) {
        log.info("delete clarification details");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<Clarify> tick = null;
        try {
        	
            tick = service.deleteclarify(ticketId,comment);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Employee details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(tick, status);
    }
  
    @GetMapping("/tickethistory/{ticketId}/{employeeName}/{days}")
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
    

 
 
 
  @GetMapping("/getname" )
  public @ResponseBody ResponseEntity<List<String>> getEmployeeName() {
      log.info("Edit Employee details");
    
      HttpStatus status = HttpStatus.BAD_REQUEST;
      List<String> emp = null;
      try {
      	
          emp = service.getname();
          status = HttpStatus.CREATED;
          System.out.println(emp);
      } catch (Exception e) {
          log.error("Error while saving Employee details ", e);
          status = HttpStatus.INTERNAL_SERVER_ERROR;
      }
      return new ResponseEntity<>(emp, status);
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
          log.info("Error while Fetching list of Tickets", e);
          status = HttpStatus.INTERNAL_SERVER_ERROR;
      }
      System.out.println(list.size());
      
      
      return new ResponseEntity<>(list, status);
  }

  @GetMapping("/listrep")
  public @ResponseBody ResponseEntity<List<Dailystatus>> listreportdetails() {
      log.info("Retrieving list of status");
  System.out.println("web contr of status");
      HttpStatus status = HttpStatus.NOT_FOUND;
      List<Dailystatus> list = null;
      try {
      	System.out.println("before web controller of status");
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
  	 log.info("Retrieving list of status");
  	 System.out.println("web contr of not reported");
      HttpStatus status = HttpStatus.NOT_FOUND;
      List<Dailystatus> emp = null;
      try {
      	System.out.println("before web controller of not reportedstatus");
          emp = service.listnotreportdetails();
          status = HttpStatus.OK;
              } catch (Exception e) {
      	 log.info("Error while Fetching list of status", e);
          status = HttpStatus.INTERNAL_SERVER_ERROR;
      }
      return new ResponseEntity<>(emp, status);

  }
  @GetMapping("/ticketstatus/{ticketId}/{employeeName}")
	 public @ResponseBody ResponseEntity<List<Ticketstatus>> ticketstatus(@PathVariable("ticketId") String ticketId,@PathVariable("employeeName") String employeeName) {
	     log.info("Retrieving list of Employees");

	     HttpStatus status = HttpStatus.NOT_FOUND;
	     List<Ticketstatus> list = null;
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
	     System.out.println("in web controller");
	     try {
	         list = service.listNochange();
	         System.out.println("in web controller try block");
	         status = HttpStatus.OK;
	     } catch (Exception e) {
	         log.info("Error while Fetching ", e);
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
	            log.info("Error while Fetching list of Tickets", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(list, status);
	    }
	 
	 @GetMapping("/getempname" )
	    public @ResponseBody ResponseEntity<List<Employee>>  getEmployeeDetails() {
	        log.info("Edit Employee details");
	      
	        HttpStatus status = HttpStatus.BAD_REQUEST;
	        List<Employee> emp2 = null; 
	        try {
	        	
	            emp2 = service.getempname();
	            status = HttpStatus.CREATED;
	            System.out.println(emp2);
	        } catch (Exception e) {
	            log.error("Error while saving Employee details ", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(emp2, status);
	    }
	    
	    
	   
	    @GetMapping("/getappname" )
	    public @ResponseBody ResponseEntity<List<Application>> getApplicationDetail() {
	        log.info("Edit Application details");
	      
	        HttpStatus status = HttpStatus.BAD_REQUEST;
	        List<Application> app = null;
	        try {
	        	
	            app = service.getappname();
	            status = HttpStatus.CREATED;
	            System.out.println(app);
	        } catch (Exception e) {
	            log.error("Error while saving Employee details!! ", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(app, status);
	    }
	    
	    
	    @GetMapping("/gettestername")
	    public @ResponseBody ResponseEntity<List<Employee>> getTestername() {
	        log.info("Retrieving list of Testers");
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
	    	HttpStatus status = HttpStatus.NOT_FOUND;
	        List<Reportsummary> list = null;
	        try {
	          	list = service.listreportTicket(report);
	      	  status = HttpStatus.OK;
	        } catch (Exception e) {
	            log.error("Error in retrieving Data", e);
	            status = HttpStatus.INTERNAL_SERVER_ERROR;
	        }
	        return new ResponseEntity<>(list, status);
	    }
	 
}
