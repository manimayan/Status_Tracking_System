package com.acc.sts.core.repository.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.acc.sts.core.repository.TicketRepository;
import com.acc.sts.model.Application;
import com.acc.sts.model.Clarify;
import com.acc.sts.model.Closed;
import com.acc.sts.model.Dayticket;
import com.acc.sts.model.DeleteTicket;
import com.acc.sts.model.Dev_comment;
import com.acc.sts.model.Documentation;
import com.acc.sts.model.Edit;
import com.acc.sts.model.Employee;
import com.acc.sts.model.Homeadmin;
import com.acc.sts.model.Homedoc;
import com.acc.sts.model.ReassignTicket;
import com.acc.sts.model.Releasetic;
import com.acc.sts.model.Tester_comment;
import com.acc.sts.model.Ticket;
import com.acc.sts.model.Updatestatus;
import com.acc.sts.model.UploadTicket;
import com.acc.sts.model.Workprogress;


@Repository
public class TicketRepositoryImpl implements TicketRepository {

	 @PersistenceContext
	 protected EntityManager entityManager;
		 
	 @Override
	 public List<Employee> listEmpName() {
	    TypedQuery<Employee> query= entityManager.createQuery("from Employee",Employee.class);
		return query.getResultList();
	 }	

	 @Override
	 public List<UploadTicket> listUploadTicket() {
        TypedQuery<UploadTicket> query = entityManager.createQuery ("from  UploadTicket where flag= 0", UploadTicket.class);
		return query.getResultList();
	 }

	 @Transactional
	 @Override
	 public int AssignUpdate(String employeeId, String ticketId,String applicationName,UploadTicket upTicket) {
		 
	 Ticket tick = new Ticket();
	 UploadTicket upTickets = new UploadTicket();
			   
	 TypedQuery<Application> query= entityManager.createQuery("from Application where applicationName = :applicationName", Application.class);
	 query.setParameter("applicationName", applicationName);
	 List<Application> appid = query.getResultList();

	 TypedQuery<Employee> quer= entityManager.createQuery("from Employee where employeeId = :employeeId", Employee.class);
	 quer.setParameter("employeeId", employeeId);
	 List<Employee> empid = quer.getResultList();
				
	 int status = 0;
	 if (status == 0) {
					  tick.setTicketId(upTicket.getTicketId());
					  tick.setTicketDescription(upTicket.getTicketDescription());
					  tick.setApplication(appid.get(0));
				      tick.setEmployee(empid.get(0));
					  tick.setFlag(1);
					  tick.setReleaseTicket("No");
					  entityManager.merge(tick);
					
					  upTickets.setTicketId(upTicket.getTicketId());
					  upTickets.setApplicationName(upTicket.getApplicationName());
					  upTickets.setFlag(1);
					  upTickets.setTicketDescription(upTicket.getTicketDescription());
					  upTickets.setCreatedBy(upTicket.getCreatedBy());
					  upTickets.setCreatedOn(upTicket.getCreatedOn());
					  entityManager.merge(upTickets);
					
					  status = 1;
				      } else 
				      {
					         status = 0;
				      }
				      
	 				  return status;	    	
			 }

			@Override
			public UploadTicket getTicketDetails(String ticketId) {
				UploadTicket uplist = new UploadTicket();
			    TypedQuery<UploadTicket> query = entityManager.createQuery ("from  UploadTicket where ticketId =:ticketId", UploadTicket.class);
			    query.setParameter("ticketId", ticketId);
		        List<UploadTicket> list = query.getResultList();
		        
		        if (!list.isEmpty()) {
					uplist.setTicketId(list.get(0).getTicketId());
					uplist.setTicketDescription(list.get(0).getTicketDescription());
					uplist.setApplicationName(list.get(0).getApplicationName());
					uplist.setCreatedBy(list.get(0).getCreatedBy());
					uplist.setCreatedOn(list.get(0).getCreatedOn());
					uplist.setFlag(list.get(0).getFlag());
				}
				return uplist;	
			} 
			
			@Override
		    public List<Edit> listticketdetails(String empId) {
		    	
		        //TypedQuery<Edit> query = entityManager.createQuery("select new com.acc.sts.model.Edit(a.ticketId,a.ticketType,a.ticketDescription,b.applicationName,a.priority) from Ticket a left outer join Application b on a.application.applicationId=b.applicationId where a.employee.employeeId=:empId ", Edit.class);
				TypedQuery<Edit> query = entityManager.createQuery("select new com.acc.sts.model.Edit(a.ticketId,a.ticketType,a.ticketDescription,b.applicationName,a.priority) from Ticket a left outer join Application b on a.application.applicationId=b.applicationId inner join Dev_comment d on a.ticketId=d.ticket.ticketId where a.employee.employeeId=:empId and d.date=a.updatedOn and d.status<>'Closed'", Edit.class);
				query.setParameter("empId",empId);
		        List<Edit> li=query.getResultList();
		       
		        System.out.println(li);
		        return li;
		      
		    }
	    @Transactional
			@Override
			public Ticket update(String empId,String ticketId, int newPriority) {
		    	
				Ticket t = new Ticket();
				
				
				Query query1 = entityManager.createQuery("update Ticket set priority=:newPriority WHERE ticketId=:ticket_id and employee.employeeId=:empId");
				System.out.println("in update core rep after query");
		    	 query1.setParameter("ticket_id",ticketId);
		    	 query1.setParameter("newPriority", newPriority);
		    	 query1.setParameter("empId", empId);
		    	 query1.executeUpdate();
				return t;
			}
	    @Override
	    public List<Releasetic> listRelease() {
	    	
	    	TypedQuery<Releasetic> query = entityManager.createQuery("select new com.acc.sts.model.Releasetic(a.ticketId,a.ticketDescription,c.applicationName,b.employeeName,a.releaseTicket) from Ticket a left outer join Employee b on a.employee.employeeId=b.employeeId left outer join Application c on a.application.applicationId=c.applicationId ",Releasetic.class);
	        List<Releasetic> li=query.getResultList();
	       System.out.println(li);
	        return li;
	    }


	 @Override
	    @Transactional
	    public Ticket update1(String ticketId,String releaseTicket) {
	    	
	    	 Ticket t= new Ticket();
	    
	    	Query query1 = entityManager.createQuery("update Ticket set releaseTicket=:releaseTicket1 WHERE ticketId=:ticketId1");
	    	
	    	 query1.setParameter("ticketId1",ticketId);
	    	
	    	 if(releaseTicket.equalsIgnoreCase("no"))
	    	 {
	    		t.setReleaseTicket("yes");
	    	 }
	    	 else
	    	 {
	    		 t.setReleaseTicket("no");
	    	 }
	    	 query1.setParameter("releaseTicket1", t.getReleaseTicket());
	    	 System.out.println(t.getReleaseTicket());
	    	 query1.executeUpdate();
	    	   return t;
	    } 

	 @Override
	    public List<DeleteTicket> listDeleteTicket() {
	    	
	        TypedQuery<DeleteTicket> query = entityManager.createQuery("select new com.acc.sts.model.DeleteTicket(a.ticketId,a.ticketDescription,b.applicationName) from Ticket a left outer join Application b on a.application.applicationId=b.applicationId ",DeleteTicket.class);
			   
			      
	        return query.getResultList();
	    }
	    @Transactional
		@Override
		public Ticket deleteTick(String ticketid) {
			Ticket ticket=new Ticket();
			Query query=entityManager.createQuery("delete from Clarification WHERE ticket.ticketId=:ticketid1");
	    	 query.setParameter("ticketid1", ticketid);
	    	 query.executeUpdate();
			Query query11=entityManager.createQuery("delete from Dev_comment WHERE ticket.ticketId=:ticketid1");
	    	 query11.setParameter("ticketid1", ticketid);
	    	 query11.executeUpdate();
			Query query2=entityManager.createQuery("delete from Documentation WHERE ticket.ticketId=:ticketid1");
	    	 query2.setParameter("ticketid1", ticketid);
	    	 query2.executeUpdate();
			Query query3=entityManager.createQuery("delete from Tester_comment WHERE ticket.ticketId=:ticketid1");
	    	 query3.setParameter("ticketid1", ticketid);
	    	 query3.executeUpdate();
			Query query1 = entityManager.createQuery("delete from Ticket WHERE ticketId=:ticketid1");
	    	 query1.setParameter("ticketid1", ticketid);
	    	 query1.executeUpdate();
			
			return ticket;
		}
	    @Transactional
	    @Override
	    public Ticket saveAddticket(Ticket ticket,String appId,String employeeId) {
	    	TypedQuery<Ticket> query= entityManager.createQuery("from Ticket where ticketId= :ticketId and employee.employeeId=:employeeId ",Ticket.class);
	        query.setParameter("ticketId",ticket.getTicketId());
	        query.setParameter("employeeId",employeeId);
	        List<Ticket> tick=query.getResultList();
	        Ticket tk=null;
	    	if(tick.size()==0){
	    		ticket.setFlag(1);
	    		ticket.setReleaseTicket("No");
	    		entityManager.persist(ticket);     
	    		return ticket;
	       	}
	    	else
	    	{
	    		return tk;
	    	}
	    	
	    }

	    @Override
		public List<Application> getname() {
	    	 TypedQuery<Application> query = entityManager.createQuery("from Application", Application.class);
	    	 return query.getResultList();
		}
	
	   
	    
	    
	    @Override
	    public List<ReassignTicket> listReassignTicket() {
	    	TypedQuery<ReassignTicket> query = entityManager.createQuery("select new com.acc.sts.model.ReassignTicket(a.ticketId,a.ticketDescription,b.employeeName) from Ticket a left outer join com.acc.sts.model.Employee b on a.employee.employeeId=b.employeeId",ReassignTicket.class);
	        List<ReassignTicket> li=query.getResultList();
	        return li;
	    }
		
		
		@Override
		public List<Employee> getEmpName() {
	    	TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
		        return (query).getResultList();
		}
	    
	  
	    @Transactional
		@Override
		public Ticket ReassignUpdate(String employeeId, String ticketId) {
	    	Ticket t=new Ticket();
	    	Query query1 = entityManager.createQuery("update Ticket set employee.employeeId=:empid WHERE ticketId=:ticketId");
	    	query1.setParameter("empid",employeeId);
	    	 query1.setParameter("ticketId",ticketId);
	   	 query1.executeUpdate();
		    return t;
	    	
	    }
	    @Override
	    public List<Homeadmin> listHomeadmin(String empId) {
	    	System.out.println("in core rep");
	       // Query query = entityManager.createQuery("select new com.acc.sts.model.Homadmin (this_.ticket_id,  this_.Ticket_type,  this_.ticket_desc,  app.Application_Name,  this_.Priority,  inner_.activity,  inner_.status,  inner_.comment_desc,  this_.Tester,  this_.Start_date,  test_.testercommentdesc)  from Ticket this_  where(this_.ticket_id) in (select   a.ticket_id,  status,  activity,  Comment_Desc from Dev_Comment a where(a.ticket_id,a.date) in (select ticket_id,max(date) as date from  Dev_Comment group by  ticket_id ) inner_ join application app on this_.Application_ID=app.Application_ID where(this_.ticket_id) in (select   t.ticket_id,  t.comment_desc as testercommentdesc from   Tester_comment t  where(t.ticket_id,t.date) in (  select ticket_id,  max(date) as date from Tester_comment group by  ticket_id )  test_ on test_.Ticket_ID=this_.Ticket_ID where  (  coalesce(inner_.status,'') = '' or inner_.status<>'Closed' or coalesce(this_.Start_date, '')='' ) and this_.Employee_ID='11386965'");
		    /*   TypedQuery<Homeadmin> query = entityManager.createQuery("select new com.acc.sts.model.Homeadmin(select t.ticketId,t.ticketType,t.ticketDescription,app.applicationName,t.priority,i.activity,i.status,i.commentDescription,test.tester,t.startDate,test.commentDescription)"
		    		   	+"from Ticket t left outer join (select a.ticketId,status,activity,commentDescription from Dev_comment a inner join("
		    		   	+"select ticketId max(date) as date from Dev_Coment group by ticketId) b"
		    		   	+"on a.ticketId=b.ticketId and a.date=b.date) i on i.ticketId=t.ticketId"
		    		   	+"join Application app on t.applicationId=app.applicationId left outer join("
		    		   	+"select test.ticketId,test.commentDescription from tester_comment test inner join("
		    		   	+"select ticketId,max(date) as date from tester_comment group by ticketId)c on"
		    		   	+"test.ticketId=c.ticketId and test.date=c.date) tested on tested.ticketId=t.ticketId where("
		    		   	+"coalesce(i.status,'')='' or i.status<>'Closed' or coalesce(t.startDate,'')='')"
		    		   	+"and t.employee.employeeId='11388271'",Homeadmin.class);*/
	    	TypedQuery<Homeadmin> query = entityManager.createQuery("select new com.acc.sts.model.Homeadmin(t.ticketId,t.ticketType,t.ticketDescription,a.applicationName,t.priority,d.activity,d.status,d.devComment, t.tester,t.startDate,test.testComment) from Ticket t join Application a on t.application.applicationId=a.applicationId left outer join com.acc.sts.model.Dev_comment d on t.ticketId=d.ticket.ticketId left outer join Tester_comment test on t.ticketId=test.ticket.ticketId where t.employee.employeeId=:empId and (( (d.date=t.updatedOn and d.status<>'Closed') or t.startDate is null))",Homeadmin.class);
		   //   TypedQuery<Homeadmin> query = entityManager.createQuery("select new com.acc.sts.model.Homeadmin(t.ticketId,t.ticketType,t.ticketDescription,a.applicationName,t.priority,d.activity,d.status,d.commentDescription,t.tester,t.startDate,test.commentDescription) from Ticket t join Application a on t.application.applicationId=a.applicationId left outer join com.acc.sts.model.Dev_comment d on t.ticketId=d.ticket.ticketId left outer join Tester_comment test on t.ticketId=test.ticket.ticketId where d.date=t.updatedOn and t.employee.employeeId=:empId and  d.status<>'closed'  or t.startDate is null group by d.ticket.ticketId",Homeadmin.class); 
	   // TypedQuery<Homeadmin> query = entityManager.createQuery("select new com.acc.sts.model.Homeadmin(t.ticketId,t.ticketType,t.ticketDescription,a.applicationName,t.priority,d.activity,d.status,d.commentDescription,t.tester,t.startDate,test.commentDescription) from Ticket t join Application a on t.application.applicationId=a.applicationId left outer join com.acc.sts.model.Dev_comment d on t.ticketId=d.ticket.ticketId left outer join Tester_comment test on t.ticketId=test.ticket.ticketId where d.date in(select  max(date) from Dev_comment) and( d.status<>'closed' or t.startDate is null) and t.employee.employeeId='11388271'",Homeadmin.class);
	      //  TypedQuery<Homeadmin> query = entityManager.createQuery("select new com.acc.sts.model.Homeadmin(t.ticket_id,  t.ticket_type,t.ticket_desc,a.application_name,t.priority,d.activity,d.status,d.comment_desc,t.tester,t.start_date,test.comment_desc) from Ticket t  left outer join (select   dev.ticket_id,status,activity,comment_desc from Dev_Comment dev  inner join ( select ticket_id,max(date) as date from  Dev_Comment group by  ticket_id ) b on dev.ticket_id=b.ticket_id and dev.date = b.date )d on d.ticket_id=t.ticket_id join Application a on t.application_id=a.application_id left outer join( select test.ticket_id, test.comment_desc as testercommentdesc from   tester_comment test inner join (  select ticket_id,  max(date) as date from tester_comment group by  ticket_id ) c on test.ticket_id=c.ticket_id and test.date = c.date ) test_ on test_.ticket_id=this_.ticket_id where  (  coalesce(d.status,'') = '' or d.status<>'Closed' or coalesce(t.start_date, '')='' ) and t.employee_id='11386286'",Homeadmin.class);  
	      // Query query = entityManager.createQuery("select t.ticket_id,  t.ticket_type,t.ticket_desc,a.application_name,t.priority,d.activity,d.status,d.comment_desc,t.tester,t.start_date,test.comment_desc from Ticket t  left outer join (select   dev.ticket_id,status,activity,comment_desc from Dev_Comment dev  inner join ( select ticket_id,max(date) as date from  Dev_Comment group by  ticket_id ) b on dev.ticket_id=b.ticket_id and dev.date = b.date )d on d.ticket_id=t.ticket_id join Application a on t.application_id=a.application_id left outer join( select test.ticket_id, test.comment_desc as testercommentdesc from   tester_comment test inner join (  select ticket_id,  max(date) as date from tester_comment group by  ticket_id ) c on test.ticket_id=c.ticket_id and test.date = c.date ) test_ on test_.ticket_id=this_.ticket_id where  (  coalesce(d.status,'') = '' or d.status<>'Closed' or coalesce(t.start_date, '')='' ) and t.employee_id='11386286'",Homeadmin.class);  
		      query.setParameter("empId", empId);
	       List<Homeadmin> li=query.getResultList();
	        System.out.println(li);
	        return li;
	    }

	@Transactional
	@Override
	public List<Clarify> updateresponse(String ticketId, String comment, String eresponse) {
		
		Query query=entityManager.createQuery("update Clarification set employeeResponse=:res,flag=:flag where ticket.ticketId=:ticketId and clarificationDescription=:comment");
		query.setParameter("res",eresponse);
		query.setParameter("flag",1);
		query.setParameter("ticketId", ticketId);
		query.setParameter("comment", comment);
		int a=query.executeUpdate();
        TypedQuery<Clarify> query1 = entityManager.createQuery("select new com.acc.sts.model.Clarify(t.ticketId,c.flag,t.ticketType,e.employeeName,t.ticketDescription,a.applicationName,d.status,c.clarificationDescription,c.employeeResponse)from  Ticket t left outer join Employee e on t.employee.employeeId=e.employeeId left outer join Application a on a.applicationId=t.application.applicationId left outer join Dev_comment d on d.ticket.ticketId=t.ticketId  left outer join Clarification c on c.ticket.ticketId=t.ticketId where c.flag=0 ", Clarify.class);
		return query1.getResultList() ;
		
	}
	
	 @Override
	    public List<Homedoc> listHomedoc(String empId) {
	    	System.out.println("list home doc repostprtirepi");
	        TypedQuery<Homedoc> query = entityManager.createQuery("select new com.acc.sts.model.Homedoc(a.ticketId,a.ticketType,a.ticketDescription,c.applicationName,b.status,d.remedy,d.documentationDescription,d.documentationId,d.documentationComment) from Ticket a join Dev_comment b on a.ticketId=b.ticket.ticketId and b.status='Closed' left outer join Application c on a.application.applicationId=c.applicationId inner join com.acc.sts.model.Documentation d on a.ticketId=d.ticket.ticketId where b.status=:close and ( d.remedy not in( 'close') or d.remedy is null) and a.employee.employeeId=:empId",Homedoc.class);
	       query.setParameter("close", "closed");
	       query.setParameter("empId", empId);
	        List<Homedoc> li=query.getResultList();
	        System.out.println(li);
	        return li;
	    }
	 @Transactional
	    @Override
	    public Documentation saveDocument(Documentation documentation,String ticket_id) {
	    	
	    System.out.println(documentation);
	    	//t.setTicket_id(documentation.getTicket_id());
	    	//documentation.setTicket(t);
	     //  entityManager.merge(documentation);
	        System.out.println("before query in repository");
	        System.out.println(ticket_id);
	        

	  	Query query1 = entityManager.createQuery("update Documentation set remedy= :remedy, documentationComment= :comment, documentationDescription= :descrip WHERE ticket.ticketId=:tid ");
		//query1.setParameter("tid",documentation.getTicket_id());
	 	query1.setParameter("tid", documentation.getTicket().getTicketId());
	  	query1.setParameter("remedy", documentation.getRemedy());
	   	query1.setParameter("comment", documentation.getDocumentationComment());
	    query1.setParameter("descrip", documentation.getDocumentationDescription());
	   
	    query1.executeUpdate();
	        
	       return documentation; 
	    }
	    	
	 @Override 
	    public List<Application> getAppname() {
	    	System.out.println("in repository for application");
			 Query query = entityManager.createQuery("from Application", Application.class);
			 return query.getResultList();
		}
	    
	    @Override 
	    public List<Employee> getTestername()
	    {
	    	System.out.println("in repository for tester");
	    	Query query = entityManager.createQuery("from Employee where role like 'tester'", Employee.class);
			 return query.getResultList();
	   
		}
	     
	   
	    @Transactional
	    @Override
	    public Updatestatus saveStatus(Updatestatus updateStatus) {
	    	System.out.println("in repository");
	    	Ticket ticket=new Ticket();
	    	ticket.setTicketId(updateStatus.getTicketId());
	    	ticket.setTicketType(updateStatus.getTicketType());
	    	ticket.setTicketDescription(updateStatus.getTicketDescription());
	    	ticket.setPriority(updateStatus.getPriority());
	    	ticket.setTester(updateStatus.getTesterName());
	    	ticket.setWorkedOnToday(updateStatus.getWorkedOnToday());
	    	Dev_comment devcomment=new Dev_comment();
	    	devcomment.setActivity(updateStatus.getActivity());
	    	devcomment.setDevComment(updateStatus.getDevComment());
	    	devcomment.setStatus(updateStatus.getStatus());
	    	devcomment.setDate(updateStatus.getDate());
	    	devcomment.setTicket(ticket);
	    	Application  application=new Application();
	    	application.setApplicationName(updateStatus.getApplicationName());
	        //entityManager.merge(ticket);
	    	Query query=entityManager.createQuery("from Dev_comment "+"where date = :date "+ "and ticket.ticketId= :ticketId  ",Dev_comment.class);
	   	     query.setParameter("date",updateStatus.getDate());
	 	     query.setParameter("ticketId",updateStatus.getTicketId());
	 	      query.getResultList();
	 	   System.out.println(query.getResultList().size());
	 	 if(query.getResultList().size()==0)
			{
	  		 entityManager.persist(devcomment);
			}
	 	 else
		 {
		
		  Query query2 = entityManager.createQuery("update Dev_comment set devComment=:devComment,status=:status,activity=:activity" +
				 " where date = :date " + " and ticket.ticketId= :ticketId ");
		  query2.setParameter("devComment",updateStatus.getDevComment());
		  query2.setParameter("status",updateStatus.getStatus());
		  query2.setParameter("activity",updateStatus.getActivity());
		   query2.setParameter("date",updateStatus.getDate());
		   query2.setParameter("ticketId",updateStatus.getTicketId());
		   query2.executeUpdate();
		 }
	 	  Query query3 = entityManager.createQuery("update Ticket set updatedOn=:date,status=:status,tester=:tester,priority=:priority,ticketType=:ticketType,workedOnToday=:workedOnToday" +
	 			 " where  ticketId= :ticketId ");
	 	  query3.setParameter("date",updateStatus.getDate());
	 	  query3.setParameter("status",updateStatus.getStatus());
	 	  query3.setParameter("tester",updateStatus.getTesterName());
	 	  query3.setParameter("priority",updateStatus.getPriority());
	 	  query3.setParameter("ticketType",updateStatus.getTicketType());
	 	  query3.setParameter("workedOnToday",updateStatus.getWorkedOnToday());
	 	// query3.setParameter("application",updateStatus.getApplicationName());
	 	  query3.setParameter("ticketId",updateStatus.getTicketId());
	 	   query3.executeUpdate();
	 	  Query query4=entityManager.createQuery(" update Ticket set startDate=:date where startDate is null and ticketId=:ticket");
	 	   query4.setParameter("date",updateStatus.getDate() );
	 	   query4.setParameter("ticket",updateStatus.getTicketId());
	 	  query4.executeUpdate();
	 	  if(devcomment.getStatus().equals("Closed"))
	 	  {
	 	 Query query5=entityManager.createQuery(" update Ticket set endDate=:date where endDate is null and ticketId=:ticket");
	 	   query5.setParameter("date",updateStatus.getDate() );
	 	   query5.setParameter("ticket",updateStatus.getTicketId());
	 	  query5.executeUpdate();
	 	  }    
	 	 Documentation doc=new Documentation();
	 	 doc.setTicket(ticket);
	 	 if(devcomment.getStatus().equalsIgnoreCase("Closed"))
	 	 {
	 		entityManager.persist(doc);
	 	 }
	 	 return updateStatus; 
	    } 
	    @Override
	    public List<Closed> listClosedticket(String empId) {
	    	 LocalDate date= LocalDate.now();
	        TypedQuery<Closed> query = entityManager.createQuery("select new com.acc.sts.model.Closed(a.ticketId,a.ticketType,a.ticketDescription,c.applicationName,b.status,a.startDate,a.endDate,d.remedy,d.documentationDescription) from Ticket a join Dev_comment b on a.ticketId=b.ticket.ticketId and b.status='Closed' left outer join Application c on a.application.applicationId=c.applicationId join com.acc.sts.model.Documentation d on a.ticketId=d.ticket.ticketId and d.remedy='Close' where a.employee.employeeId=:empId",Closed.class);
	        query.setParameter("empId", empId);
	        List<Closed> li=query.getResultList();
	        System.out.println(li);
	        return li;
	    }
	    
	    @Override
	    public List<Closed> allClosedticket() {
	    	 LocalDate date= LocalDate.now();
	        TypedQuery<Closed> query = entityManager.createQuery("select new com.acc.sts.model.Closed(a.ticketId,a.ticketType,a.ticketDescription,c.applicationName,b.status,a.startDate,a.endDate,d.remedy,d.documentationDescription) from Ticket a join Dev_comment b on a.ticketId=b.ticket.ticketId and b.status='Closed' left outer join Application c on a.application.applicationId=c.applicationId join com.acc.sts.model.Documentation d on a.ticketId=d.ticket.ticketId and d.remedy='Close'",Closed.class);
	        
	        List<Closed> li=query.getResultList();
	        System.out.println(li);
	        return li;
	    }


	    @Transactional
	    @Override
	    public Closed saveClosed(Closed closed) {
	        entityManager.persist(closed);
			return closed;
	    }
	    
	
	    
	    @Transactional
		@Override
		public List<UploadTicket> onchange(List<UploadTicket> file) {
	    	
	  
	    		    	System.out.println("file details:"+file);
			UploadTicket[] upload=file.toArray(new UploadTicket[file.size()]);
			for(int i=0;i<upload.length;i=i+3)
			{
				System.out.println(upload[i].getTicketId());
				UploadTicket up=upload[i];
			entityManager.persist(up);
			Ticket t=new Ticket();
			Application application=new Application();
			 Query query = entityManager.createQuery("Select applicationId from Application where applicationName=:app");
	        query.setParameter("app",upload[i].getApplicationName());
	        List<String> appId=query.getResultList();
	       application.setApplicationId(appId.get(0));
			t.setTicketId(upload[i].getTicketId());
			t.setTicketDescription(upload[i].getTicketDescription());
			t.setReleaseTicket("No");
			t.setApplication(application);
			entityManager.persist(t);
			}
			  return (List<UploadTicket>) file;
		} 
 

		@Override
		public List<UploadTicket> listTicketDump() {
			TypedQuery<UploadTicket> query = entityManager.createQuery("select new com.acc.sts.model.UploadTicket(u.createdBy,u.createdOn) from UploadTicket u where u.createdOn in(select max(createdOn) from UploadTicket)", UploadTicket.class);
			return query.getResultList();
		}
		
		@Override
		public List<Homeadmin> listTesterHome(String empName) {
		System.out.println("tester home page query");
    	TypedQuery<Homeadmin> query = entityManager.createQuery("select new com.acc.sts.model.Homeadmin(t.ticketId,t.ticketType,t.ticketDescription,a.applicationName,t.priority,d.activity,d.status,d.devComment,t.startDate,test.testComment,e.employeeName) from Ticket t join Application a on t.application.applicationId=a.applicationId left outer join com.acc.sts.model.Dev_comment d on t.ticketId=d.ticket.ticketId left outer join Tester_comment test on t.ticketId=test.ticket.ticketId left outer join  com.acc.sts.model.Employee e on t.employee.employeeId=e.employeeId where t.tester=:tester and (d.activity='Testing' or d.activity='Build' or d.activity='Design')  and ((d.date=t.updatedOn and d.status<>'Closed')) group by d.ticket.ticketId",Homeadmin.class);
		/*TypedQuery<Homeadmin> query = entityManager.createQuery("select new com.acc.sts.model.Homeadmin(t.ticketId,t.ticketType,t.ticketDescription,a.applicationName,t.priority,d.activity,d.status,d.commentDescription,t.tester,t.startDate,test.commentDescription,e.employeeName)from Ticket t join Application a on t.application.applicationId=a.applicationId"+
"inner join Employee e on e.employeeId=t.employee.employeeId"+
"left outer join ( select d.ticket.ticketId,activity from Dev_comment d inner join("+
"select dev.ticket.ticketId,max(dev.date) as date from Dev_comment dev group by dev.ticket.ticketId)b"+
"on d.ticket.ticketId=b.ticket.ticketId and d.date=b.date and d.status<>'Closed') inner on"+
"inner.ticketId=t.ticketId left outer join ( select test.ticket.ticketId,test.status,test.commentDescription from"+
"Tester_comment test inner join ( select t1.ticket.ticketId,max(t1.date) as date from Tester_comment t1 group by t1.ticket.ticketId)c"+
"on test.ticket.ticketId=c.ticket.ticketId) tes on tes.ticket.ticketId=t.ticketId"+
"where inner.activity='Testing' or inner.activity='Build' ot onner.activity='Design')and t.tester=:empName",Homeadmin.class);*/

		query.setParameter("tester", empName);
			return query.getResultList();
		}

  
		 @Transactional
		    @Override
		    public Updatestatus saveStatusTester(Updatestatus updateStatus) {
				
		    	Ticket ticket=new Ticket();
		    	ticket.setTicketId(updateStatus.getTicketId());
		    	System.out.println(":in repository:"+ticket.getTicketId());
		    	
		    	Tester_comment tester=new Tester_comment();
		    	tester.setActivity(updateStatus.getActivity());
		    	tester.setTestComment(updateStatus.getTestComment());
		    	tester.setDate(updateStatus.getDate());
		    	tester.setStatus(updateStatus.getStatus());
		    	tester.setTicket(ticket);
		    	
		    	  Dev_comment comment=new Dev_comment();
			 	  comment.setActivity(updateStatus.getActivity());
			 		comment.setDevComment(updateStatus.getDevComment());
			 		comment.setDate(updateStatus.getDate());	
			 		comment.setStatus(updateStatus.getStatus());
			 		comment.setTicket(ticket);
			 		
		  	  if(updateStatus.getActivity().equalsIgnoreCase("Testing"))
		  	  {
		  		  System.out.println("for Testing");
		  		Query query=entityManager.createQuery("from Tester_comment where date = :date "+ "and ticket.ticketId= :ticketId  ",Tester_comment.class);
		   	     query.setParameter("date",updateStatus.getDate());
		 	     query.setParameter("ticketId",updateStatus.getTicketId());
		 	      query.getResultList();
		 	   System.out.println(query.getResultList().size());
		 	  if(query.getResultList().size()==0)
				{
		 		 System.out.println("in testing if loop");
		  		 entityManager.persist(tester);
				}
		 	 else
			 {
		 		 System.out.println("in testing else loop");
			  Query query2 = entityManager.createQuery("update Tester_comment set testComment=:testComment,status=:status,activity=:activity" +
					 " where date = :date " + " and ticket.ticketId= :ticketId ");
			  query2.setParameter("testComment",updateStatus.getTestComment());
			  query2.setParameter("status",updateStatus.getStatus());
			  query2.setParameter("activity",updateStatus.getActivity());
			   query2.setParameter("date",updateStatus.getDate());
			   query2.setParameter("ticketId",updateStatus.getTicketId());
			   query2.executeUpdate(); 
			 }
		 	  }
		  	  //for UAT
		 	  else{
		 		 System.out.println("for UAT");
		 		 Query query3=entityManager.createQuery("from Dev_comment "+"where date = :date "+ "and ticket.ticketId= :ticketId  ",Dev_comment.class);
		   	     query3.setParameter("date",updateStatus.getDate());
		 	     query3.setParameter("ticketId",updateStatus.getTicketId());
		 	      query3.getResultList();
		 	   System.out.println(query3.getResultList().size());
		 	   
		 	  if(query3.getResultList().size()==0)
				{
		 		 System.out.println("in UAT  if in dev_comment loop");
		  		 entityManager.persist(comment);
				}
		 	 else
			 {
		 		System.out.println("in UAT else in dev_comment loop");
			  Query query4 = entityManager.createQuery("update Dev_comment set devComment=:devComment,status=:status,activity=:activity" +
					 " where date = :date " + " and ticket.ticketId= :ticketId ");
			  query4.setParameter("devComment",updateStatus.getTestComment());
			  query4.setParameter("status",updateStatus.getStatus());
			  query4.setParameter("activity",updateStatus.getActivity());
			   query4.setParameter("date",updateStatus.getDate());
			   query4.setParameter("ticketId",updateStatus.getTicketId());
			   query4.executeUpdate();
			 } 


		 	 Query query5=entityManager.createQuery("from Tester_comment where date = :date "+ "and ticket.ticketId= :ticketId  ",Tester_comment.class);
	   	     query5.setParameter("date",updateStatus.getDate());
	 	     query5.setParameter("ticketId",updateStatus.getTicketId());
	 	      query5.getResultList();
	 	   System.out.println(query5.getResultList().size());
	 	  if(query5.getResultList().size()==0)
			{
	 		 System.out.println("in UAT if in tester_comment  loop");
	  		 entityManager.persist(tester);
			}
	 	 else
		 {
	 		 System.out.println("in UAT else in tester_comment loop");
		  Query query6 = entityManager.createQuery("update Tester_comment set testComment=:testComment,status=:status,activity=:activity" +
				 " where date = :date " + " and ticket.ticketId= :ticketId ");
		  query6.setParameter("testComment",updateStatus.getTestComment());
		  query6.setParameter("status",updateStatus.getStatus());
		  query6.setParameter("activity",updateStatus.getActivity());
		   query6.setParameter("date",updateStatus.getDate());
		   query6.setParameter("ticketId",updateStatus.getTicketId());
		   query6.executeUpdate(); 
		 } 
		 		  
	  }
		  	System.out.println("TICKET UPDATE FOR DATE");
		  	  	Query query7 = entityManager.createQuery("update Ticket set updatedOn=:date where  ticketId= :ticketId ");
		 	  query7.setParameter("date",updateStatus.getDate());
		 	query7.setParameter("ticketId",updateStatus.getTicketId());
			 query7.executeUpdate();	  		  
		  		  
			 System.out.println("TICKET UPDATE FOR WORKED ON");
			 Query query8=entityManager.createQuery(" update Ticket set workedOnToday=:work  where ticketId=:ticket");
		 	   query8.setParameter("work",updateStatus.getWorkedOnToday());
		 	//   query8.setParameter("tester",updateStatus.getTesterName());
		 	   query8.setParameter("ticket",updateStatus.getTicketId());
		 	  query8.executeUpdate();	  
		 	  
		    	return updateStatus;
		    }

	
	    
	    @Transactional
		@Override
		public List<Integer> checkpriority(String ticketId, String employeeId) {
			System.out.println("in repository for priority");
	      Query query = entityManager.createQuery("select t.priority from Ticket t join Application a on t.application.applicationId=a.applicationId left outer join com.acc.sts.model.Dev_comment d on t.ticketId=d.ticket.ticketId left outer join Tester_comment test on t.ticketId=test.ticket.ticketId where t.employee.employeeId=:employeeId and (( (d.date=t.updatedOn and d.status<>'Closed') or t.startDate is null)) and t.ticketId<>:ticketId");
			// Query query = entityManager.createQuery("select priority from Ticket where employee.employeeId=:employeeId and ticketId<>:ticketId");
			 query.setParameter("employeeId",employeeId);
			 query.setParameter("ticketId",ticketId);
			 return query.getResultList();
			
		}
	    
	    
	    @Transactional
		@Override
		public List<Integer> checkeditpriority( String employeeId) {
			System.out.println("in repository for priority");
	      Query query = entityManager.createQuery("select t.priority from Ticket t join Application a on t.application.applicationId=a.applicationId left outer join com.acc.sts.model.Dev_comment d on t.ticketId=d.ticket.ticketId left outer join Tester_comment test on t.ticketId=test.ticket.ticketId where t.employee.employeeId=:employeeId and (( (d.date=t.updatedOn and d.status<>'Closed') or t.startDate is null)) ");
			// Query query = entityManager.createQuery("select priority from Ticket where employee.employeeId=:employeeId and ticketId<>:ticketId");
			 query.setParameter("employeeId",employeeId);
           return query.getResultList();
			
		}
	    @Override 
	    public List<Employee> getdevelopername()
	    {
	    	System.out.println("in repository for tester");
	    	Query query = entityManager.createQuery("from Employee where role like 'Developer'", Employee.class);
			 return query.getResultList();
	   
		}   

		@SuppressWarnings("unchecked")
		@Override
		public List<Object> getWorkProgress(String employeeId) {
			Query query = entityManager.createQuery("select SUM(CASE WHEN status='In Progress' THEN 1 ELSE 0 END),SUM(CASE WHEN status='On Hold' THEN 1 ELSE 0 END),SUM(CASE WHEN status='Closed' THEN 1 ELSE 0 END),SUM(CASE WHEN status='Approved' THEN 1 ELSE 0 END)  from Ticket t where employee.employeeId = :employeeId group by employee.employeeId");
	        query.setParameter("employeeId",employeeId);   	
	        return query.getResultList();

		}

}
		    

