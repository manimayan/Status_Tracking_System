package com.acc.sts.web.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.acc.sts.model.Application;
import com.acc.sts.model.Clarify;
import com.acc.sts.model.Closed;
import com.acc.sts.model.DeleteTicket;
import com.acc.sts.model.Documentation;
import com.acc.sts.model.Edit;
import com.acc.sts.model.Employee;
import com.acc.sts.model.Homeadmin;
import com.acc.sts.model.Homedoc;
import com.acc.sts.model.ReassignTicket;
import com.acc.sts.model.Releasetic;
import com.acc.sts.model.Ticket;
import com.acc.sts.model.Updatestatus;
import com.acc.sts.model.UploadTicket;
import com.acc.sts.model.Workprogress;
import com.acc.sts.web.service.TicketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ticket")
@CrossOrigin(value = "http://localhost:4200")
public class TicketController {
    @Autowired
    private TicketService service;

    @GetMapping("/list")
    public @ResponseBody ResponseEntity<List<UploadTicket>> listUploadTicket() {
        log.info("Retrieving list of Tickets");
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<UploadTicket> list = null;
        try {
            list = service.listUploadTicket();
 
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.info("Error while Fetching list of Employees", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status);
    }

    
    @GetMapping("/listempname" )
    public @ResponseBody ResponseEntity<List<Employee>> listEmpName() {
        log.info("list Employee details");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<Employee> emp = null;
        try {
        	emp = service.listEmpName();
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Employee details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(emp, status);
    }

@PostMapping("/update/{employeeId}/{ticketId}/{applicationName}")
public @ResponseBody ResponseEntity<Integer> ReassignUpdate(@PathVariable String employeeId,
		  @PathVariable String ticketId,@PathVariable String applicationName ) {
	  log.info("Assigning Tickets");
	  HttpStatus status = HttpStatus.BAD_REQUEST;
	  int response = 0;
	  try {
  	  response = service.AssignUpdate(employeeId,ticketId,applicationName);
  	  status = HttpStatus.CREATED;
	  } catch (Exception e) {
        log.error("Error in Assigning Tickets", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
	  }
	  return new ResponseEntity<>(response, status);
	}
@GetMapping("/listpri/{empId}")
public @ResponseBody ResponseEntity<List<Edit>> listticketdetails(@PathVariable("empId") String empId) {
    log.info("Retrieving list of Employees");
System.out.println("web contr");
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<Edit> list = null;
    try {
        list = service.listticketdetails(empId);
        status = HttpStatus.OK;
    } catch (Exception e) {
        log.info("Error while Fetching list of Employees", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(list, status);
}

@PostMapping("/updatepri/{empId}/{ticketId}/{newPriority}")
  public @ResponseBody ResponseEntity<Ticket> update(@PathVariable("empId") String empId,@PathVariable("ticketId") String ticketId,
		  @PathVariable("newPriority") int newPriority) {
	 log.info("Update Employee details");
      HttpStatus status = HttpStatus.BAD_REQUEST;
      Ticket pri = null;
      System.out.println("before update web controller");
      try {
    	  System.out.println("in update web controller");
    	  pri = service.update(empId,ticketId,newPriority);
    	  System.out.println("in core controller try");
    	  status = HttpStatus.CREATED;
      } catch (Exception e) {
          log.error("Error in updating Data", e);
          status = HttpStatus.INTERNAL_SERVER_ERROR;
      }
      return new ResponseEntity<>(pri, status);
  }
@GetMapping("/listRelease")
public @ResponseBody ResponseEntity<List<Releasetic>> listRelease() {
    log.info("Retrieving list of Employees");

    HttpStatus status = HttpStatus.NOT_FOUND;
    List<Releasetic> list = null;
    try {
        list = service.listRelease();
        status = HttpStatus.OK;
        System.out.println(list);
    } catch (Exception e) {
        log.info("Error while Fetching list of Employees", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(list, status);
}

@PostMapping("/update1/{ticketId}/{releaseTicket}")
public @ResponseBody ResponseEntity <Ticket> update1(@PathVariable("ticketId") String ticketId,
		  @PathVariable("releaseTicket") String releaseTicket) {
    log.info("Retrieving list of Employees");

    HttpStatus status = HttpStatus.NOT_FOUND;
  Ticket list = null;
    try {
        list = service.update1(ticketId,releaseTicket);
        status = HttpStatus.OK;
        System.out.println(list);
    } catch (Exception e) {
        log.info("Error while Fetching list of Employees", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(list, status);
}
@PostMapping("/delete/{ticketid}")
public @ResponseBody ResponseEntity<Ticket> submitEmpdetails(@PathVariable("ticketid") String ticketid) {
    log.info("Save Employee details");

    HttpStatus status = HttpStatus.BAD_REQUEST;
    Ticket empDetails = null;
    try {
    	 System.out.println("in core controller");
     	  empDetails = service.deleteTick(ticketid);
     	  System.out.println("in core controller try");
     	  status = HttpStatus.CREATED;
    } catch (Exception e) {
    	log.error("Error in retrieving Data", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(empDetails, status);
}

@GetMapping("/listdel")
public @ResponseBody ResponseEntity<List<DeleteTicket>> listDeleteTicket() {
    log.info("Retrieving list of Tickets");

    HttpStatus status = HttpStatus.NOT_FOUND;
    List<DeleteTicket> list = null;
  
    try {
    	  System.out.println("in web c b4 try");
        list = service.listDeleteTicket();
        System.out.println(list);
        status = HttpStatus.OK;
    } catch (Exception e) {
        log.info("Error while Fetching list of Tickets", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(list, status);
}


@PostMapping("/Add/{appId}/{employeeId}")
public @ResponseBody ResponseEntity<Ticket> saveAddticket(@RequestBody Ticket ticket,@PathVariable("appId") String appId,@PathVariable("employeeId") String employeeId) {
    log.info("Save Ticket details");
    HttpStatus status = HttpStatus.BAD_REQUEST;
    Ticket at = null;
    try {
        at = service.saveAddticket(ticket,appId,employeeId);
        status = HttpStatus.CREATED;
    } catch (Exception e) {
        log.error("Error while saving Ticket details ", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(at, status);
}

@GetMapping("/getname" )
public @ResponseBody ResponseEntity<List<Application>> getAppName() {
    log.info("App details");
    HttpStatus status = HttpStatus.BAD_REQUEST;
    List<Application> an = null;
    try {
        an = service.getname();
        status = HttpStatus.CREATED;
        System.out.println("in web cont after"+an);
    } catch (Exception e) {
        log.error("Error while fetching App details ", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(an, status);
}

@GetMapping("/listdetails")
public @ResponseBody ResponseEntity<List<ReassignTicket>> listReassignTicket() {
    log.info("Retrieving list of Tickets");
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<ReassignTicket> listdetails = null;
    try {
        listdetails = service.listReassignTicket();
        status = HttpStatus.OK;
    } catch (Exception e) {
        log.info("Error while Fetching list of Tickets", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(listdetails, status);
}


@GetMapping("/getemployeename" )
public @ResponseBody ResponseEntity<List<Employee>>  getEmployeeDetails() {
    log.info("list Employee details");
    HttpStatus status = HttpStatus.BAD_REQUEST;
    List<Employee> empl = null; 
    try {
    	
        empl = service.getEmpName();
        status = HttpStatus.CREATED;
    } catch (Exception e) {
        log.error("Error while saving Employee details ", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(empl, status);
}

@PostMapping("/reassignticket/{employeeId}/{ticketId}")
public @ResponseBody ResponseEntity<Ticket> ReassignUpdate(@PathVariable("employeeId") String employeeId,
		  @PathVariable("ticketId") String ticketId) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    Ticket empDetails = null;
    try {
  	  empDetails = service.ReassignUpdate(employeeId,ticketId);
  	  status = HttpStatus.OK;
    } catch (Exception e) {
        log.error("Error in Reassigning Tickets", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(empDetails, status);
}


@GetMapping("/mytickets/{empId}")
public @ResponseBody ResponseEntity<List<Homeadmin>> listHomeadmin(@PathVariable("empId") String empId) {
    log.info("Retrieving list of my ticket c.controller");
System.out.println("In core controller");
    HttpStatus status = HttpStatus.NOT_FOUND;
  List<Homeadmin> list2=null;
    try {
    	System.out.println("in core controller before");
        list2 = service.listHomeadmin(empId);
      
        status = HttpStatus.OK;
    } catch (Exception e) { // Spring 2.0 throws this one
    	e.printStackTrace();
    	log.info("Error while Fetching list of tickets", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR; }  
    System.out.println(list2);
    return new ResponseEntity<>(list2, status);
}

@GetMapping("/homedoc/{empId}")
public @ResponseBody ResponseEntity<List<Homedoc>> listHomedoc(@PathVariable("empId") String empId) {
    log.info("Retrieving list of Homedoc tickets");
System.out.println("in web controller before ");
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<Homedoc> list1 = null;
    try {
        list1 = service.listHomedoc(empId);
        System.out.println("in web controller  after");
        status = HttpStatus.OK;
       
    } catch (Exception e) {
        log.info("Error while Fetching list of Ticket details", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    System.out.println("last"+list1);
    return new ResponseEntity<>(list1, status);
}

@PostMapping("/updateresponse/{ticketId}/{comment}/{eresponse}" )
public @ResponseBody ResponseEntity<List<Clarify>> updateresponse(@PathVariable("ticketId") String ticketId,@PathVariable("comment") String comment,@PathVariable("eresponse") String eresponse) {
    log.info("delete clarification details");
    HttpStatus status = HttpStatus.BAD_REQUEST;
    List<Clarify> tick = null;
    try {
    	
        tick = service.updateresponse(ticketId,comment,eresponse);
        status = HttpStatus.CREATED;
    } catch (Exception e) {
        log.error("Error while saving Employee details ", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(tick, status);
}


@PostMapping("/save/{ticket_id}")
public @ResponseBody ResponseEntity<Documentation> saveDocument(@RequestBody Documentation documentation,@PathVariable("ticket_id") String ticket_id) {
    log.info("Save Documentation details");

    HttpStatus status = HttpStatus.BAD_REQUEST;
    Documentation docu = null;
    try {
    	System.out.println("before web controller"+documentation);
        docu = service.saveDocument(documentation,ticket_id);
        System.out.println("after web controller");
        status = HttpStatus.CREATED;
    } catch (Exception e) {
        log.error("Error while saving Documentation details ", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(docu, status);
}

@GetMapping("/getapplication")
public @ResponseBody ResponseEntity<List<Application>> getAppname() {
    log.info("Retrieving list of Employees");
   System.out.println("in home web controller for application");
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<Application> list = null;
    try {
        list = service.getAppname();
        status = HttpStatus.OK;
    } catch (Exception e) {
        log.info("Error while Fetching list of Employees", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(list, status);
}

@GetMapping("/gettestername")
public @ResponseBody ResponseEntity<List<Employee>> getTestername() {
    log.info("Retrieving list of Employees");
   System.out.println("in home web controller for testername");
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<Employee> list = null;
    try {
        list = service.getTestername();
        status = HttpStatus.OK;
    } catch (Exception e) {
        log.info("Error while Fetching list of Employees", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(list, status);
}

/*@GetMapping("/list")
public @ResponseBody ResponseEntity<List<Updatestatus>> listUpdatestatus() {
    log.info("Retrieving list of status details");
System.out.println("in web controller for list");
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<Updatestatus> list = null;
    try {
        list = service.listUpdatestatus();
        System.out.println("in web controller try for list");
        status = HttpStatus.OK;
    } catch (Exception e) {
        log.info("Error while Fetching list of status details", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(list, status);
}*/

@PostMapping("/save")
public @ResponseBody ResponseEntity<Updatestatus> saveStatus(@RequestBody Updatestatus updateStatus) {
    log.info("Save Employee details");

    HttpStatus status = HttpStatus.BAD_REQUEST;
    Updatestatus sts = null;
    try {
    	System.out.println("in web controller");
    	sts = service.saveStatus(updateStatus);
    	System.out.println("in web controller try");
    	System.out.println(sts);
        status = HttpStatus.CREATED;
    } catch (Exception e) {
        log.error("Error while saving Employee details ", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(sts, status);
}

@GetMapping("/myclosed/{empId}")
public @ResponseBody ResponseEntity<List<Closed>> listClosed(@PathVariable("empId") String empId) {
    log.info("Retrieving list of Closed tickets");
System.out.println("in web controller befor ");
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<Closed> list1 = null;
    try {
        list1 = service.listClosed(empId);
        System.out.println("in web controller  after");
        status = HttpStatus.OK;
       
    } catch (Exception e) {
        log.info("Error while Fetching list of ClosedTickets", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    System.out.println("last"+list1);
    return new ResponseEntity<>(list1, status);
}

@GetMapping("/allclosed")
public @ResponseBody ResponseEntity<List<Closed>> listallClosed() {
    log.info("Retrieving list of Closed tickets");
System.out.println("in web controller befor ");
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<Closed> list1 = null;
    try {
        list1 = service.listAllClosed();
        System.out.println("in web controller  after");
        status = HttpStatus.OK;
       
    } catch (Exception e) {
        log.info("Error while Fetching list of ClosedTickets", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    System.out.println("last"+list1);
    return new ResponseEntity<>(list1, status);
}

@PostMapping(value="/uploadTo/{employeeName}",consumes ="multipart/form-data",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public @ResponseBody ResponseEntity<List<UploadTicket>> saveEmployee(@RequestPart("uploadFile") MultipartFile file,@PathVariable("employeeName") String employeeName){
log.info("Save Employee details");
System.out.println("web controller upload");
HttpStatus status = HttpStatus.BAD_REQUEST;
List<UploadTicket> emp = null;
System.out.println(file);
String name=file.getOriginalFilename();
System.out.println(name);
try{
	InputStream in = (InputStream) file.getInputStream();
	System.out.println("In"+in);
	Workbook workbook  = new XSSFWorkbook(in);

	System.out.println(workbook.getSheetAt(0));
	System.out.println("web2 controller");


	List<UploadTicket> tUpload = service.readData(workbook.getSheetAt(0),employeeName);
	
	emp = service.onchange(tUpload);
	status = HttpStatus.CREATED;
} catch (Exception e) {
	log.error("Error while saving Employee details ", e);
	e.printStackTrace();
	status = HttpStatus.INTERNAL_SERVER_ERROR;
}

return new ResponseEntity<>(emp, status);


}
@GetMapping("/ticketdump")
public @ResponseBody ResponseEntity<List<UploadTicket>> listTicketDump() {
    log.info("Retrieving Ticket Dump details");
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<UploadTicket> list = null;
    try {
        list = service.listTicketDump();
        status = HttpStatus.OK;
    } catch (Exception e) {
      log.error("Error while Fetching Ticket Dump details", e);
      status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(list, status);
}

@PostMapping("/savetester")
public @ResponseBody ResponseEntity<Updatestatus> saveStatusTester(@RequestBody Updatestatus updateStatus) {
    log.info("Save Employee details");

    HttpStatus status = HttpStatus.BAD_REQUEST;
    Updatestatus sts = null;
    try {
    	
    	sts = service.saveStatusTester(updateStatus);

        status = HttpStatus.CREATED;
    } catch (Exception e) {
        log.error("Error while saving Employee details ", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(sts, status);
}

@GetMapping("/testerticket/{empName}")
public @ResponseBody ResponseEntity<List<Homeadmin>> listTesterHome(@PathVariable("empName") String empName) {
log.info("Retrieving list of my ticket c.controller");
System.out.println("In core controller");
HttpStatus status = HttpStatus.NOT_FOUND;
List<Homeadmin> list2=null;
try {
	System.out.println("in core controller before");
    list2 = service.listTesterHome(empName);
  
    status = HttpStatus.OK;
} catch (Exception e) { // Spring 2.0 throws this one
	e.printStackTrace();
	log.info("Error while Fetching list of tickets", e);
    status = HttpStatus.INTERNAL_SERVER_ERROR; }  
System.out.println(list2);
return new ResponseEntity<>(list2, status);
}

@PostMapping("/checkpriority/{ticketId}/{employeeId}")
public @ResponseBody ResponseEntity<List<Integer>> checkpriority(@PathVariable("ticketId") String ticketId,@PathVariable("employeeId") String employeeId) {
     log.info("Save Employee details");
     HttpStatus status = HttpStatus.BAD_REQUEST;
     List<Integer> sts = null;
     try {
  	   System.out.println("in core controller");
         sts = service.checkpriority(ticketId,employeeId);
         System.out.println("in core controller try");
         status = HttpStatus.CREATED;
     } catch (Exception e) {
         log.error("Error while saving Employee details ", e);
         status = HttpStatus.INTERNAL_SERVER_ERROR;
     }
     return new ResponseEntity<>(sts, status);
  }

@PostMapping("/checkeditpriority/{employeeId}")
public @ResponseBody ResponseEntity<List<Integer>> checkeditpriority(@PathVariable("employeeId") String employeeId) {
     log.info("Save Employee details");
     HttpStatus status = HttpStatus.BAD_REQUEST;
     List<Integer> sts = null;
     try {
  	   System.out.println("in core controller");
         sts = service.checkeditpriority(employeeId);
         System.out.println("in core controller try");
         status = HttpStatus.CREATED;
     } catch (Exception e) {
         log.error("Error while saving Employee details ", e);
         status = HttpStatus.INTERNAL_SERVER_ERROR;
     }
     return new ResponseEntity<>(sts, status);
  }

@GetMapping("/getdevelopername")
public @ResponseBody ResponseEntity<List<Employee>> getdevelopername() {
    log.info("Retrieving list of Employees");
   System.out.println("in home web controller for testername");
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<Employee> list = null;
    try {
        list = service.getdevelopername();
        status = HttpStatus.OK;
    } catch (Exception e) {
        log.info("Error while Fetching list of Employees", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(list, status);
}


@PostMapping("/getworkprogress/{employeeId}")
public @ResponseBody ResponseEntity<List<Object>> getWorkProgress(@PathVariable("employeeId") String employeeId) {
	log.info("list Employee Work Progress");
    HttpStatus status = HttpStatus.BAD_REQUEST;
    List<Object> workprogress = null;
    try {
    	 workprogress = service.getWorkProgress(employeeId);
         status = HttpStatus.CREATED;
     } catch (Exception e) {
         log.error("Error while listing Employee Work Progress ", e);
         status = HttpStatus.INTERNAL_SERVER_ERROR;
     }
     return new ResponseEntity<>(workprogress, status);
}

}