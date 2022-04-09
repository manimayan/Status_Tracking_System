package com.acc.sts.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
import com.acc.sts.web.client.StatusClient;
import com.acc.sts.web.client.config.ApiOptions;
import com.acc.sts.web.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService{
	  @Autowired
	    private StatusClient ticketClient;

	    @Autowired
	    @Qualifier("CoreOptions")
	    private ApiOptions coreOptions;

	    @Autowired
	    @Qualifier("coreRestTemplate")
	    private RestTemplate coreRestTemplate;

	    @Override
	    public List<Clarify> listclarify() {
	        return ticketClient.listclarify();
	    }
	    
		@Override
	    public List<Clarify> searchTicket(Ticket ticket)
	   {
		return ticketClient.searchTicket(ticket);
		
		   
	   }

		 @Override
		    public List<Timereport> listNotReporttimeEmployee() {
		        return ticketClient.listNotReporttimeEmployee();
		    }
		    
		    
		    
		    @Override
		    public List<Timereport> listNotReporttimeEmpabsence() {
		        return ticketClient.listNotReporttimeEmpabsence();
		    }

		
		
		@Override
		public Clarification updateComment(Clarification clarify) {
			
			return ticketClient.updateComment(clarify);
		}

		@Override
		public List<Clarify> deleteclarify(String ticketId,String comment) {
			
			return ticketClient.deleteclarify(ticketId,comment);
		}
		@Override
		public List<String> getname() {
			// TODO Auto-generated method stub
			return ticketClient.getname();
		}
		@Override
		public List<Ticket> gettickname() {
			// TODO Auto-generated method stub
			return ticketClient.gettickname();
		}



		@Override
		public List<Tickethistory> submit(String ticketId,String employeeName, int days) {
			// TODO Auto-generated method stub
			System.out.println("in we serviceimpl");
			return ticketClient.submit(ticketId,employeeName,days);
			  
		}
		 @Override
		    public List<NotReportEmployee> listNotReportEmployee() {
		        return ticketClient.listNotReportEmployee();
		    }
		 @Override
		    public List<Dailystatus> listreportdetails() {
		    	System.out.println("before web serviceimpl of status");
		        return ticketClient.listreportdetails();
		    }
		    @Override
			public List<Dailystatus> listnotreportdetails() {
				// TODO Auto-generated method stub
		    	System.out.println("before web serviceimpl of notreported");
				return ticketClient.listnotreportdetails();
			}
		    @Override
			public List<Ticketstatus> ticketstatus(String ticketId, String employeeName) {
				return ticketClient.ticketstatus(ticketId,employeeName);
			}
			
			
			

			@Override
		    public List<Nochange> listNochange() {
		    	System.out.println("in web service");
		    List<Nochange> a=ticketClient.listNochange();
		    System.out.println("checking service"+a);
		    return a; 

		 
		    }
			
			 @Override
			    public List<Dayticket> listDayticket() {
			    	return ticketClient.listDayticket();
			    }
			 
			 @Override
				public List<Employee> getempname() {
					return ticketClient.getempname();
				}
			    
			    
			    @Override
				public List<Application> getappname() {
					return ticketClient.getappname();
				}
			    
			    @Override
			   	public List<Employee> getTestername() {
			   		return ticketClient.getTestername();
			   	}
			    
			    @Override
			    public List<Reportsummary> listreportTicket(Reportsummary report) {
			    	return ticketClient.listreportTicket(report);
			    }

				@Override
				public List<Clarify> listPendingResponse(String empId) {
					return ticketClient.listPendingResponse(empId);
				}
			 
}
