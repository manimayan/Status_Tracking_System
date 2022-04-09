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

import com.acc.sts.model.Edit;
import com.acc.sts.model.Employee;
import com.acc.sts.model.Question;
import com.acc.sts.model.Questioncheck;
import com.acc.sts.model.Security;
import com.acc.sts.model.Ticket;
import com.acc.sts.web.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/employee")
@CrossOrigin(value = "http://localhost:4200")
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    
    @PostMapping("/search")
    public @ResponseBody ResponseEntity<List<Employee> > searchResource(@RequestBody Employee employee) {
        log.info("Retrieving list of Employees");
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Employee> list =null;
        try {
            list = service.searchResource(employee);
            status = HttpStatus.CREATED;
        } catch (Exception e) {                      
            log.error("Error while checking details for login", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status); 
    }
    
    
    @PostMapping("/searchbyid")
    public @ResponseBody ResponseEntity<List<Employee> > searchResourceById(@RequestBody Employee employee) {
        log.info("Retrieving list of Employees");
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Employee> list =null;
        try {
            list = service.searchResourceById(employee);
            status = HttpStatus.CREATED;
        } catch (Exception e) {                      
            log.error("Error while checking details for login", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status); 
    }
    
    

    @GetMapping("/list")
    public @ResponseBody ResponseEntity<List<Employee>> listEmployee() {
        log.info("Retrieving list of Employees");
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Employee> list = null;
        try {
            list = service.listEmployee();
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.info("Error while Fetching list of Employees", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status);
    }
    
   
    @PostMapping("/save")
    public @ResponseBody ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        log.info("Save Employee details");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Employee emp = null;
        try {
            emp = service.saveEmployee(employee);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Employee details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(emp, status);
    }
    
    @PostMapping("/saveemp")
    public @ResponseBody ResponseEntity<Employee> saveEmployeebyuser(@RequestBody Employee employee) {
        log.info("Save Employee details");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Employee emp = null;
        try {
            emp = service.saveEmployeebyuser(employee);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Employee details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(emp, status);
    }
    
    @PostMapping("/edit" )
    public @ResponseBody ResponseEntity<Employee> editEmployee(@RequestBody Employee employee) {
        log.info("Edit Employee details");
      
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Employee emp = null;
        try {
        	
            emp = service.editEmployee(employee);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Employee details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(emp, status);
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


    @PostMapping("/change/{employeeId}/{password}/{newpassword}")
    public @ResponseBody ResponseEntity<Employee> changepass(@PathVariable("employeeId") String employeeId,
    		@PathVariable("password") String password,@PathVariable("newpassword") String newpassword) {
        log.info("change password details");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Employee emp = null;
        try {
            emp = service.changepass(employeeId,password,newpassword);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while changing password details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(emp, status);
    }
    
    @PostMapping("/reset/{employeeId}/{newpassword}")
    public @ResponseBody ResponseEntity<Employee> resetpass(@PathVariable("employeeId") String employeeId,
    		@PathVariable("newpassword") String newpassword) {
        log.info("change password details");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Employee emp = null;
        try {
            emp = service.resetpass(employeeId,newpassword);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while changing password details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(emp, status);
    }
    
    
    @GetMapping("/supervisorlist")
    public @ResponseBody ResponseEntity<List<String>> supervisorlist()
    {
    	log.info("fetching supervisor details");
    	HttpStatus status=HttpStatus.BAD_REQUEST;
    	List<String> supervisor = null;
        try {
        	supervisor = service.supervisorlist();
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.info("Error while Fetching list of Supervisor", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(supervisor,status);
    	
    }
    
    @PostMapping("/getmodify/{employeeId}")
    public @ResponseBody ResponseEntity<Employee> listreportTicket(@PathVariable("employeeId") String employeeId) {
    	log.info("Retrieving list of Tickets");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Employee list = null;
        try {
      	list = service.listmodify(employeeId);
      	  status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error in retrieving Data", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status);
    }
    
    
    @GetMapping("/getquestion" )
    public @ResponseBody ResponseEntity<List<Question>>  getSecurityDetails() {
        log.info("list Employee details");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<Question> ques = null; 
        try {
        	
        	ques = service.getQuestion();
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Employee details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(ques, status);
    }
    
    @PostMapping("/getdetails" )
    public @ResponseBody ResponseEntity<List<Questioncheck>>  getSecurityDetails1(@RequestBody List<Questioncheck> answer) {
        log.info("list Employee details");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<Questioncheck> ques = null; 
        try {
        	
        	ques = service.getSecurityDetails1(answer);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Employee details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(ques, status);
    }

    @PostMapping("/security")
    public @ResponseBody ResponseEntity<Security> saveSecurity(@RequestBody Security security) {
    	log.info("Saving Security details");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Security sec = null;
        try {
        	sec = service.saveSecurity(security);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Security details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(sec, status);
    }
    
    @PostMapping("/securitycheck")
    public @ResponseBody ResponseEntity<List<Security> > securityCheck (@RequestBody Security security) {
        log.info("Retrieving Security details");
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Security> list =null;
        try {
            list = service.securityCheck(security);
            status = HttpStatus.CREATED;
        } catch (Exception e) {                      
            log.error("Error while retrieving Security details", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status); 
    }
    
    @GetMapping("/listquestion/{empId}")
    public @ResponseBody ResponseEntity<List<Questioncheck>> listticketdetails(@PathVariable("empId") String empId) {
        log.info("Retrieving list of Employees");
    System.out.println("web contr");
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Questioncheck> list = null;
        try {
            list = service.listquestiondetails(empId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.info("Error while Fetching list of Employees", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status);
    }
    
    @PostMapping("/getSaveans" )
    public @ResponseBody ResponseEntity<List<Questioncheck>>  getSaveans(@RequestBody List<Questioncheck> answer) {
        log.info("list Employee details");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<Questioncheck> ques = null; 
        System.out.println("web controller");
        try {
        	
        	ques = service.getSaveans(answer);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Employee details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(ques, status);
    }
    
    @GetMapping("/getemployee")
    public @ResponseBody ResponseEntity<List<Employee>> getEmployee() {
        log.info("Retrieving list of tickets");
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Employee> list = null;
        try {
            list = service.getEmployee();
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.info("Error while Fetching list of Employees", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status);
    }
    
   /* @PostMapping("/deleteemp/{empId}" )
    public @ResponseBody ResponseEntity<List<Questioncheck>>  deleteEmp(@RequestBody List<Questioncheck> answer) {
        log.info("list Employee details");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<Questioncheck> ques = null; 
        System.out.println("web controller");
        System.out.println("wwwwwwwwwwwwwwww" +answer);
        try {
        	ques = service.deleteEmp(answer);
        	 System.out.println("gggggggggggggggggg"+ques);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Error while saving Employee details ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(ques, status);
    }*/
    
    @GetMapping("/deleteemp/{empId}")
    public @ResponseBody ResponseEntity<List<Questioncheck>> deleteEmp(@PathVariable("empId") String empId) {
        log.info("Deleting security questions for a particular employee ID");
    System.out.println("web contr");
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Questioncheck> list = null;
        try {
            list = service.deleteEmp(empId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.info("Error while Fetching list of Employees", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status);
    }
    
    @GetMapping("/admin")
    public @ResponseBody ResponseEntity<List<Employee>> AdminEmployee() {
        log.info("Retrieving list of Employees");
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Employee> list = null;
        try {
            list = service.AdminEmployee();
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.info("Error while Fetching list of Employees", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(list, status);
    }
    
}
