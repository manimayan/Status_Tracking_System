package com.acc.sts.web.service;

import java.util.List;

import com.acc.sts.model.PublicHoliday;
import com.acc.sts.model.Vacation;

public interface VacationService {

	Vacation saveVacation(Vacation vacation);

	List<Vacation> listPlannedVacation();
	
	List<Vacation> listUnPlannedVacation();

	PublicHoliday savePublicholidays(List<PublicHoliday> holidays);

	List<PublicHoliday> listHolidays();

	List<Object> getPlannedVacationMonths(String employeeId);

	List<Object> getUnPlannedVacationMonths(String employeeId);

}
