package com.acc.sts.core.repository;

import java.util.List;

import com.acc.sts.model.PublicHoliday;
import com.acc.sts.model.Vacation;

public interface VacationRepository {
	
	
	List<Vacation> listPlannedVacation();
	
	List<Vacation> listUnPlannedVacation();
	
	Vacation saveVacation(Vacation vacation);
	
	PublicHoliday savePublicholidays(List<PublicHoliday> holidays);

	List<PublicHoliday> listHolidays();

	List<Object> getPlannedVacationMonths(String employeeId);

	List<Object> getUnPlannedVacationMonths(String employeeId);

	 	

}
