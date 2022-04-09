package com.acc.sts.core.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acc.sts.core.service.VacationService;
import com.acc.sts.model.PublicHoliday;
import com.acc.sts.model.Vacation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/vacation")
public class VacationController {

	@Autowired
	private VacationService service;

	@GetMapping("/listPlanned")
	public @ResponseBody ResponseEntity<List<Vacation>> listPlannedVacation() {
		log.info("Retrieving list of Planned Vacations");
		HttpStatus status = HttpStatus.NOT_FOUND;
		List<Vacation> list = null;
		try {
			list = service.listPlannedVacation();
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.info("Error while Fetching list of Planned Vacations", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(list, status);
	}


	@GetMapping("/listUnplanned")
	public @ResponseBody ResponseEntity<List<Vacation>> listVacation() {
		log.info("Retrieving list of Unplanned Vacations");
		HttpStatus status = HttpStatus.NOT_FOUND;
		List<Vacation> list = null;
		try {
			list = service.listUnPlannedVacation();
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.info("Error while Fetching list of Unplanned Vacations", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(list, status);
	}



	@PostMapping("/save")
	public @ResponseBody ResponseEntity<Vacation> saveVacation(@RequestBody Vacation vacation) {
		log.info("Save Vacation details");
		System.out.println("vacation");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		System.out.println("before"+vacation);
		Vacation vac = null;
		try {
			vac = service.saveVacation(vacation);
			status = HttpStatus.CREATED;
			System.out.println("vacatrion aft persist:"+vac);
	
		} catch (Exception e) {
			log.error("Error while saving Employee details ", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(vac, status);
	}


	@PostMapping("/savepublicholidays")
	public @ResponseBody ResponseEntity<PublicHoliday> savePublicholidays(@RequestBody List<PublicHoliday> holidays) {
		log.info("Save Public Holiday ");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		PublicHoliday hol=null;
		try {
			hol = service.savePublicholidays(holidays);
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			log.error("Error while saving Holidays ", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(hol, status);
	}
	
	
	@GetMapping("/disableddays")
	public @ResponseBody ResponseEntity<List<PublicHoliday>> listHolidays()
	{
		log.info("Retrieving list of holidays");
		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<PublicHoliday> hol = null;
		try{
			hol = service.listHolidays();
			status = HttpStatus.OK;
		}catch(Exception e)
		{
			log.error("Error while listing the holidays",e);
			status= HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(hol, status);
		
	}
	
	@PostMapping("/getplannedvacationmonths/{employeeId}")
	public @ResponseBody ResponseEntity<List<Object>> getPlannedVacationMonths(@PathVariable("employeeId") String employeeId) {
		log.info("List of planned vacations");
	    HttpStatus status = HttpStatus.BAD_REQUEST;
	    List<Object> plannedvacationmonths = null;
	    try {
	    	plannedvacationmonths = service.getPlannedVacationMonths(employeeId);
	         status = HttpStatus.CREATED;
	     } catch (Exception e) {
	         log.error("Error while listing planned vacations ", e);
	         status = HttpStatus.INTERNAL_SERVER_ERROR;
	     }
	     return new ResponseEntity<>(plannedvacationmonths, status);
	}
 
	@PostMapping("/getunplannedvacationmonths/{employeeId}")
	public @ResponseBody ResponseEntity<List<Object>> getUnPlannedVacationMonths(@PathVariable("employeeId") String employeeId) {
		log.info("List of planned vacations");
	    HttpStatus status = HttpStatus.BAD_REQUEST;
	    List<Object> unplannedvacationmonths = null;
	    try {
	    	unplannedvacationmonths = service.getUnPlannedVacationMonths(employeeId);
	         status = HttpStatus.CREATED;
	     } catch (Exception e) {
	         log.error("Error while listing planned vacations ", e);
	         status = HttpStatus.INTERNAL_SERVER_ERROR;
	     }
	     return new ResponseEntity<>(unplannedvacationmonths, status);
	}
}
