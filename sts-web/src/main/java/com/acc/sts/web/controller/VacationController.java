package com.acc.sts.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acc.sts.model.Employee;
import com.acc.sts.model.PublicHoliday;
import com.acc.sts.model.Vacation;
import com.acc.sts.web.service.VacationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/vacation")
@CrossOrigin(value = "http://localhost:4200")
public class VacationController {

@Autowired
private VacationService service;

@GetMapping("/listPlanned")
public @ResponseBody ResponseEntity<List<Vacation>> listPlannedVacation() {
    log.info("Retrieving list of  Planned Vacation");
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<Vacation> list = null;
    try {
        list = service.listPlannedVacation();
        status = HttpStatus.OK;
    } catch (Exception e) {
        log.info("Error while Fetching list of Planned Vacation", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(list, status);
}

@GetMapping("/listUnPlanned")
public @ResponseBody ResponseEntity<List<Vacation>> listUnPlannedVacation() {
    log.info("Retrieving list of UnPlanned Vacation");
    HttpStatus status = HttpStatus.NOT_FOUND;
    List<Vacation> list = null;
    try {
        list = service.listUnPlannedVacation();
        status = HttpStatus.OK;
    } catch (Exception e) {
        log.info("Error while Fetching list of  UnPlanned Vacation", e);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return new ResponseEntity<>(list, status);
}



@PostMapping("/save")
public @ResponseBody ResponseEntity<Vacation> saveVacation(@RequestBody Vacation vacation) {
    log.info("Save Vacation details");
    HttpStatus status = HttpStatus.BAD_REQUEST;
    Vacation vac = null;
    try {
        vac= service.saveVacation(vacation);
        status = HttpStatus.CREATED;
    } catch (Exception e) {
        log.error("Error while saving Vaction details ", e);
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
public @ResponseBody ResponseEntity<List<PublicHoliday>> listHolidays() {
	log.info("Listing disabled holidays");
	HttpStatus status= HttpStatus.NOT_FOUND;
	List<PublicHoliday> hol = null;
	try{
	hol = service.listHolidays();
	 status = HttpStatus.OK;
	}catch (Exception e) {
	log.info("Error while fetching trhe details" ,e);
	status=HttpStatus.INTERNAL_SERVER_ERROR;
}
   return new ResponseEntity<>(hol, status);
}

@PostMapping("/getplannedvacationmonths/{employeeId}")
public @ResponseBody ResponseEntity<List<Object>> getPlannedVacationMonths(@PathVariable("employeeId") String employeeId) {
	log.info("list planned vactions");
    HttpStatus status = HttpStatus.BAD_REQUEST;
    List<Object> plannedvacationmonths = null;
    try {
    	plannedvacationmonths = service.getPlannedVacationMonths(employeeId);
         status = HttpStatus.CREATED;
     } catch (Exception e) {
         log.error("Error while listing planned vactions ", e);
         status = HttpStatus.INTERNAL_SERVER_ERROR;
     }
     return new ResponseEntity<>(plannedvacationmonths, status);
}

@PostMapping("/getunplannedvacationmonths/{employeeId}")
public @ResponseBody ResponseEntity<List<Object>> getUnPlannedVacationMonths(@PathVariable("employeeId") String employeeId) {
	log.info("list planned vactions");
    HttpStatus status = HttpStatus.BAD_REQUEST;
    List<Object> unplannedvacationmonths = null;
    try {
    	unplannedvacationmonths = service.getUnPlannedVacationMonths(employeeId);
         status = HttpStatus.CREATED;
     } catch (Exception e) {
         log.error("Error while listing planned vactions ", e);
         status = HttpStatus.INTERNAL_SERVER_ERROR;
     }
     return new ResponseEntity<>(unplannedvacationmonths, status);
}
}
