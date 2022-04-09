package com.acc.sts.web.client;

import java.util.Date;
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

import com.acc.sts.model.Employee;
import com.acc.sts.model.Timereport;
import com.acc.sts.model.Timing;
import com.acc.sts.web.client.config.ApiOptions;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class TimingClient {

    @Autowired
    @Qualifier("CoreOptions")
    private ApiOptions coreOptions;

    @Autowired
    @Qualifier("coreRestTemplate")
    private RestTemplate coreRestTemplate; 
    public Timing saveIntime(Timing intime) {
    	System.out.println("in web client");
    	System.out.println("in web client"+intime); 
        log.info("Save ");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Timing> entity = new HttpEntity<>(intime, headers);
        ParameterizedTypeReference<Timing> resultType = new ParameterizedTypeReference<Timing>() {
        };
        Link link = new Link("/timing/in-time");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
    }
    
    public Timing saveTiming(Timing outtime) {
        log.info("Save  info");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Timing> entity = new HttpEntity<>(outtime, headers);
        ParameterizedTypeReference<Timing> resultType = new ParameterizedTypeReference<Timing>() {
        };
        System.out.println("web-client");
        System.out.println(outtime.getEmployee()+""+outtime.getOutTime());

       Link link = new Link("/timing/outTime");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
    }

    public List<Employee> getname() {
		
        log.info("Reterieving List of Employees");
        ParameterizedTypeReference<List<Employee>> resultType = new ParameterizedTypeReference<List<Employee>>() {
        };
        System.out.println("web-client");
        Link link = new Link("/timing/getname");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
    }
    

	/*public List<Timereport> submitEmpdetails(String employeeId, String fdate, String todate) {
		System.out.println("in web client");
		// Link link = new Link("/timing/timereport" + employeeId + fdate + todate );
		        ParameterizedTypeReference<List<Timereport>> resultType = new ParameterizedTypeReference<List<Timereport>>() {
		            };
		            System.out.println("web-client");
		            Link link = new Link("/timing/timereport/" + employeeId + "/"+ fdate + "/" + todate );
		       List<Timereport> a=  coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
		       System.out.println("a"+a);
	       return a;
	}*/

    public List<Timereport> submitEmpdetails(Timereport report) {
		System.out.println("in web client");
		 HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<Timereport> entity = new HttpEntity<>(report, headers);
		        ParameterizedTypeReference<List<Timereport>> resultType = new ParameterizedTypeReference<List<Timereport>>() {
		            };
		           
		            System.out.println("web-client");
		            Link link = new Link("/timing/timereport");
		       List<Timereport> a=  coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
		       System.out.println("a"+a);
		       return a;
	}
    





    
}
