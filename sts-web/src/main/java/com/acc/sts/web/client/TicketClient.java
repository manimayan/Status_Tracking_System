package com.acc.sts.web.client;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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
import com.acc.sts.web.client.config.ApiOptions;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class TicketClient {

    @Autowired
    @Qualifier("CoreOptions")
    private ApiOptions coreOptions;

    @Autowired
    @Qualifier("coreRestTemplate")
    private RestTemplate coreRestTemplate;

	public List<UploadTicket> listUploadTicket() {
		log.info("Reterieving List of Employees");
        ParameterizedTypeReference<List<UploadTicket>> resultType = new ParameterizedTypeReference<List<UploadTicket>>() {
        };
        Link link = new Link("/ticket/list");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
	}

	public List<Employee> listEmpName() {
        log.info("Reterieving List of Employees");
        ParameterizedTypeReference<List<Employee>> resultType = new ParameterizedTypeReference<List<Employee>>() {
        };
        Link link = new Link("/ticket/listempname");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
    }

	 public int AssignUpdate(String employeeId, String ticketId, String applicationName) {
		 log.info("Assigning ticket to Employee");
		 HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_JSON);
	     ParameterizedTypeReference<Integer> resultType = new ParameterizedTypeReference<Integer>() {
			            };
	     Link link = new Link("/ticket/update/" + employeeId + "/"+ ticketId + "/" + applicationName );
	     return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, null, resultType).getBody();	    
	} 
	
	 public List<Edit> listticketdetails(String empId) {
	        log.info("Reterieving List of Tickets");
	       
	        ParameterizedTypeReference<List<Edit>> resultType = new ParameterizedTypeReference<List<Edit>>() {
	        };
	        Link link = new Link("/ticket/listpri"+ "/" + empId);
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
	    }

	    public Ticket update(String empId,String ticketId, int newPriority) {
			
			log.info("Update Employee info");
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Ticket> entity = new HttpEntity<>(headers);
			        ParameterizedTypeReference<Ticket> resultType = new ParameterizedTypeReference<Ticket>() {
			            };
			           
			            Link link = new Link("/ticket/updatepri" + "/" + empId + "/" + ticketId + "/"+ newPriority );
			            
			       return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();	    
		}
	    public List<Releasetic> listRelease() {
			log.info("Reterieving List of Employees");
	        ParameterizedTypeReference<List<Releasetic>> resultType = new ParameterizedTypeReference<List<Releasetic>>() {
	        };
	        Link link = new Link("/ticket/listRelease");
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
		}


	 public Ticket update1(String ticketId, String releaseTicket) {
			log.info("Save Documentation info");
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Ticket> entity = new HttpEntity<>(headers);
	        
	        ParameterizedTypeReference <Ticket> resultType = new ParameterizedTypeReference <Ticket>() {
	        };
	       
	        Link link = new Link("/ticket/update1"+ "/" + ticketId + "/"+ releaseTicket );
	       
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
		}
	 public List<DeleteTicket> listDeleteTicket() {
	        log.info("Reterieving List of Tickets");
	       
	        ParameterizedTypeReference<List<DeleteTicket>> resultType = new ParameterizedTypeReference<List<DeleteTicket>>() {
	        };
	        
	        Link link = new Link("/ticket/listdel");
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
	    }

	   public Ticket deleteTick(String ticketid) {
	        log.info("Save Employee info");
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Employee> entity = new HttpEntity<>(headers);
	        ParameterizedTypeReference<Ticket> resultType = new ParameterizedTypeReference<Ticket>() {
			            };
	       Link link = new Link("/ticket/delete/" + ticketid );
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
	        
	    }
	   public Ticket saveAddticket(Ticket ticket,String appId, String employeeId) {
	        log.info("Save Ticket info");
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Ticket> entity = new HttpEntity<>(ticket, headers);
	        ParameterizedTypeReference<Ticket> resultType = new ParameterizedTypeReference<Ticket>() {
	        };
	        Link link = new Link("/ticket/Add/"+ appId+"/"+employeeId);
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
	    }

	    public List<Application> getname() {
	        log.info("Reterieving List of App");
	        ParameterizedTypeReference<List<Application>> resultType = new ParameterizedTypeReference<List<Application>>() {
	        };
	        Link link = new Link("/ticket/getname");
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
	    }

	    
	    
	    public List<ReassignTicket> listReassignTicket() {
			log.info("Reterieving List of Employees");
	        ParameterizedTypeReference<List<ReassignTicket>> resultType = new ParameterizedTypeReference<List<ReassignTicket>>() {
	        };
	        Link link = new Link("/ticket/listdetails");
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();

		}
		
	public List<Employee> getEmpName() {
			
	        log.info("Reterieving List of Employees");
	        ParameterizedTypeReference<List<Employee>> resultType = new ParameterizedTypeReference<List<Employee>>() {
	        };
	        Link link = new Link("/ticket/getemployeename");
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
	    }
	    
	public Ticket ReassignUpdate(String ticketId, String employeeId) {
	    	log.info("Reassigning ticket to Employee");
			ParameterizedTypeReference<Ticket> resultType = new ParameterizedTypeReference<Ticket>() {
			};
			Link link = new Link("/ticket/reassignticket/" + ticketId + "/"+ employeeId );
			return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, null, resultType).getBody();	    
		}

	public List<Homeadmin> listHomeadmin(String empId) {
        log.info("Reterieving List of my tickets");
        System.out.println("in web client ");
        ParameterizedTypeReference<List<Homeadmin>> resultType = new ParameterizedTypeReference<List<Homeadmin>>() {
        };
        Link link = new Link("/ticket/mytickets/"+empId);
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
    }

public List<Clarify> updateresponse(String ticketId, String comment, String eresponse) {
	 log.info("trying to delete clarification details");
	 HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Clarify> entity = new HttpEntity<>(headers);
        ParameterizedTypeReference<List<Clarify>> resultType = new ParameterizedTypeReference<List<Clarify>>() {
        };
        Link link = new Link("/ticket/updateresponse/"+ticketId+"/"+comment+"/"+eresponse);
        System.out.println(entity);
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
}

public List<Homedoc> listHomedoc(String empId) {
    log.info("Reterieving List of tickets");
    System.out.println("in web client ");
    ParameterizedTypeReference<List<Homedoc>> resultType = new ParameterizedTypeReference<List<Homedoc>>() {
    };
    Link link = new Link("/ticket/homedoc/"+empId); 
    return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
}

 public Documentation saveDocument(Documentation docu,String ticket_id) {
        log.info("Save Documentation info");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Documentation> entity = new HttpEntity<>(docu, headers);
        System.out.println("in web client");
        ParameterizedTypeReference<Documentation> resultType = new ParameterizedTypeReference<Documentation>() {
        };
        System.out.println("in web client b4 try");
        Link link = new Link("/ticket/save/" + ticket_id );
        System.out.println("after web client");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
    }
 public List<Application> getAppname() {
		
     log.info("Reterieving List of application name");
     ParameterizedTypeReference<List<Application>> resultType = new ParameterizedTypeReference<List<Application>>() {
     };
     Link link = new Link("/ticket/getapplication");
     return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
}
 
public List<Employee> getTestername() {
		
     log.info("Reterieving List of tester name");
     ParameterizedTypeReference<List<Employee>> resultType = new ParameterizedTypeReference<List<Employee>>() {
     };
     Link link = new Link("/ticket/gettestername");
     return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
 }



/*public List<Updatestatus> listUpdatestatus() {
 log.info("Reterieving List of status details");
 ParameterizedTypeReference<List<Updatestatus>> resultType = new ParameterizedTypeReference<List<Updatestatus>>() {
 };
 Link link = new Link("/home/list");
 return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
}*/

public Updatestatus saveStatus(Updatestatus sts) {
	        log.info("Save status info");
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Updatestatus> entity = new HttpEntity<>(sts, headers);
	        ParameterizedTypeReference<Updatestatus> resultType = new ParameterizedTypeReference<Updatestatus>() {
	        };
	        Link link = new Link("/ticket/save");
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
}
	
public List<Closed> listClosed(String empId) {
    log.info("Reterieving List of tickets");
    System.out.println("in web client ");
    ParameterizedTypeReference<List<Closed>> resultType = new ParameterizedTypeReference<List<Closed>>() {
    };
    Link link = new Link("/ticket/myclosed/"+ empId);
    return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
}

public List<Closed> listAllClosed() {
    log.info("Reterieving List of tickets");
    System.out.println("in web client ");
    ParameterizedTypeReference<List<Closed>> resultType = new ParameterizedTypeReference<List<Closed>>() {
    };
    Link link = new Link("/ticket/allclosed");
    return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
}

public List<UploadTicket> onchange(List<UploadTicket> tUpload) {
	 log.info("Save Employee info");
	 System.out.println(tUpload); 
	
	 
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       HttpEntity<List<UploadTicket>> entity = new HttpEntity<>(tUpload, headers);
       ParameterizedTypeReference<List<UploadTicket>> resultType = new ParameterizedTypeReference<List<UploadTicket>>() {
       };
       Link link = new Link("/ticket/uploadsave");
       return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
   }

public List<UploadTicket> listTicketDump() {
	log.info("Reterieving ticket dump details");
    ParameterizedTypeReference<List<UploadTicket>> resultType = new ParameterizedTypeReference<List<UploadTicket>>() {
    };
    Link link = new Link("/ticket/ticketdump");
    return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
}

public List<Homeadmin> listTesterHome(String empName) {
        log.info("Reterieving List of my tickets");
        System.out.println("in web client ");
        ParameterizedTypeReference<List<Homeadmin>> resultType = new ParameterizedTypeReference<List<Homeadmin>>() {
        };
        Link link = new Link("/ticket/testerticket/"+empName);
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
    }

public Updatestatus saveStatusTester(Updatestatus sts) {
	        log.info("Save status info");
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Updatestatus> entity = new HttpEntity<>(sts, headers);
	        ParameterizedTypeReference<Updatestatus> resultType = new ParameterizedTypeReference<Updatestatus>() {
	        };
	        Link link = new Link("/ticket/savetester");
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
}

public List<Integer> checkpriority(String ticketId, String employeeId) {
	 log.info("trying to delete clarification details");
	 HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       HttpEntity<Integer> entity = new HttpEntity<>(headers);
       ParameterizedTypeReference<List<Integer>> resultType = new ParameterizedTypeReference<List<Integer>>() {
       };
       Link link = new Link("/ticket/checkpriority/"+ticketId+"/"+employeeId);
       System.out.println(entity);
       return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
}

public List<Integer> checkeditpriority(String employeeId) {
	log.info("trying to delete clarification details");
	 HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<Integer> entity = new HttpEntity<>(headers);
      ParameterizedTypeReference<List<Integer>> resultType = new ParameterizedTypeReference<List<Integer>>() {
      };
      Link link = new Link("/ticket/checkeditpriority/"+employeeId);
      System.out.println(entity);
      return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
}
public List<Employee> getdevelopername() {
	
    log.info("Reterieving List of tester name");
    ParameterizedTypeReference<List<Employee>> resultType = new ParameterizedTypeReference<List<Employee>>() {
    };
    Link link = new Link("/ticket/getdevelopername");
    return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
}

public List<Object> getWorkProgress(String employeeId) {
    log.info("Reterieving List of Employee Work Progress");
	 HttpHeaders headers = new HttpHeaders();
     headers.setContentType(MediaType.APPLICATION_JSON);
     HttpEntity<Object> entity = new HttpEntity<>(headers);
     ParameterizedTypeReference<List<Object>> resultType = new ParameterizedTypeReference<List<Object>>() {
     };
     Link link = new Link("/ticket/getworkprogress/"+employeeId);
     return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
}



}
