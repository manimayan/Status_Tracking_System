package com.acc.sts.core.repository;

import java.util.List;

import com.acc.sts.model.Employee;
import com.acc.sts.model.Question;
import com.acc.sts.model.Questioncheck;
import com.acc.sts.model.Security;
import com.acc.sts.model.Ticket;

public interface EmployeeRepository {
	
	List<Employee> searchEmployee(Employee employee);
	
	List<Employee> searchEmployeeById(Employee employee);

    List<Employee> listEmployee();

    Employee saveEmployee(Employee employee);
    
    Employee saveEmployeebyuser(Employee employee);
    
    Employee editEmployee(Employee employee);

	List<String> getname();
	
    Employee changepass(String employeeId, String password, String newpassword);
    
    Employee resetpass(String employeeId, String newpassword);

	List<String> supervisorlist();

	Employee listmodify(String employeeId);

	Security saveSecurity(Security security);

	List<Security> securityCheck(Security security);

	List<Question> getQuestion();

	List<Questioncheck> listquestiondetails(String empId);

	List<Questioncheck> getSaveans(List<Questioncheck> answer);
	
	List<Questioncheck> getSecurityDetails1(List<Questioncheck> answer);
	
	List<Employee> getEmployee();
	
	// List<Questioncheck> deleteEmp(List<Questioncheck> answer);
	
	List<Questioncheck> deleteEmp(String empId);
	
	  List<Employee> AdminEmployee();
	
}
