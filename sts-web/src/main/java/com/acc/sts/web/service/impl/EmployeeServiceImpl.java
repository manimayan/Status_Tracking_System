package com.acc.sts.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.acc.sts.model.Edit;
import com.acc.sts.model.Employee;
import com.acc.sts.model.Question;
import com.acc.sts.model.Questioncheck;
import com.acc.sts.model.Security;
import com.acc.sts.model.Ticket;
import com.acc.sts.web.client.EmployeeClient;
import com.acc.sts.web.client.config.ApiOptions;
import com.acc.sts.web.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeClient empClient;

    @Autowired
    @Qualifier("CoreOptions")
    private ApiOptions coreOptions;

    @Autowired
    @Qualifier("coreRestTemplate")
    private RestTemplate coreRestTemplate;

    @Override
	public List<Employee>  searchResource(Employee employee) {
		return empClient.searchEmployee(employee);
	}

    @Override
	public List<Employee> searchResourceById(Employee employee) {
    	return empClient.searchEmployeeById(employee);
	}
    
    @Override
    public List<Employee> listEmployee() {
        return empClient.listEmployee();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return empClient.saveEmployee(employee);
    }
    
    @Override
    public Employee editEmployee(Employee employee) {
        return empClient.editEmployee(employee);
    }

    @Override
	public List<String> getname() {
		return empClient.getname();
	}
    
    @Override
	public  Employee changepass(String employeeId, String password, String newpassword ) {
		return empClient.changepass(employeeId,password,newpassword);
	}
    
    @Override
	public  Employee resetpass(String employeeId,String newpassword ) {
		return empClient.resetpass(employeeId,newpassword);
	}

	@Override
	public List<String> supervisorlist() {
		return empClient.supervisorlist();
	}

	@Override
	public Employee listmodify(String employeeId) {
		return empClient.listmodify(employeeId);
	}

	@Override
    public Security saveSecurity(Security security) {
        return empClient.saveSecurity(security);
    }
	
	@Override
	public List<Security>  securityCheck(Security security) {
		return empClient.securityCheck(security);
	}

	@Override
	public List<Question> getQuestion() {
		return empClient.getQuestion();
	}


	@Override
	public List<Questioncheck> listquestiondetails(String empId) {
		return empClient.listquestiondetails(empId);
	}

	@Override
	public List<Questioncheck> getSaveans(List<Questioncheck> answer) {
		return empClient.getSaveans(answer);
	}

	
	@Override
	public List<Questioncheck> getSecurityDetails1(List<Questioncheck> answer) {
		return empClient.getSecurityDetails1(answer);
	}

	@Override
	public List<Employee> getEmployee() {
		return empClient.getEmployee();
	}

	/*@Override
	public List<Questioncheck> deleteEmp(List<Questioncheck> answer) {
		return empClient.deleteEmp(answer);
	}*/

	@Override
	public Employee saveEmployeebyuser(Employee employee) {
		return empClient.saveEmployeebyuser(employee);
	}

	@Override
	public List<Questioncheck> deleteEmp(String empId) {
		return empClient.deleteEmp(empId);
	}

	@Override
	public List<Employee> AdminEmployee() {
		return empClient.AdminEmployee();
	}

	
}
