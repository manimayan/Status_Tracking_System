package com.acc.sts.web.service.impl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.acc.sts.model.Application;
import com.acc.sts.model.Clarify;
import com.acc.sts.model.Closed;
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
import com.acc.sts.web.client.TicketClient;
import com.acc.sts.web.client.config.ApiOptions;
import com.acc.sts.web.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketClient tickClient;

    @Autowired
    @Qualifier("CoreOptions")
    private ApiOptions coreOptions;

    @Autowired
    @Qualifier("coreRestTemplate")
    private RestTemplate coreRestTemplate;

    @Override
    public List<UploadTicket> listUploadTicket() {
        return tickClient.listUploadTicket();
    }
    

	@Override
	public List<Employee> listEmpName() {
		return tickClient.listEmpName();
	}

	 @Override
	  public int AssignUpdate(String employeeId, String ticketId,String applicationName) {
	       return tickClient.AssignUpdate(employeeId, ticketId, applicationName);
	 } 
	 @Override
	    public List<Edit> listticketdetails(String empId) {
	    	System.out.println("before web client");
	        return tickClient.listticketdetails(empId);
	    }

		@Override
		public Ticket update(String empId,String ticketId, int newPriority) {
			System.out.println("before web client of update");
			return tickClient.update(empId,ticketId,newPriority);
		}
		@Override
		public Ticket update1(String ticketId, String releaseTicket) {
			// TODO Auto-generated method stub
			return tickClient.update1(ticketId,releaseTicket);
		}
		 @Override
			public List<Releasetic> listRelease() {
				
				return tickClient.listRelease();
			}
		 @Override
		    public List<DeleteTicket> listDeleteTicket() {
		        return tickClient.listDeleteTicket();
		    }

			@Override
			public Ticket deleteTick(String ticketid) {
				// TODO Auto-generated method stub
				return tickClient.deleteTick(ticketid);
			}
			 @Override
			    public Ticket saveAddticket(Ticket ticket,String appId,String employeeId) {
			        return tickClient.saveAddticket(ticket, appId,employeeId);
			    }

			    @Override
				public List<Application> getname() {
					return tickClient.getname();
				}
			    
			    
			    
			    @Override
			    public List<ReassignTicket> listReassignTicket() {
			       return tickClient.listReassignTicket();
			    }
			 
			 @Override
				public List<Employee> getEmpName() {
					return tickClient.getEmpName();
				}
			    
			    @Override
			    public Ticket ReassignUpdate(String employeeId, String ticketId) {
			       return tickClient.ReassignUpdate(employeeId, ticketId);
			    }
			    
			    @Override
			    public List<Homeadmin> listHomeadmin(String employeeId) {
			    	System.out.println("in core service");
			        return tickClient.listHomeadmin(employeeId);
			        
			    }


			@Override
			public List<Clarify> updateresponse(String ticketId, String comment, String eresponse) {
				
				return tickClient.updateresponse(ticketId,comment,eresponse);
			}
			
			 @Override
			    public List<Homedoc> listHomedoc(String employeeId){
			    	System.out.println("in web service");
			        return tickClient.listHomedoc(employeeId);
			    }

		 @Override
			    public Documentation saveDocument(Documentation documentation,String ticket_id) {
			    /*	Ticket t=new Ticket();
			    	t.setTicketid(ticket_id);
			    	documentation.setTicket(t);
			    	System.out.println(documentation);
			    */	
				 return tickClient.saveDocument(documentation,ticket_id);
			    	
			    }
			    
		 @Override
			public List<Application> getAppname() {
				// TODO Auto-generated method stub
				return tickClient.getAppname();
			}
		    
		    @Override
		   	public List<Employee> getTestername() {
		   		// TODO Auto-generated method stub
		   		return tickClient.getTestername();
		   	}
		    
		   /* @Override
		    public List<Updatestatus> listUpdatestatus() {
		        return homeClient.listUpdatestatus();
		    }*/
		    
		    @Override
		    public Updatestatus saveStatus(Updatestatus updatestatus) {
		        System.out.println("in web service");
		        Updatestatus res =  tickClient.saveStatus(updatestatus);
				 System.out.println(res);
			         return res;
		    }

		    @Override
		    public List<Closed> listClosed(String empId){
		    	System.out.println("in web service");
		        return tickClient.listClosed(empId);
		    }
		    
		    @Override
		    public List<Closed> listAllClosed(){
		    	System.out.println("in web service");
		        return tickClient.listAllClosed();
		    }


			@Override
			public Closed saveClosed(Closed closed) {
				// TODO Auto-generated method stub
				return null;
			}
			
			
			
			
			@Override
			public List<UploadTicket> onchange(List<UploadTicket> tUpload) {
				return tickClient.onchange(tUpload);

			}
			





			@Override
			public List<UploadTicket> readData(Sheet sheetAt, String employeeName) {
				 {
					
					 System.out.println("sheet data:"+sheetAt.getLastRowNum());
						@SuppressWarnings("unused")
						
						String tick = null;
						
				LocalDateTime createdOn = LocalDateTime.now();   
						
					        System.out.println(createdOn);
					        System.out.println("web service");
					
						List<UploadTicket> tUpload = new ArrayList<UploadTicket>();
						int rowNum = sheetAt.getLastRowNum();
						for (int i = 0; i <= rowNum; i++) {
							Row nextRow = (Row) sheetAt.getRow(i);
							if (((org.apache.poi.ss.usermodel.Row) nextRow).getRowNum() != 0) {
								Iterator<Cell> cellIterator = ((org.apache.poi.ss.usermodel.Row) nextRow).cellIterator();
								UploadTicket ult = new UploadTicket();
								while (cellIterator.hasNext()) {
									Cell nextCell = cellIterator.next();
									int columnindex = nextCell.getColumnIndex();
									
									if (columnindex == 0) {
										String id = nextCell.getStringCellValue();
										tick = id.replaceAll("\\s", "");
										ult.setTicketId(tick);
									} else if (columnindex == 1) {
										ult.setTicketDescription(nextCell.getStringCellValue());
									} else if (columnindex == 2) {
										ult.setApplicationName(nextCell.getStringCellValue());
									} else {
										continue;
									}
									ult.setCreatedOn(createdOn);
									System.out.println(employeeName);
									ult.setCreatedBy(employeeName);
									tUpload.add(ult);
									
								}
							}

						}
						System.out.println(tUpload);
						return tUpload;
					} 

			}


			@Override
			public List<UploadTicket> listTicketDump() {
				 return tickClient.listTicketDump();
			}

		
			 @Override
			    public List<Homeadmin> listTesterHome(String empName) {
			    	System.out.println("in core service");
			        return tickClient.listTesterHome(empName);
			        
			    }
			 @Override
		    public Updatestatus saveStatusTester(Updatestatus updatestatus) {
		        
			         return tickClient.saveStatusTester(updatestatus);
		    }
			 
			 @Override
			    public List<Integer> checkpriority(String ticketId, String employeeId){
			    	System.out.println("in web service");
			        return tickClient.checkpriority(ticketId,employeeId);
			    }
				
				@Override
			    public List<Integer> checkeditpriority( String employeeId){
			    	System.out.println("in web service");
			        return tickClient.checkeditpriority(employeeId);
			    }
				@Override
			   	public List<Employee> getdevelopername() {
			   		// TODO Auto-generated method stub
			   		return tickClient.getdevelopername();
			   	}


				@Override
				public List<Object> getWorkProgress(String employeeId) {
					return tickClient.getWorkProgress(employeeId);				
					}
			
}



