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

import com.acc.sts.model.Edit;
import com.acc.sts.model.Employee;
import com.acc.sts.model.Question;
import com.acc.sts.model.Questioncheck;
import com.acc.sts.model.Security;
import com.acc.sts.model.Ticket;
import com.acc.sts.web.client.config.ApiOptions;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class EmployeeClient {

    @Autowired
    @Qualifier("CoreOptions")
    private ApiOptions coreOptions;

    @Autowired
    @Qualifier("coreRestTemplate")
    private RestTemplate coreRestTemplate;
    
    public List<Employee> searchEmployee(Employee emp)
    {
    	 HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         HttpEntity<Employee> entity = new HttpEntity<>(emp, headers);
         ParameterizedTypeReference<List<Employee>> resultType = new ParameterizedTypeReference<List<Employee>>() {
         };
         Link link = new Link("/employee/search");
         return  coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
    }
    
    
    public List<Employee> searchEmployeeById(Employee emp)
    {
    	 HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         HttpEntity<Employee> entity = new HttpEntity<>(emp, headers);
         ParameterizedTypeReference<List<Employee>> resultType = new ParameterizedTypeReference<List<Employee>>() {
         };
         Link link = new Link("/employee/searchbyid");
         return  coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
    }

    
    public List<Employee> listEmployee() {
        log.info("Reterieving List of Employees");
        ParameterizedTypeReference<List<Employee>> resultType = new ParameterizedTypeReference<List<Employee>>() {
        };
        Link link = new Link("/employee/list");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
    }
    
    public Employee saveEmployee(Employee emp) {
        log.info("Save Employee info");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Employee> entity = new HttpEntity<>(emp, headers);
        ParameterizedTypeReference<Employee> resultType = new ParameterizedTypeReference<Employee>() {
        };
        Link link = new Link("/employee/save");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
    }
    
    public Employee saveEmployeebyuser(Employee emp) {
        log.info("Save Employee info");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Employee> entity = new HttpEntity<>(emp, headers);
        ParameterizedTypeReference<Employee> resultType = new ParameterizedTypeReference<Employee>() {
        };
        Link link = new Link("/employee/saveemp");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
    }
    

    public Employee editEmployee(Employee emp) {
           log.info("Edit Employee info");
          	System.out.println("edit web client");

           HttpHeaders headers = new HttpHeaders();
           headers.setContentType(MediaType.APPLICATION_JSON);
           HttpEntity<Employee> entity = new HttpEntity<>(emp, headers);
           ParameterizedTypeReference<Employee> resultType = new ParameterizedTypeReference<Employee>() {
           };
           Link link = new Link("/employee/edit");
           return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
       }
       
       public List<String> getname() {
   		
           log.info("Reterieving List of Employees");
           ParameterizedTypeReference<List<String>> resultType = new ParameterizedTypeReference<List<String>>() {
           };
           Link link = new Link("/employee/getname");
           return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
       }

       public  Employee changepass(String employeeId, String password, String newpassword ) {
	        log.info("change password info");
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Employee> entity = new HttpEntity<>(headers);
	        ParameterizedTypeReference<Employee> resultType = new ParameterizedTypeReference<Employee>() {
	        };
	        Link link = new Link("/employee/change/"+employeeId+"/"+password+"/"+newpassword);
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
	    }
       
       
       public  Employee resetpass(String employeeId, String newpassword ) {
	        log.info("change password info");
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Employee> entity = new HttpEntity<>(headers);
	        ParameterizedTypeReference<Employee> resultType = new ParameterizedTypeReference<Employee>() {
	        };
	        Link link = new Link("/employee/reset/"+employeeId+"/"+newpassword);
	        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
	    }
       

	public List<String> supervisorlist() {
		 log.info("Reterieving List of Supervisors");
         ParameterizedTypeReference<List<String>> resultType = new ParameterizedTypeReference<List<String>>() {
         };
         Link link = new Link("/employee/supervisorlist");
         return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
	}

	public Employee listmodify(String employeeId) {
		 log.info("Employee Details");
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.APPLICATION_JSON);
       HttpEntity<String> entity = new HttpEntity<>(employeeId, headers);
       ParameterizedTypeReference<Employee> resultType = new ParameterizedTypeReference<Employee>() {
       };
       Link link = new Link("/employee/getmodify/"+employeeId);
       return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
	}
    
	public Security saveSecurity(Security security) {
        log.info("Saving Security Details");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Security> entity = new HttpEntity<>(security,headers);
        ParameterizedTypeReference<Security> resultType = new ParameterizedTypeReference<Security>() {
        };
        Link link = new Link("/employee/security");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
	}
	
	public List<Security> securityCheck(Security security) {
		log.info("Retrieving Security details");
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<Security> entity = new HttpEntity<>(security, headers);
	    ParameterizedTypeReference<List<Security>> resultType = new ParameterizedTypeReference<List<Security>>() {
	    };
	    Link link = new Link("/employee/securitycheck");
	    return  coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
	}

	public List<Question> getQuestion() {
		
		log.info("Reterieving List of Employees");
        ParameterizedTypeReference<List<Question>> resultType = new ParameterizedTypeReference<List<Question>>() {
        };
        Link link = new Link("/employee/getquestion");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
	}
	

public List<Questioncheck> listquestiondetails(String empId) {
    log.info("Reterieving List of Questions");
    
    ParameterizedTypeReference<List<Questioncheck>> resultType = new ParameterizedTypeReference<List<Questioncheck>>() {
    };
    Link link = new Link("/employee/listquestion"+ "/" + empId);
    return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
}

public List<Questioncheck> getSaveans(List<Questioncheck> answer) {
	log.info("Retrieving Security details");
	HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<List<Questioncheck>> entity = new HttpEntity<>(answer, headers);
    ParameterizedTypeReference<List<Questioncheck>> resultType = new ParameterizedTypeReference<List<Questioncheck>>() {
    };
    Link link = new Link("/employee/getSaveans");
    return  coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
}

public List<Questioncheck> getSecurityDetails1(List<Questioncheck> answer) {
	log.info("Retrieving Security details");
	HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<List<Questioncheck>> entity = new HttpEntity<>(answer, headers);
    ParameterizedTypeReference<List<Questioncheck>> resultType = new ParameterizedTypeReference<List<Questioncheck>>() {
    };
    Link link = new Link("/employee/getdetails");
    return  coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
}

public List<Employee> getEmployee() {
	
    log.info("Reterieving List of tickets");
    ParameterizedTypeReference<List<Employee>> resultType = new ParameterizedTypeReference<List<Employee>>() {
    };
    Link link = new Link("/report/getemployee");
    return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
}

/*public List<Questioncheck> deleteEmp(List<Questioncheck> answer) {
	log.info("Retrieving Security details");
	HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<List<Questioncheck>> entity = new HttpEntity<>(answer, headers);
    ParameterizedTypeReference<List<Questioncheck>> resultType = new ParameterizedTypeReference<List<Questioncheck>>() {
    };
    Link link = new Link("/employee/deleteEmp");
    return  coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
}*/

public List<Questioncheck> deleteEmp(String empId) {
    log.info("Reterieving List of Questions");
    
    ParameterizedTypeReference<List<Questioncheck>> resultType = new ParameterizedTypeReference<List<Questioncheck>>() {
    };
    Link link = new Link("/employee/deleteemp"+ "/" + empId);
    return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
}

public List<Employee> AdminEmployee() {
    log.info("Reterieving List of Employees");
    ParameterizedTypeReference<List<Employee>> resultType = new ParameterizedTypeReference<List<Employee>>() {
    };
    Link link = new Link("/employee/admin");
    return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
}

}