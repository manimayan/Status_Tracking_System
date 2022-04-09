package com.acc.sts.core.repository.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.acc.sts.core.repository.TimingRepository;
import com.acc.sts.model.Employee;
import com.acc.sts.model.Timereport;
import com.acc.sts.model.Timing;

@Repository
public class TimingRepositoryImpl implements TimingRepository {

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    @Override
     public Timing saveIntime(Timing intime) { 
    	Query query=entityManager.createQuery("from Timing "+"where currentDate = :date "+ "and Employee_ID= :employeeId  ",Timing.class);
 	   query.setParameter("date",intime.getDate());
 	   query.setParameter("employeeId",intime.getEmployee());
 	   query.getResultList();
 	   System.out.println(query.getResultList().size());
 	   if(query.getResultList().size()==0)
 	   {
       entityManager.persist(intime);
 	   }
 	  else
 		{
     	
     	Query query2 = entityManager.createQuery("update Timing set inTime = :inTime" +
     			 " where currentDate = :date " + " and Employee_ID= :employeeId ");
      query2.setParameter("inTime", intime.getInTime());
      query2.setParameter("date", intime.getDate());
	     query2.setParameter("employeeId",intime.getEmployee());
     	query2.executeUpdate();
 		
  } 
       return intime;
    }

    @Transactional
    @Override
    public Timing saveTiming(Timing outtime) {
 /*   	SimpleDateFormat fd=new SimpleDateFormat("yyyy-MM-dd");*/
    	System.out.println("diff"+outtime.getDifference());  	 
    	System.out.println("date"+outtime.getDate());
    	
    	Query query=entityManager.createQuery("from Timing "+"where currentDate = :date "+ "and Employee_ID= :employeeId  ",Timing.class);
    	 //query.setParameter("date", fd.format(outtime.getDate()));
    	 query.setParameter("date", outtime.getDate());
  	     query.setParameter("employeeId",outtime.getEmployee());
  	      query.getResultList();
  	   System.out.println(query.getResultList().size());
  	 if(query.getResultList().size()==0)
		{
  		 entityManager.persist(outtime);
		}
		else
		{
    	
    	Query query2 = entityManager.createQuery("update Timing set outTime = :outTime, difference = timediff(:outTime,inTime)" +
    			 " where currentDate = :date " + " and Employee_ID= :employeeId ");
    	   query2.setParameter("outTime", outtime.getOutTime());
    	  // query2.setParameter("date", fd.format(outtime.getDate()));
    	   query2.setParameter("date", outtime.getDate());
    	   query2.setParameter("employeeId",outtime.getEmployee());
    	    query2.executeUpdate();
		}
         return outtime;  
    }
    @Override
	public List<Employee> getname() {
    	System.out.println("in repository");
    	TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class); 
	        return query.getResultList();
	}
     

    
    
   /* @Override
    @Transactional
   
    public List<Timereport> submitEmpdetails(String employeeId, String fdate, String todate)
   {
    	System.out.println("in repository");
    	if(employeeId.equals("1"))
    	{
    		TypedQuery<Timereport> query = entityManager.createQuery
    				 ("select new com.acc.sts.model.Timereport(e.employeeId,e.employeeName,e.emailId,a.applicationName,t.inTime,t.outTime,t.difference,t.date) from Employee e inner join Application a on e.application.applicationId=a.applicationId inner join Timing t on e.employeeId=t.employee.employeeId where  currentDate>=:todate and currentDate<=:fdate ", Timereport.class);
    				    	//query.setParameter("employeeId", employeeId);
    				        query.setParameter("todate", fdate); 
    				        query.setParameter("fdate", todate); 
    				    	return query.getResultList();

    	}
    	else
    	{
    	TypedQuery<Timereport> query = entityManager.createQuery
 ("select new com.acc.sts.model.Timereport(e.employeeId,e.employeeName,e.emailId,a.applicationName,t.inTime,t.outTime,t.difference,t.date) from Employee e inner join Application a on e.application.applicationId=a.applicationId inner join Timing t on e.employeeId=t.employee.employeeId where e.employeeId=:employeeId and currentDate>=:todate and currentDate<=:fdate ", Timereport.class);
    	query.setParameter("employeeId", employeeId);
        query.setParameter("todate", fdate); 
        query.setParameter("fdate", todate); 
        System.out.println("checking");
     
    	return query.getResultList();
    	}
   
    }
*/

    @Override
    @Transactional
    
    public List<Timereport> submitEmpdetails(Timereport report)
    {
    	//select e.EMPLOYEE_ID,e.EMPLOYEE_NAME,e.EMAIL,a.Application_Name,t.inTime,t.outTime,t.Difference,t.currentDate from employee_details e inner join ticket_details b on e.EMPLOYEE_ID=b.Employee_ID inner join Application a on b.Application_ID=a.Application_ID inner join Timing t on e.EMPLOYEE_ID=t.Employee_ID; 

    	
     	String str="select new com.acc.sts.model.Timereport(e.employeeId,e.employeeName,e.supervisorName,t.inTime,t.outTime,t.difference,t.date) from Employee e  inner join Timing t on e.employeeId=t.employee.employeeId";
     	TypedQuery<Timereport> query = entityManager.createQuery(str, Timereport.class); 
     	if ((report.getEmployeeName()) !=null && !"".equals(report.getEmployeeName())){
     		System.out.println("employeeId not null");
     		System.out.println(report.getEmployeeId());
     		str +=" and e.employeeId=:employeeId";
     		query = entityManager.createQuery(str, Timereport.class); 
     	}
     	if ((report.getFdate()) !=null && !"".equals(report.getFdate())){
     		System.out.println("fdate not null");
     		System.out.println(report.getFdate());
     		str +=" and t.date between :fdate and :todate";
     		query = entityManager.createQuery(str, Timereport.class); 
     	}
     	
     	if ((report.getEmployeeName()) !=null && !"".equals(report.getEmployeeName())){
     		query.setParameter("employeeId", report.getEmployeeName());
     	}
     	if ((report.getFdate()) !=null && !"".equals(report.getFdate())){
     		query.setParameter("fdate", report.getFdate());
     		query.setParameter("todate", report.getTodate());
     	}
     	return query.getResultList();
    
    
    }
    
    

}
