package com.acc.sts.web.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

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

	int AssignUpdate(String employeeId, String ticketId,String applicationName);
	List<Edit> listticketdetails(String empId);
	Ticket update(String empId,String ticketId, int newPriority);
	Ticket update1(String ticketId, String releaseTicket);

	List<Releasetic> listRelease();
	List<DeleteTicket> listDeleteTicket();

	Ticket deleteTick(String ticketid);
	Ticket saveAddticket(Ticket ticket,String appId, String employeeId);

	List<Application> getname();

	
	List<Employee> getEmpName();
	
	List<ReassignTicket> listReassignTicket();

	Ticket ReassignUpdate(String employeeId, String ticketId);

	 List<Homeadmin> listHomeadmin(String employeeId);

	List<Clarify> updateresponse(String ticketId, String comment, String eresponse);
		
	List<Homedoc> listHomedoc(String employeeId);
	
	Documentation saveDocument(Documentation documentation,String ticket_id);

	List<Application> getAppname();

	List<Employee> getTestername();

	 Updatestatus saveStatus(Updatestatus updatestatus);
	 
	 List<Closed> listClosed(String empId);
	 
	 List<Closed> listAllClosed();

	    Closed saveClosed(Closed closed);

		List<UploadTicket> onchange(List<UploadTicket> tUpload);

		List<UploadTicket> readData(Sheet sheetAt, String  employeeName);

		List<UploadTicket> listTicketDump();

		List<Homeadmin> listTesterHome(String empName);

		Updatestatus saveStatusTester(Updatestatus updatestatus);

		List<Integer> checkpriority(String ticketId, String employeeId);

		List<Integer> checkeditpriority(String employeeId);

		List<Employee> getdevelopername();

		List<Object> getWorkProgress(String employeeId);
}
