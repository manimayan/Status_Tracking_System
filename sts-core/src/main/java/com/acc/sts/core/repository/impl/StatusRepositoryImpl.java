package com.acc.sts.core.repository.impl;



import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.acc.sts.core.repository.StatusRepository;
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

@Repository
public class StatusRepositoryImpl implements StatusRepository{
	 @PersistenceContext
	    protected EntityManager entityManager;

	 
	  @Override
	    public List<Clarify> listclarify() {
	    
		  TypedQuery<Clarify> query = entityManager.createQuery("select new com.acc.sts.model.Clarify(c.ticket.ticketId,c.flag,t.ticketType,e.employeeName,t.ticketDescription,a.applicationName,d.status,c.clarificationDescription,c.employeeResponse) from Ticket t inner join Employee e on t.employee.employeeId=e.employeeId inner join Application a on t.application.applicationId=a.applicationId inner join Dev_comment d on t.ticketId=d.ticket.ticketId inner join Clarification c on t.ticketId=c.ticket.ticketId where d.date in(select max(date) from Dev_comment d group by d.ticket.ticketId)",Clarify.class);

	        return query.getResultList();
	    }
	  
	  
	  @Override
		public List<Timereport> timelistnotreportdetails() 
	    {
			System.out.println("core rep of not reportedtime");
			LocalDate date = LocalDate.now();
		        TypedQuery<Timereport> query = entityManager.createQuery("select new com.acc.sts.model.Timereport(c.employeeId,c.employeeName,a.inTime,a.outTime)from Employee c inner join Timing a on a.employee.employeeId=c.employeeId  "
		        		+ "WHERE  c.employeeId NOT IN"
		        		+ "(select c.employeeId from Employee c inner join Timing a on a.employee.employeeId=c.employeeId "
		        		+ "where a.date=:curdate and a.difference is NOT NULL group by c.employeeId) and a.date=:curdate   ", Timereport.class); 
		        query.setParameter("curdate", date);
	        System.out.println("core repository of not reportedtime");
	      
	        System.out.println(query.getFirstResult());
	        return query.getResultList();
		}
	    
	    @Override
		public List<Timereport> timelistnotreportabsencedetails() {
			System.out.println("core rep of not reported");
			LocalDate date = LocalDate.now();  
	    	  System.out.println(date);
		        /*TypedQuery<Timereport> query =entityManager.createQuery("select new com.acc.sts.model.Timereport(c.employeeName)from Employee c  "
		        		+ "WHERE  c.employeeId NOT IN"
		        		+ "(select c.employeeId from Employee c inner join Timing a on a.employee.employeeId=c.employeeId "
		        		+ "where a.date=:curdate and a.difference is NOT NULL group by c.employeeId)  ", Timereport.class);  */
		        TypedQuery<Timereport> query = entityManager.createQuery("select new com.acc.sts.model.Timereport(c.employeeName)from Employee c  WHERE c.employeeId NOT IN(select c.employeeId from Employee c inner join Timing a on a.employee.employeeId=c.employeeId  where a.date=:curdate and a.difference is  NULL group by c.employeeId)  group by c.employeeId", Timereport.class);  
	         query.setParameter("curdate", date);
	        System.out.println("core repository of not reported");
	        System.out.println(query.getFirstResult());
	        return query.getResultList();
		}	




	    
		@Override
		public List<Clarify> searchTicket(Ticket ticket) {
			
			TypedQuery<Ticket> query= entityManager.createQuery("from Ticket where ticketId= :ticketId ",Ticket.class);
			query.setParameter("ticketId",ticket.getTicketId());
			List<Ticket> tick=query.getResultList();
			 List<Clarify> cla=null;
			if(tick.size()==0)
			{
				return cla;
			}
			else
			{
				TypedQuery<Clarify> query1 = entityManager.createQuery("select new com.acc.sts.model.Clarify(t.ticketId,t.ticketType,e.employeeName,t.ticketDescription,a.applicationName,d.status)from Ticket t left outer join Employee e on t.employee.employeeId=e.employeeId left outer join Application a on a.applicationId=t.application.applicationId left outer join Dev_comment d on d.ticket.ticketId=t.ticketId  where t.ticketId=:ticketId and d.date in(select max(date) from Dev_comment d where d.ticket.ticketId=:ticketId )", Clarify.class);
				 query1.setParameter("ticketId",ticket.getTicketId());
			        return query1.getResultList();			
			}
			
		}
		
		@Override
		@Transactional
		public Clarification updateComment(Clarification clarify) {
		
			 entityManager.persist(clarify);
			return clarify;
		}

		
		@Override
		@Transactional
		public List<Clarify> deleteclarify(String ticketId,String comment) {
			Query query=entityManager.createQuery("delete from Clarification where ticket.ticketId=:ticketId and clarificationDescription=:comment");			
			query.setParameter("ticketId", ticketId);
			query.setParameter("comment", comment);
			int a=query.executeUpdate();
	        TypedQuery<Clarify> query1 = entityManager.createQuery("select new com.acc.sts.model.Clarify(t.ticketId,c.flag,t.ticketType,e.employeeName,t.ticketDescription,a.applicationName,d.status,c.clarificationDescription,c.employeeResponse)from  Ticket t left outer join Employee e on t.employee.employeeId=e.employeeId left outer join Application a on a.applicationId=t.application.applicationId left outer join Dev_comment d on d.ticket.ticketId=t.ticketId  left outer join Clarification c on c.ticket.ticketId=t.ticketId where c.flag=0 ", Clarify.class);
			return query1.getResultList() ;
		}
		@Override 
		  public List<String> getname() {
				 Query query = entityManager.createQuery("Select employeeName from Employee");
				 return query.getResultList();
			}

		    @Transactional
		   	@Override
		   	public List<Tickethistory>submit( String ticketId,String employeeName, int days) {
		    	
		    	  Calendar cal = Calendar.getInstance();
		          cal.setTime(new Date());
		       LocalDate date=LocalDate.now();
		          cal.add(Calendar.DATE, -days);
		         	LocalDate startDate=date.minusDays(days);
		         TypedQuery<Ticket> query= entityManager.createQuery("from Ticket where ticketId= :ticketId ",Ticket.class);
					query.setParameter("ticketId",ticketId);
					List<Ticket> tick=query.getResultList();
					List<Tickethistory> cla=null;
				Query query1 = entityManager.createQuery("select new com.acc.sts.model.Tickethistory (e.employeeName,"
		      + "t.ticketId,t.ticketType,d.date,d.status,d.devComment) from Ticket t inner join Employee e on t.employee.employeeId = e.employeeId "
		      + "inner join Dev_comment d on t.ticketId = d.ticket.ticketId where( e.employeeName=:employeename or t.ticketId=:ticketId) and d.date >= :startDate and d.date <= :endDate"); 
		    query1.setParameter("employeename", employeeName);  
			query1.setParameter("ticketId", ticketId); 
			query1.setParameter("endDate", date); 
			query1.setParameter("startDate", startDate); 
			return query1.getResultList();  
					
		    	
				    }
		    
		    @Override
		    public List<NotReportEmployee> listNotReportEmployee() {
		        	LocalDate tdyDate = LocalDate.now();   
		        																												//select e.employee_Name,e.employee_ID,e.email,e.designation from employee_details e inner join ticket_details t on e.employee_ID=t.employee_ID  where e.Employee_ID not in (select  employee0_.Employee_ID as col_1_0_  from employee_details employee0_ inner join Ticket_details ticket1_ on (ticket1_.Employee_ID=employee0_.Employee_ID) where ticket1_.Updated_On='2018-05-03' and employee0_.is_active='yes' group by ticket1_.Employee_ID ) and e.is_active='yes' group by e.employee_ID
		           TypedQuery<NotReportEmployee> query = entityManager.createQuery("select new com.acc.sts.model.NotReportEmployee(c.employeeName,c.employeeId,c.designation) from Employee c inner join Ticket a on a.employee.employeeId=c.employeeId  WHERE c.employeeId NOT IN(select c.employeeId from Employee c inner join Ticket a on a.employee.employeeId=c.employeeId inner join  Dev_comment d on d.ticket.ticketId=a.ticketId inner join Employee c on a.employee.employeeId=c.employeeId where d.date=:curdate and c.isActive='yes' group by c.employeeId) and c.isActive='yes' group by c.employeeId", NotReportEmployee.class); 
		           query.setParameter("curdate",tdyDate); 
		           List<NotReportEmployee> li=query.getResultList();
		           return li; 
		   }   

		   @Override
		    public List<Dailystatus> listreportdetails()  {    
		    	LocalDate tdyDate = LocalDate.now();    		
		       
		    	System.out.println(tdyDate);
		        TypedQuery<Dailystatus> query = entityManager.createQuery
		        		("select new com.acc.sts.model.Dailystatus(c.employeeName,a.ticketId,"
		        				+ "a.ticketType,a.ticketDescription,b.applicationName,"
		        				+ "a.priority,d.activity,d.status,d.devComment,a.updatedOn,a.tester,t.testComment,a.workedOnToday)"
		        				+ " from Ticket a inner join Application b on a.application.applicationId=b.applicationId inner join Employee c on "
		        				+ "a.employee.employeeId=c.employeeId inner join Dev_comment d on d.ticket.ticketId=a.ticketId left outer join Tester_comment t on t.ticket.ticketId=a.ticketId"
		        				+ " where d.date=:curdate  and c.isActive='yes'", Dailystatus.class);
		       
		        query.setParameter("curdate", tdyDate);
		        return query.getResultList();

		        
		       
		      
		    }

		    @Override
			public List<Dailystatus> listnotreportdetails() {
				System.out.println("core rep of not reported");
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				LocalDate date = LocalDate.now();  
		    	  System.out.println(date);
			        TypedQuery<Dailystatus> query = entityManager.createQuery("select new com.acc.sts.model.Dailystatus(c.employeeName)from Employee c inner join Ticket a on a.employee.employeeId=c.employeeId  WHERE c.employeeId NOT IN(select c.employeeId from Employee c inner join Ticket a on a.employee.employeeId=c.employeeId inner join  Dev_comment d on d.ticket.ticketId=a.ticketId inner join Employee c on a.employee.employeeId=c.employeeId where d.date=:curdate and c.isActive='yes' group by c.employeeId) and c.isActive='yes' group by c.employeeId", Dailystatus.class); 
		         query.setParameter("curdate", date);
		        System.out.println("core repository of not reported");
		        System.out.println(query.getFirstResult());
		        return query.getResultList();
			}
		    	 

		    	@Override
		        public List<Ticketstatus> ticketstatus(String ticketId,String employeeName) {
		        	TypedQuery<Ticketstatus> query = entityManager.createQuery("select new com.acc.sts.model.Ticketstatus(a.ticketId,a.ticketDescription,a.ticketType,b.employeeName,a.updatedOn,c.status,c.devComment) from Ticket a left outer join Employee b on a.employee.employeeId=b.employeeId left outer join Dev_comment c on a.ticketId=c.ticket.ticketId WHERE a.ticketId=:ticketId or b.employeeId =:employeeName ",Ticketstatus.class);
		        	System.out.println("in update core rep after query ticketstatus");
		        	System.out.println(ticketId);
		        	 query.setParameter("ticketId",ticketId);
		        	 query.setParameter("employeeName",employeeName);
		        	 List<Ticketstatus> li=query.getResultList();
		           System.out.println(li);
		           //query.executeUpdate();
		            return li;
		        }		
			 @Override 
			  public List<Ticket> gettickname() {
					 Query query = entityManager.createQuery("from Ticket",Ticket.class);
					 return query.getResultList();
				}

		 @Override 
		    public List<Nochange> listNochange() {
			 
		        
		    	//TypedQuery<Nochange> query = entityManager.createQuery("select new com.acc.sts.model.Nochange(a.ticketId,b.emp_name,a.ticketType,a.ticketDescription,c.applicationName,a.priority,d.activity,d.status,a.updatedOn,d.commentDescription) from Ticket a left outer join Employee b on a.ticketId=b.ticket.ticketId left outer join Application c on a.application.applicationId=c.applicationId join com.acc.sts.model.Dev_comment d on a.ticketId=d.ticket.ticketId " );
		        //return query.getResultList();
			 //TypedQuery<Nochange> query =  entityManager.createQuery("select new com.acc.sts.mpdel.Nochange(a.ticketId,b.emp_name,a.ticketType,a.ticketDescription,c.applicationName,a.priority,d.activity,d.status,a.updatedOn,d.commentDescription)from Ticket_Details a from Ticket a left outer join Employee_details b on a.ticketId=b.ticket_details.ticketId left outer join Application c on a.application.applicationName=c.applicationName join com.acc.sts.model.Dev_comment d on a.ticketId=d.ticket.ticketId and d.status="In Progress" );
			 TypedQuery<Nochange> query = entityManager.createQuery
					 ("select new com.acc.sts.model.Nochange(t.ticketId,e.employeeName,t.ticketType,t.ticketDescription,t.application.applicationId,t.priority,d.activity,d.status,t.updatedOn,d.devComment)from Ticket t inner join Employee e on e.employeeId=t.employee.employeeId inner join Dev_comment d on t.ticketId=d.ticket.ticketId where d.status='In Progress' and DATEDIFF(t.updatedOn,t.startDate) >= 5 ",Nochange.class);
			 return query.getResultList();
		    }
		 @Override
		    public List<Dayticket> listDayticket() {
		    	SimpleDateFormat fd=new SimpleDateFormat("yyyy-MM-dd");
		    	LocalDate date=LocalDate.now();
		        TypedQuery<Dayticket> query = entityManager.createQuery("select new com.acc.sts.model.Dayticket(c.employeeName,a.ticketId,a.ticketType,a.ticketDescription,b.applicationName,a.priority,d.activity,d.status,d.devComment,a.startDate) from Ticket a inner join Application b on a.application.applicationId=b.applicationId inner join Employee c on a.employee.employeeId=c.employeeId inner join Dev_comment d on d.ticket.ticketId=a.ticketId where c.isActive='yes' and a.workedOnToday='yes'and d.date=:curdate", Dayticket.class);
		        query.setParameter("curdate",date);
		        return query.getResultList();
		    }
		 
		 @Override
			public List<Employee> getempname() {
		    	TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
			        return (query).getResultList();
			}
		    
		  
		    public List<Application> getappname() {
		    	TypedQuery<Application> query = entityManager.createQuery("from Application", Application.class);
		        return (query).getResultList();
			}
		    
		    @Override 
		    public List<Employee> getTestername()
		    {
		    	TypedQuery<Employee> query = entityManager.createQuery("from Employee where role like 'Tester'", Employee.class);
				 return query.getResultList();
			}
		   
		    @Override
		    public List<Reportsummary> listreportTicket(Reportsummary report) {
		    LocalDate date= LocalDate.now();	 
		    String str="select new com.acc.sts.model.Reportsummary(a.employeeName,b.ticketId,b.ticketType,b.ticketDescription,c.applicationName,b.priority,d.activity,d.status,b.tester,d.devComment,b.startDate,b.updatedOn,b.endDate,e.remedy,e.documentationDescription) from Ticket b left outer join Employee a on b.employee.employeeId=a.employeeId left outer join Application c on b.application.applicationId=c.applicationId left outer join Dev_comment d on d.ticket.ticketId=b.ticketId left outer join Documentation e on e.ticket.ticketId=b.ticketId where a.isActive='YES'";
				TypedQuery<Reportsummary> query = entityManager.createQuery(str, Reportsummary.class); 
				if ((report.getEmployeeName()) !=null && !"".equals(report.getEmployeeName())){
					str += " and a.employeeId=:employeeId";	
					query = entityManager.createQuery(str, Reportsummary.class);
				}
				if ((report.getApplicationName()) !=null && !"".equals(report.getApplicationName())){
					str += " and c.applicationId=:applicationId";	
					query = entityManager.createQuery(str, Reportsummary.class);
				}	
				if ((report.getActivity()) !=null && !"".equals((report.getActivity()))){
					str += " and d.activity=:activity";	
					query = entityManager.createQuery(str, Reportsummary.class);
				}
				if ((report.getStatus()) !=null && !"".equals((report.getStatus()))){
					str += " and d.status=:status";	
					query = entityManager.createQuery(str, Reportsummary.class);
				}
				
				if ((report.getReleaseTicket()) !=null && !"".equals((report.getReleaseTicket()))){
				//	str += " and (d.status <> 'Closed' or b.startDate is null) and b.releaseTicket=:releaseTicket";	
					str += " and b.releaseTicket=:releaseTicket";
					query = entityManager.createQuery(str, Reportsummary.class);
				}
				if ((report.getTicketType()) !=null && !"".equals((report.getTicketType()))){
					str += " and b.ticketType=:ticketType";	
					query = entityManager.createQuery(str, Reportsummary.class);
				}
				if ((report.getTester()) !=null && !"".equals(report.getTester())){
					str += " and b.tester=:tester";	
					query = entityManager.createQuery(str, Reportsummary.class);
				}
				if (((report.getStartdateFrom()) !=null && (report.getStartdateTo()) !=null )&& ((!"".equals(report.getStartdateFrom()))&&(!"".equals(report.getStartdateTo())))){
					str += " and b.startDate between :startdateFrom and :startdateTo";	
					query = entityManager.createQuery(str, Reportsummary.class);
				}
				if (((report.getEnddateFrom()) !=null && (report.getEnddateTo()) !=null )&& ((!"".equals(report.getEnddateFrom()))&&(!"".equals(report.getEnddateTo())))){
					str += " and b.endDate between :enddateFrom and :enddateTo";	
					query = entityManager.createQuery(str, Reportsummary.class);
				}
				str += " group by b.ticketId  order by  a.employeeName, b.priority";
				query = entityManager.createQuery(str, Reportsummary.class);
				if ((report.getEmployeeName()) !=null && !"".equals(report.getEmployeeName())){
					query.setParameter("employeeId",report.getEmployeeName());
				}
				if ((report.getApplicationName()) !=null && !"".equals(report.getApplicationName())){
					query.setParameter("applicationId",report.getApplicationName());
				}	
				if ((report.getActivity()) !=null && !"".equals((report.getActivity()))){
					query.setParameter("activity",report.getActivity());
				}
				if ((report.getStatus()) !=null && !"".equals((report.getStatus()))){
					query.setParameter("status",report.getStatus());
				}
				if ((report.getReleaseTicket()) !=null && !"".equals((report.getReleaseTicket()))){
					query.setParameter("releaseTicket",report.getReleaseTicket());
			    }
		        if ((report.getTicketType()) !=null && !"".equals((report.getTicketType()))){
					query.setParameter("ticketType",report.getTicketType());
				}
				if ((report.getTester()) !=null && !"".equals(report.getTester())){
					query.setParameter("tester",report.getTester());
				}
				if (((report.getStartdateFrom()) !=null && (report.getStartdateTo()) !=null )&& ((!"".equals(report.getStartdateFrom()))&&(!"".equals(report.getStartdateTo())))){
					query.setParameter("startdateFrom",report.getStartdateFrom());
					query.setParameter("startdateTo",report.getStartdateTo());
				}
				if (((report.getEnddateFrom()) !=null && (report.getEnddateTo()) !=null )&& ((!"".equals(report.getEnddateFrom()))&&(!"".equals(report.getEnddateTo())))){
					query.setParameter("enddateFrom",report.getEnddateFrom());
					query.setParameter("enddateTo",report.getEnddateTo());
				}
				return query.getResultList();
		    }

			@Override
			public List<Clarify> listPendingResponse(String empId) {
				System.out.println("hello hi");
				  TypedQuery<Clarify> query = entityManager.createQuery("select new com.acc.sts.model.Clarify(t.ticketId,c.flag,t.ticketType,e.employeeName,t.ticketDescription,a.applicationName,d.status,c.clarificationDescription,c.employeeResponse) from Ticket t inner join Employee e on t.employee.employeeId=e.employeeId inner join Application a on t.application.applicationId=a.applicationId inner join Dev_comment d on t.ticketId=d.ticket.ticketId inner join Clarification c on t.ticketId=c.ticket.ticketId where d.date in(select max(date) from Dev_comment d group by d.ticket.ticketId) and c.flag=0 and t.employee.employeeId=:empId",Clarify.class);
				  query.setParameter("empId", empId);
			        return query.getResultList();
			}     
		 
}
