package com.acc.sts.core.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.acc.sts.core.repository.StatusRepository;
import com.acc.sts.core.service.StatusService;
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

@Service
public class StatusServiceImpl implements StatusService{


    @Autowired
    StatusRepository repository;
    @Override
    public List<Clarify> listclarify() {
        return repository.listclarify();
    }


	@Override
	public  List<Clarify> searchTicket(Ticket ticket) {
     
		return repository.searchTicket(ticket);
	}


	 @Override
		public List<Timereport> timelistnotreportdetails() {
			
			return repository.timelistnotreportdetails();
		}
		
		
		
		@Override
		public List<Timereport> timelistnotreportabsencedetails() {
			
			return repository.timelistnotreportabsencedetails();
		}
	
	
	public Clarification updateComment(Clarification clarify){
	return repository.updateComment(clarify);
		
		
	}


	@Override
	public List<Clarify> deleteclarify(String ticketId,String comment) {
		
		return repository.deleteclarify(ticketId,comment);
	}
	
	@Override
	public List<String> getname() {
		// TODO Auto-generated method stub
		return repository.getname();
	}


	@Override
	public List<Tickethistory> submit(String ticketId,String employeeName, int days) {
		// TODO Auto-generated method stub
		 
		 
		 List<Tickethistory> a=	 repository.submit(ticketId,employeeName,days);
         System.out.println("in core service checking:"+a);
        /* List<Tickethistory> tc = new ArrayList<Tickethistory>();*/
        /* if(a!=null){
         Iterator i = a.iterator();
         while (i.hasNext()){
        	 Tickethistory th = (Tickethistory) i.next();
        	 LocalDate date = th.getDate();
        	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	 sdf.format(date);
        	 th.setDate(date);
        	 tc.add(th);
         }
         }*/
		 return a;
	}
	

@Override
public List<Ticket> gettickname() {
	// TODO Auto-generated method stub
	return repository.gettickname();
}
	  @Override
	    public List<NotReportEmployee> listNotReportEmployee() {
	        return repository.listNotReportEmployee();
	    }

	  @Override
	    public List<Dailystatus> listreportdetails() {
		
	        return repository.listreportdetails();
	    }

	@Override
	public List<Dailystatus> listnotreportdetails() {
		
		return repository.listnotreportdetails();
	}
	@Override
	public List<Ticketstatus> ticketstatus(String ticketId, String employeeName) {
		return repository.ticketstatus(ticketId,employeeName);
	}
	
	
	
	@Override
    public List<Nochange> listNochange() {
	 
    List<Nochange> a=repository.listNochange();
    System.out.println("checking service"+a);
    return a; 
	}
	
	@Override
    public List<Dayticket> listDayticket() {
    	return repository.listDayticket();
    }
	
	 @Override
		public List<Employee> getempname() {
			return repository.getempname();
		}
	    
	   
	   @Override
		public List<Application> getappname() {
			return repository.getappname();
		}
	   
	   
	   @Override
	   public List<Reportsummary> listreportTicket(Reportsummary report) {
		   return repository.listreportTicket(report);
	   }

	   @Override
	  	public List<Employee> getTestername() {
	  		return repository.getTestername();
	  	}


	@Override
	public List<Clarify> listPendingResponse(String empId) {
		 return repository.listPendingResponse(empId);
	}

	
}
