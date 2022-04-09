package com.acc.sts.web.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
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
import com.acc.sts.web.client.config.ApiOptions;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class StatusClient {
	  @Autowired
	    @Qualifier("CoreOptions")
	    private ApiOptions coreOptions;

	    @Autowired
	    @Qualifier("coreRestTemplate")
	    private RestTemplate coreRestTemplate;
	    
	    public List<Clarify> listclarify() {
	        log.info("retrieving clarification list");
	        ParameterizedTypeReference<List<Clarify>> resultType = new ParameterizedTypeReference<List<Clarify>>() {
	        };
	        Link link = new Link("/report/clarify");
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
	    }

	    
	    public List<Timereport> listNotReporttimeEmpabsence() {
			log.info("Reterieving List of not reportedtime");
	        ParameterizedTypeReference<List<Timereport>> resultType = new ParameterizedTypeReference<List<Timereport>>() {
	        };
	        Link link = new Link("/report/timelistnotrepabsence");
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
		}
	
	
	
	public List<Timereport> listNotReporttimeEmployee() {
		log.info("Reterieving List of not reportedtime");
        ParameterizedTypeReference<List<Timereport>> resultType = new ParameterizedTypeReference<List<Timereport>>() {
        };
        Link link = new Link("/report/timelistnotrep");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
	}
	
	
	    
	    
		public  List<Clarify> searchTicket(Ticket ticket) {
		
			   HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_JSON);
		        HttpEntity<Ticket> entity = new HttpEntity<>(ticket, headers);
		        ParameterizedTypeReference< List<Clarify>> resultType = new ParameterizedTypeReference< List<Clarify>>() {
		        };
		        Link link = new Link("/report/search");
		        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
			
			
		}

		
		public  Clarification updateComment(Clarification clarify) {
			 log.info("trying to update the supervisor comment");

			   HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_JSON);
		        HttpEntity<Clarification> entity = new HttpEntity<>(clarify, headers);
		        ParameterizedTypeReference<Clarification> resultType = new ParameterizedTypeReference<Clarification>() {
		        };
		        Link link = new Link("/report/updateComment");
		        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
		        
		}

		public List<Clarify> deleteclarify(String ticketId,String comment) {
			 log.info("trying to delete clarification details");
			 HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_JSON);
		        HttpEntity<Clarify> entity = new HttpEntity<>(headers);
		        ParameterizedTypeReference<List<Clarify>> resultType = new ParameterizedTypeReference<List<Clarify>>() {
		        };
		        Link link = new Link("/report/deleteclarify/"+ticketId+"/"+comment);
		        System.out.println(entity);
		        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
		}
		 public List<String> getname() {
				
		        log.info("Reterieving List of Employees");
		        ParameterizedTypeReference<List<String>> resultType = new ParameterizedTypeReference<List<String>>() {
		        };
		        Link link = new Link("/report/getname");
		        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
		    }

		
			
		    
			public List<Tickethistory> submit(String ticketId,String employeeName, int days) {
				        ParameterizedTypeReference<List<Tickethistory>> resultType = new ParameterizedTypeReference<List<Tickethistory>>() {
				            };
				            Link link = new Link("/report/Tickethistory/" + ticketId  + "/" + employeeName +"/" + days );
				       return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();	    
			}
			
			public List<NotReportEmployee> listNotReportEmployee() {
		        log.info("Reterieving List of Tickets");
		        
		        ParameterizedTypeReference<List<NotReportEmployee>> resultType = new ParameterizedTypeReference<List<NotReportEmployee>>() {
		        };
		        Link link = new Link("/report/notreportlist");
		        
		        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
		    }
		    
			public List<Dailystatus> listreportdetails() {
		        log.info("Reterieving List of status");
		        
		        ParameterizedTypeReference<List<Dailystatus>> resultType = new ParameterizedTypeReference<List<Dailystatus>>() {
		        };
		        Link link = new Link("/report/listrep");
		        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
		    }

			public List<Dailystatus> listnotreportdetails() {
				log.info("Reterieving List of not reportedstatus");
		        ParameterizedTypeReference<List<Dailystatus>> resultType = new ParameterizedTypeReference<List<Dailystatus>>() {
		        };
		        Link link = new Link("/report/listnotrep");
		        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();

			}
			
			public List<Ticketstatus> ticketstatus(String ticketId, String employeeName) {
				log.info("Reterieving List of Employees");
		        ParameterizedTypeReference<List<Ticketstatus>> resultType = new ParameterizedTypeReference<List<Ticketstatus>>() {
		        };
		        Link link = new Link("/report/ticketstatus"+ "/" + ticketId+"/"+employeeName);
		        System.out.println("after web client ticket status");
		        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
			}
			 
			 public List<Nochange> listNochange() {
			        log.info("Reterieving List");
			        ParameterizedTypeReference<List<Nochange>> resultType = new ParameterizedTypeReference<List<Nochange>>() {
			        };
			        Link link = new Link("/report/listNo");
			        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
			    }

			  public List<Dayticket> listDayticket() {
			        log.info("Reterieving List of Tickets");
			        ParameterizedTypeReference<List<Dayticket>> resultType = new ParameterizedTypeReference<List<Dayticket>>() {
			        };
			        Link link = new Link("/report/daylist");
			        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
			    }
			  public List<Ticket> gettickname() {
					
			       log.info("Reterieving List of tickets");
			       ParameterizedTypeReference<List<Ticket>> resultType = new ParameterizedTypeReference<List<Ticket>>() {
			       };
			       Link link = new Link("/report/gettickname");
			       return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
			   }
			  
			  public List<Employee> getempname() {
					
			        log.info("Reterieving List of Employees");
			        ParameterizedTypeReference<List<Employee>> resultType = new ParameterizedTypeReference<List<Employee>>() {
			        };
			        Link link = new Link("/report/getempname");
			        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
			    }
			    
				public List<Application> getappname() {
					 log.info("Reterieving List of Application");
				        ParameterizedTypeReference<List<Application>> resultType = new ParameterizedTypeReference<List<Application>>() {
				        };
				        Link link = new Link("/report/getappname");
				        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
				}

				public List<Employee> getTestername() {
					
			        log.info("Reterieving List of tester name");
			        ParameterizedTypeReference<List<Employee>> resultType = new ParameterizedTypeReference<List<Employee>>() {
			        };
			        Link link = new Link("/report/gettestername");
			        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
			    }

				public List<Reportsummary> listreportTicket(Reportsummary report) {
					 log.info("Reterieving List of Tickets");
					 ParameterizedTypeReference<List<Reportsummary>> resultType = new ParameterizedTypeReference<List<Reportsummary>>() {
					 };
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					HttpEntity<Reportsummary> entity = new HttpEntity<>(report, headers);
					Link link = new Link("/report/reportlist/");
					return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();	    
					}

				public List<Clarify> listPendingResponse(String empId) {
					log.info("retrieving clarification list to enter response");
			        ParameterizedTypeReference<List<Clarify>> resultType = new ParameterizedTypeReference<List<Clarify>>() {
			        };
			        Link link = new Link("/report/pendingresponse/" + empId);
			        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
			    
					
				}
				
				
}
