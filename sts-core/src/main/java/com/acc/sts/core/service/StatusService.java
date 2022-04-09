package com.acc.sts.core.service;

import java.util.List;

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

public interface StatusService {

	List<Clarify> listclarify();

	List<Clarify> searchTicket(Ticket ticket1);

	Clarification updateComment(Clarification clarify);

	List<Clarify> deleteclarify(String ticketId,String comment);
	List<String> getname();


	List<Tickethistory> submit(String ticketId,String employeeName, int days);  
	
	  List<NotReportEmployee> listNotReportEmployee();
	  List<Dailystatus> listreportdetails();
		 List<Dailystatus> listnotreportdetails();
		 List<Ticketstatus> ticketstatus(String ticketId, String employeeName);

		 List<Ticket> gettickname();


			List<Nochange> listNochange();
			 List<Dayticket> listDayticket();

			List<Employee> getempname();

			List<Application> getappname();

			List<Employee> getTestername();

			List<Reportsummary> listreportTicket(Reportsummary report);

			List<Clarify> listPendingResponse(String empId);

			List<Timereport> timelistnotreportdetails();

			List<Timereport> timelistnotreportabsencedetails();
}
