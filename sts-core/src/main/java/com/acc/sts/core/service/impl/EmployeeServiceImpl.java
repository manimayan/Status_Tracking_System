package com.acc.sts.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.acc.sts.core.repository.EmployeeRepository;
import com.acc.sts.core.service.EmployeeService;
import com.acc.sts.model.Edit;
import com.acc.sts.model.Employee;
import com.acc.sts.model.Question;
import com.acc.sts.model.Questioncheck;
import com.acc.sts.model.Security;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository repository;
    
	@Override
	public List<Employee> searchResource(Employee employee) {
		 return repository.searchEmployee(employee);
	}
	
	@Override
	public List<Employee> searchResourceById(Employee employee) {
		 return repository.searchEmployeeById(employee);
	}

    @Override
    public List<Employee> listEmployee() {
        return repository.listEmployee();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.saveEmployee(employee);
    }
    
    public Employee editEmployee(Employee employee) {
        return repository.editEmployee(employee);
    }
    
    @Override
	public List<String> getname() {
		return repository.getname();
	}

    @Override
	public Employee changepass(String employeeId, String password, String newpassword) {
		return repository.changepass(employeeId,password,newpassword);
	}
    
    @Override
	public Employee resetpass(String employeeId, String newpassword) {
		return repository.resetpass(employeeId,newpassword);
	}

	@Override
	public List<String> supervisorlist() {
		return repository.supervisorlist();
	}
	
	@Override
	public Employee listmodify(String employeeId) {
		return repository.listmodify(employeeId);
	}
	
	@Override
    public Security saveSecurity(Security security) {
        return repository.saveSecurity(security);
    }
	
	@Override
	public List<Security> securityCheck(Security security) {
		 return repository.securityCheck(security);
	}

	@Override
	public List<Question> getQuestion() {
		
		List<Question> question = null;
		List<Question> queList = new ArrayList<>(); 
		try {
			question = repository.getQuestion();
		    int numberOfElements = 10;
		    Collections.shuffle(question);
			for (int i = 0; i < numberOfElements; i++) {
		        queList.add(question.get(i)); 
		    }
    }
		catch (Exception e) {
            log.info("Error while Fetching list of Employees", e);
		}
		
		 return queList;
	}

	@Override
    public List<Questioncheck> listquestiondetails(String empId) {
		List<Questioncheck> ques = null;
		List<Questioncheck> newList = new ArrayList<>(); 
		try {
			ques = repository.listquestiondetails(empId);
		    int numberOfElements = 10;
		    Collections.shuffle(ques);
		    	
			for (int i = 0; i < numberOfElements; i++) {
		        newList.add(ques.get(i)); 
		    }
    }
		catch (Exception e) {
            log.info("Error while Fetching list of Employees", e);
		}
		 return newList;
		
}
	@Override
	public List<Questioncheck> getSaveans(List<Questioncheck> answer) {
		return repository.getSaveans(answer);
	}
	
	@Override
	public List<Questioncheck> getSecurityDetails1(List<Questioncheck> answer) {
		return repository.getSecurityDetails1(answer);
	}

	@Override
	public List<Employee> getEmployee() {
		return repository.getEmployee();
	}

	/*@Override
	public List<Questioncheck> deleteEmp(List<Questioncheck> answer) {
		return repository.deleteEmp(answer);
	}
*/
	@Override
	public Employee saveEmployeebyuser(Employee employee) {
		return repository.saveEmployeebyuser(employee);
	}

	@Override
	public List<Questioncheck> deleteEmp(String empId) {
		return repository.deleteEmp(empId);
	}

	@Override
	public List<Employee> AdminEmployee() {
		return repository.AdminEmployee();
	}

}