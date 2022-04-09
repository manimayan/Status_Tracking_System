package com.acc.sts.core.service;

import java.util.List;

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

public interface TicketService {

    List<UploadTicket> listUploadTicket();

	List<Employee> listEmpName();

	int AssignUpdate(String employeeId, String ticketId,String applicationName,UploadTicket upTicket);

    UploadTicket getTicketDetails(String ticketId);
    List<Edit> listticketdetails(String empId);
	
	 Ticket update(String empId,String ticketId, int newPriority);
	  Ticket update1(String ticketId, String releaseTicket);
		

		List<Releasetic> listRelease();
		List<DeleteTicket> listDeleteTicket();

		Ticket deleteTick(String ticketid);
		Ticket saveAddticket(Ticket ticket,String appId, String employeeId);

		

		   List<Application> getname();

		List<ReassignTicket> listReassignTicket();

		List<Employee> getEmpName();

		Ticket ReassignUpdate(String employeeId, String ticketId);
		 List<Homeadmin> listHomeadmin(String empId);

			List<Clarify> updateresponse(String ticketId, String comment, String eresponse);
			
			List<Homedoc> listHomedoc(String empId);
		 Documentation saveDocument(Documentation documentation,String ticket_id);
		 
		 List<UploadTicket> onchange(List<UploadTicket> file);
		 
		 List<Application> getAppname();
			
			List<Employee> getTestername();

			Updatestatus saveStatus(Updatestatus updateStatus);
			
			 List<Closed> listClosedticket(String empId);
			 
			 List<Closed> allClosedticket();

			    Closed saveClosed(Closed closed);

				List<UploadTicket> listTicketDump();

				Updatestatus saveStatusTester(Updatestatus updateStatus);

				List<Homeadmin> listTesterHome(String empName);

				List<Integer> checkpriority(String ticketId, String employeeId);

				List<Integer> checkeditpriority(String employeeId);

				List<Employee> getdevelopername();

				List<Object> getWorkProgress(String employeeId);
}
