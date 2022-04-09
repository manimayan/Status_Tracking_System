package com.acc.sts.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.acc.sts.core.repository.TicketRepository;

import com.acc.sts.core.service.TicketService;
import com.acc.sts.model.Application;
import com.acc.sts.model.Clarify;
import com.acc.sts.model.Closed;
import com.acc.sts.model.Dayticket;
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

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository repository;

	@Override
	public List<UploadTicket> listUploadTicket(){
		return repository.listUploadTicket();
    }

	@Override
	public List<Employee> listEmpName(){
		return repository.listEmpName();
	}

	@Override
	public int AssignUpdate(String employeeId, String ticketId,String applicationName,UploadTicket upTicket) {
		return repository.AssignUpdate(employeeId,ticketId,applicationName,upTicket);
	}

	@Override
	public UploadTicket getTicketDetails(String ticketId) {
		return repository.getTicketDetails(ticketId);
	}
	 @Override
	    public List<Edit> listticketdetails(String empId) {
		 
	        return repository.listticketdetails(empId);
	    }
	@Override
	public Ticket update(String empId,String ticketId, int newPriority) {
		
		return repository.update(empId,ticketId,newPriority);
	}
	@Override
	public List<Releasetic> listRelease() {
		// TODO Auto-generated method stub
		 return repository.listRelease();
	}
	@Override
	public Ticket update1(String ticketId, String releaseTicket) {
		// TODO Auto-generated method stub
		return repository.update1(ticketId,releaseTicket);
	}
	
	 @Override
	    public List<DeleteTicket> listDeleteTicket() {
	        return repository.listDeleteTicket();
	    }

		@Override
		public Ticket deleteTick(String ticketid) {
			// TODO Auto-generated method stub
			return repository.deleteTick(ticketid);
		}
		 @Override
		    public Ticket saveAddticket(Ticket ticket,String appId,String employeeId) {
		    	Application appl=new Application();
		    	appl.setApplicationId(appId);
		    	ticket.setApplication(appl);
		    	Employee empl=new Employee();
		    	empl.setEmployeeId(employeeId);
		    	ticket.setEmployee(empl);
		        return repository.saveAddticket(ticket,appId,employeeId);
		    }
		    
		    @Override
			public List<Application> getname() {
				return repository.getname();
			}
		    
		    
		    @Override
		    public List<ReassignTicket> listReassignTicket() {
		    	return repository.listReassignTicket();
		       
		    }
		 
		 @Override
			public List<Employee> getEmpName() {
				return repository.getEmpName();
			}
		   
		   @Override
			public Ticket ReassignUpdate(String employeeId, String ticketId) {
				return repository.ReassignUpdate(employeeId,ticketId);
			}

		   @Override
		    public List<Homeadmin> listHomeadmin(String empId) {
		    	System.out.println("in core service");
		        return repository.listHomeadmin(empId);
		        
		    }

		 @Override
			public List<Clarify> updateresponse(String ticketId, String comment, String eresponse) {
				
				return repository.updateresponse(ticketId,comment,eresponse);
			}
		 
		 @Override
		    public List<Homedoc> listHomedoc(String empId){
		    	System.out.println("in web service");
		        return repository.listHomedoc(empId);
		    }
		 public Documentation saveDocument(Documentation documentation,String ticket_id) {
				
				
				
				Ticket t=new Ticket();
				t.setTicketId(ticket_id);
				documentation.setTicket(t);
				System.out.println("idss"+documentation);
				return repository.saveDocument(documentation,ticket_id);
				
			}
		 
		 @Override
		   	public List<Application> getAppname() {
		   		// TODO Auto-generated method stub
		   		return repository.getAppname();
		   	}

		    @Override
		   	public List<Employee> getTestername() {
		   		// TODO Auto-generated method stub
		   		return repository.getTestername();
		   	}
		    
		    /*@Override
		    public List<Updatestatus> listUpdatestatus() {
		        return repository.listUpdatestatus();
		    }*/
		    
		    @Override
		    public Updatestatus saveStatus(Updatestatus updateStatus) {
		    	System.out.println("in core controller");
		        Updatestatus res=repository.saveStatus(updateStatus);
		        System.out.println(res);
		        return res;
		    }
		    
		    @Override
		    public List<Closed> listClosedticket(String empId) {
		    	System.out.println("in core service");
		        return repository.listClosedticket(empId);
		        
		    }
		    
		    @Override
		    public List<Closed> allClosedticket() {
		    	System.out.println("in core service");
		        return repository.allClosedticket();
		        
		    }

			@Override
			public Closed saveClosed(Closed closed) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<UploadTicket> onchange(List<UploadTicket> file) {
				
				return repository.onchange(file);
			}

			@Override
			public List<UploadTicket> listTicketDump() {
				return repository.listTicketDump();
			}
			

			 @Override
					    public Updatestatus saveStatusTester(Updatestatus updateStatus) {
					    	
					        return repository.saveStatusTester(updateStatus);
					    }

			@Override
						public List<Homeadmin> listTesterHome(String empName) {
							
							return repository.listTesterHome(empName);
						}

@Override
		    public List<Integer> checkpriority(String ticketId, String employeeId){
		    	System.out.println("in web service");
		        return repository.checkpriority(ticketId,employeeId);
		    }
			
			@Override
		    public List<Integer> checkeditpriority(String employeeId){
		    	System.out.println("in web service");
		        return repository.checkeditpriority(employeeId);
		    }
			@Override
		   	public List<Employee> getdevelopername() {
		   		// TODO Auto-generated method stub
		   		return repository.getdevelopername();
		   	}

			@Override
			public List<Object> getWorkProgress(String employeeId) {
				return repository.getWorkProgress(employeeId);
			}




}
