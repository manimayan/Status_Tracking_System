package com.acc.sts.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.acc.sts.model.PublicHoliday;
import com.acc.sts.model.Vacation;
import com.acc.sts.web.client.VacationClient;
import com.acc.sts.web.client.config.ApiOptions;
import com.acc.sts.web.service.VacationService;

@Service
public class VacationServiceImpl implements VacationService {

	@Autowired
	private VacationClient vacClient;
	
	   @Autowired
	    @Qualifier("CoreOptions")
	    private ApiOptions coreOptions;

	    @Autowired
	    @Qualifier("coreRestTemplate")
	    private RestTemplate coreRestTemplate;

	@Override
	public Vacation saveVacation(Vacation vacation) {
		return vacClient.saveVacation(vacation);
	}

	@Override
	public List<Vacation> listPlannedVacation() {
		 return vacClient.listPlannedVacation();
	}

	@Override
	public List<Vacation> listUnPlannedVacation() {
		return vacClient.listUnPlannedVacation();
	}

	@Override
	public PublicHoliday savePublicholidays(List<PublicHoliday> holidays) {
		return vacClient.savePublicHolidays(holidays);
	}

	@Override
	public List<PublicHoliday> listHolidays() {
		return vacClient.listHolidays();
	}

	@Override
	public List<Object> getPlannedVacationMonths(String employeeId) {
		return vacClient.getPlannedVacationMonths(employeeId);
	}

	@Override
	public List<Object> getUnPlannedVacationMonths(String employeeId) {
		return vacClient.getUnPlannedVacationMonths(employeeId);
	}

}
