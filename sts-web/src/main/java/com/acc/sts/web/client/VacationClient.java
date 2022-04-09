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

import com.acc.sts.model.Employee;
import com.acc.sts.model.PublicHoliday;
import com.acc.sts.model.Vacation;
import com.acc.sts.web.client.config.ApiOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class VacationClient {
	
	@Autowired
    @Qualifier("CoreOptions")
    private ApiOptions coreOptions;

    @Autowired
    @Qualifier("coreRestTemplate")
    private RestTemplate coreRestTemplate;
    
    public List<Vacation> listPlannedVacation() {
        log.info("Reterieving List of Planned Vacation");
        ParameterizedTypeReference<List<Vacation>> resultType = new ParameterizedTypeReference<List<Vacation>>() {
        };
        Link link = new Link("/vacation/listPlanned");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
    }
    
    public List<Vacation> listUnPlannedVacation() {
        log.info("Reterieving List of UnPlanned Vacation");
        ParameterizedTypeReference<List<Vacation>> resultType = new ParameterizedTypeReference<List<Vacation>>() {
        };
        Link link = new Link("/vacation/listUnplanned");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
    }
    
    public Vacation saveVacation(Vacation vacation) {
    	System.out.println(vacation.getVacationDateFrom());
        log.info("Save Vacation info");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Vacation> entity = new HttpEntity<>(vacation, headers);
        ParameterizedTypeReference<Vacation> resultType = new ParameterizedTypeReference<Vacation>() {
        };
        Link link = new Link("/vacation/save");
        return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();	
    }

	public PublicHoliday savePublicHolidays(List<PublicHoliday> holidays) {
	    log.info("Save Holiday info");
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<List<PublicHoliday>> entity = new HttpEntity<>(holidays, headers);
	    ParameterizedTypeReference<PublicHoliday> resultType = new ParameterizedTypeReference<PublicHoliday>() {
	    };
	    Link link = new Link("/vacation/savepublicholidays");
	    return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();	   
	}

	public List<PublicHoliday> listHolidays() {
		log.info("Listing holiday info");
        ParameterizedTypeReference <List<PublicHoliday>> resultType =new ParameterizedTypeReference<List<PublicHoliday>>(){
	    };
	    Link link = new Link("/vacation/disableddays");
	    return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.GET, null, resultType).getBody();
	}

	public List<Object> getPlannedVacationMonths(String employeeId) {
	    log.info("Reterieving List of planned vacations");
		 HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_JSON);
	     HttpEntity<Object> entity = new HttpEntity<>(headers);
	     ParameterizedTypeReference<List<Object>> resultType = new ParameterizedTypeReference<List<Object>>() {
	     };
	     Link link = new Link("/vacation/getplannedvacationmonths/"+employeeId);
	     return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
	}

	public List<Object> getUnPlannedVacationMonths(String employeeId) {
	    log.info("Reterieving List of Unplanned vacations");
		 HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_JSON);
	     HttpEntity<Object> entity = new HttpEntity<>(headers);
	     ParameterizedTypeReference<List<Object>> resultType = new ParameterizedTypeReference<List<Object>>() {
	     };
	     Link link = new Link("/vacation/getunplannedvacationmonths/"+employeeId);
	     return coreRestTemplate.exchange(coreOptions.linkTo(link), HttpMethod.POST, entity, resultType).getBody();
	}
	

}
