package com.acc.sts.core.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acc.sts.core.repository.EmployeeRepository;
import com.acc.sts.model.DeleteTicket;
import com.acc.sts.model.Edit;
import com.acc.sts.model.Employee;
import com.acc.sts.model.Question;
import com.acc.sts.model.Questioncheck;
import com.acc.sts.model.Security;
import com.acc.sts.model.Ticket;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
	public List<Employee> searchEmployee(Employee employee){
        List<Employee> list =null;
	    TypedQuery<Employee> query=entityManager.createQuery("from Employee where employeeId= :eid and password= :pswd ", Employee.class);
	    query.setParameter("eid", employee.getEmployeeId());
	    query.setParameter("pswd", employee.getPassword());
	    list= query.getResultList();
	    return list;
	}
    
    
    @Override
	public List<Employee> searchEmployeeById(Employee employee){
        List<Employee> list =null;
	    TypedQuery<Employee> query=entityManager.createQuery("from Employee where employeeId= :eid ", Employee.class);
	    query.setParameter("eid", employee.getEmployeeId());
	    list= query.getResultList();
	    return list;
	}
    
    
    @Override
    public List<Employee> listEmployee() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Transactional
	@Override
	public Employee saveEmployee(Employee employee) {
entityManager.persist(employee);
employee.setIsActive("Yes");
employee.setIsInclude("Yes");
employee.setPassword("pass");
employee.setReport("0");
employee.setFlag(0);

//for security reason this two field commented (Kavitha Naganathan)
/*employee.setContactNumber(0);
employee.setEmailId(" ");*/

return employee;
	 } 

    @Transactional
    @Override
   public Employee editEmployee(Employee employee) {
       
     	if(employee.getReport().equalsIgnoreCase("yes")){
     		employee.setReport("1");
     	
     	}
     	else
     	{
     		employee.setReport("0");
     		
     	}
     	//entityManager.merge(employee)
       //TODO
     	Query query1 = entityManager.createQuery("update Employee set isActive = :isactive,isSupervisor = :issupervisor,supervisorName= :supervisorname,isAdmin= :isadmin, isInclude= :isinclude, designation=:desg,report= :report, role= :role WHERE employeeId=:empId");
     	 query1.setParameter("empId", employee.getEmployeeName());   
    	 query1.setParameter("isinclude", employee.getIsInclude());
    	 query1.setParameter("isactive", employee.getIsActive());
    	 query1.setParameter("isadmin", employee.getIsAdmin());
    	 query1.setParameter("issupervisor", employee.getIsSupervisor());
    	 query1.setParameter("report", employee.getReport());
    	 query1.setParameter("role", employee.getRole());
    	 query1.setParameter("desg", employee.getDesignation());
    	 query1.setParameter("supervisorname", employee.getSupervisorName());    	 
    	 query1.executeUpdate();
        return employee;
    }

    
	@Override
	public List<String> getname() {
		 Query query = entityManager.createQuery("Select employeeName from Employee");    
	        return query.getResultList();
	}



    @Transactional
	@Override
	public Employee changepass(String employeeId, String password, String newpassword) {
		TypedQuery<Employee> q1=entityManager.createQuery("from Employee where employeeId=:empid",Employee.class);
		q1.setParameter("empid", employeeId);
		List<Employee> e=q1.getResultList();
		Employee pswd1=e.get(0);
		String pd=pswd1.getPassword();
		if(password.equals(pd))
		{
			Query query=entityManager.createQuery("update Employee set password=:pswd WHERE employeeId=:empid");
			query.setParameter("pswd",newpassword);
			query.setParameter("empid", employeeId);
			query.executeUpdate();
		}
		return pswd1;
	}
    
    
    @Transactional
	@Override
	public Employee resetpass(String employeeId,String newpassword) {
		TypedQuery<Employee> q1=entityManager.createQuery("from Employee where employeeId=:empid",Employee.class);
		q1.setParameter("empid", employeeId);
		List<Employee> e=q1.getResultList();
		Employee pswd1=e.get(0);
			Query query=entityManager.createQuery("update Employee set password=:pswd WHERE employeeId=:empid");
			query.setParameter("pswd",newpassword);
			query.setParameter("empid", employeeId);
			query.executeUpdate();
		return pswd1;
	}
    

	@Override
	public List<String> supervisorlist() {
		Query query = entityManager.createQuery("Select employeeName from Employee where isSupervisor ='yes'");    
        return query.getResultList();
	}
	
	@Override
    public Employee listmodify(String employeeId) {
		Employee employee = null;
        TypedQuery<Employee> query = entityManager.createQuery("update Employee set flag='1' where employeeId=:employeeId", Employee.class);
        query.setParameter("employeeId", employeeId);
        employee=query.getSingleResult();
        return employee;
    }
	
	@Transactional
    @Override
    public Security saveSecurity(Security security) {
	    entityManager.persist(security);
	    Query query=entityManager.createQuery("update Employee set flag='1' where employeeId=:employeeId");
	    query.setParameter("employeeId",security.getEmployee().getEmployeeId());
	    query.executeUpdate();
	    return security;
    }
	

 @Override
	public List<Security> securityCheck(Security security){
        List<Security> list =null;
        System.out.println(security.getEmployee().getEmployeeId());
	    TypedQuery<Security> query=entityManager.createQuery("from Security where employee.employeeId= :employeeId", Security.class);
	    query.setParameter("employeeId",security.getEmployee().getEmployeeId());
	    list= query.getResultList();
	    return list;
	}

@Override
public List<Question> getQuestion() {
	 Query query = entityManager.createQuery("from Question" ,Question.class);    
	 List<Question> question=query.getResultList();
     return question;
}

@Override
@Transactional
public List<Questioncheck> getSecurityDetails1(List<Questioncheck> answer) {
	
	for (Iterator<Questioncheck> it = answer.iterator(); it.hasNext();) {
		Questioncheck quesObj = it.next();

		entityManager.persist(quesObj);
		entityManager.flush();
		entityManager.clear();
    }
	
	 Query query1=entityManager.createQuery("update Employee set flag='1' where employeeId=:employeeId");
	query1.setParameter("employeeId",answer.get(0).getEmployee().getEmployeeId());
	    query1.executeUpdate();
    return answer;
}

@Override
public List<Questioncheck> listquestiondetails(String empId) {
	TypedQuery<Questioncheck> query = entityManager.createQuery("from Questioncheck where employee.employeeId = :empId ", Questioncheck.class);
	query.setParameter("empId",empId);
    List<Questioncheck> ques=query.getResultList();
    return ques;
}


@Override
public List<Questioncheck> getSaveans(List<Questioncheck> answer) {

	Query[] query= new Query[3];
	for(int i=0;i<3;i++)
	{
		 query[i]=entityManager.createQuery("from Questioncheck where answers= :ans and question= :ques");
		    query[i].setParameter("ans",answer.get(i).getAnswers());
		    query[i].setParameter("ques",answer.get(i).getQuestion());
		    query[i].getResultList();
	}	
    return answer;
}


@Override
public List<Employee> getEmployee() {
	Query query = entityManager.createQuery("from Employee",Employee.class);
	 return query.getResultList();
}


/*@Override
public List<Questioncheck> deleteEmp(List<Questioncheck> answer) {
	TypedQuery<Questioncheck> query = entityManager.createQuery("delete from Questioncheck where employeeId=:employeeId" ,Questioncheck.class);
			query.setParameter("employeeId",answer.get(0).getEmployee().getEmployeeId());
		    query.executeUpdate();
			
	 Query query1=entityManager.createQuery("update Employee set flag='0' where employeeId=:employeeId");
		query1.setParameter("employeeId",answer.get(0).getEmployee().getEmployeeId());
		    query1.executeUpdate();
	return answer;
}
*/
@Transactional
@Override
public Employee saveEmployeebyuser(Employee employee) {
	entityManager.persist(employee);
	employee.setIsActive("Yes");
	employee.setIsInclude("Yes");
	employee.setPassword("pass");
	employee.setReport("0");
	employee.setFlag(0);
	employee.setIsAdmin("No");
	employee.setIsSupervisor("No");
	
	//for security reason this two field commented (Kavitha Naganathan)
	/*employee.setContactNumber(0);
	employee.setEmailId(" ");*/
	
	return employee;
}


@Override
@Transactional
public List<Questioncheck> deleteEmp(String empId) {
	Query query = entityManager.createQuery("delete from Questioncheck where employee.employeeId=:employeeId");
	query.setParameter("employeeId",empId);
    query.executeUpdate();
	
Query query1=entityManager.createQuery("update Employee set flag='0' , Password='pass' where employeeId=:employeeId");
query1.setParameter("employeeId",empId);
    query1.executeUpdate();
    Query query2=entityManager.createQuery("from Questioncheck",Questioncheck.class);
return query2.getResultList();
}


@Override
public List<Employee> AdminEmployee() {
	TypedQuery<Employee> query = entityManager.createQuery("from Employee where isAdmin='Yes' ", Employee.class);
    return query.getResultList();
}

}