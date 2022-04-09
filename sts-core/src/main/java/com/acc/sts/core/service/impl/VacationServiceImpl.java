package com.acc.sts.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acc.sts.core.repository.EmployeeRepository;
import com.acc.sts.core.repository.VacationRepository;
import com.acc.sts.core.service.VacationService;
import com.acc.sts.model.PublicHoliday;
import com.acc.sts.model.Vacation;

@Service
public class VacationServiceImpl implements VacationService {
	
	@Autowired
	VacationRepository repository;

	@Override
	public Vacation saveVacation(Vacation vacation) {
		Vacation list=repository.saveVacation(vacation);
	    return list;  
	}

	@Override
	public List<Vacation> listPlannedVacation() {
	    return repository.listPlannedVacation();
	}

	@Override
	public List<Vacation> listUnPlannedVacation() {
		return repository.listUnPlannedVacation();
	}

	@Override
	public PublicHoliday savePublicholidays(List<PublicHoliday> holidays) {
		PublicHoliday list=repository.savePublicholidays(holidays);
		return list;
	}

	@Override
	public List<PublicHoliday> listHolidays() {
		return repository.listHolidays();
	}

	@Override
	public List<Object> getPlannedVacationMonths(String employeeId) {
		return repository.getPlannedVacationMonths(employeeId);
	}

	@Override
	public List<Object> getUnPlannedVacationMonths(String employeeId) {
		return repository.getUnPlannedVacationMonths(employeeId);
	}



}
