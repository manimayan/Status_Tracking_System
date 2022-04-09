package com.acc.sts.core.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.acc.sts.core.repository.VacationRepository;
import com.acc.sts.model.PublicHoliday;
import com.acc.sts.model.Vacation;

@Repository
public class VacationRepositoryImpl implements  VacationRepository{

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<Vacation> listPlannedVacation() {
        TypedQuery<Vacation> query = entityManager.createQuery("from Vacation where vacationType = 'Vacation' or vacationType = 'Optional' or vacationType = 'Comp-off'", Vacation.class);
        return query.getResultList();	
    }
    
	@Override
	public List<Vacation> listUnPlannedVacation() {
        TypedQuery<Vacation> query = entityManager.createQuery("from Vacation where vacationType = 'Sick'", Vacation.class);
        return query.getResultList();
	}
    
	@Transactional
	@Override
	public Vacation saveVacation(Vacation vacation) {
		System.out.println("Vacation;"+vacation.getVacationType()+vacation.getEmployee().getEmployeeId()+vacation.getVacationDateTo());
		   entityManager.persist(vacation);
		   
	        return vacation;
	}

	@Transactional
	@Override
	public PublicHoliday savePublicholidays(List<PublicHoliday> holidays) {
		for(PublicHoliday pl:holidays){
		   entityManager.persist(pl);
		} 
	        return holidays.get(0);
	}

	@Override
	public List<PublicHoliday> listHolidays() {
	 TypedQuery<PublicHoliday> query = entityManager.createQuery(" from PublicHoliday ", PublicHoliday.class);
		return query.getResultList();
	}

	@Override
	public List<Object> getPlannedVacationMonths(String employeeId) {
	Query query = entityManager.createQuery("select "+
			    "SUM(CASE WHEN (vacationType='Vacation' or vacationType='Optional' or vacationType='Comp-off') and MONTH(vacationDateFrom) = 1 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN (vacationType='Vacation' or vacationType='Optional' or vacationType='Comp-off') and MONTH(vacationDateFrom) = 2 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN (vacationType='Vacation' or vacationType='Optional' or vacationType='Comp-off') and MONTH(vacationDateFrom) = 3 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN (vacationType='Vacation' or vacationType='Optional' or vacationType='Comp-off') and MONTH(vacationDateFrom) = 4 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN (vacationType='Vacation' or vacationType='Optional' or vacationType='Comp-off') and MONTH(vacationDateFrom) = 5 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN (vacationType='Vacation' or vacationType='Optional' or vacationType='Comp-off') and MONTH(vacationDateFrom) = 6 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN (vacationType='Vacation' or vacationType='Optional' or vacationType='Comp-off') and MONTH(vacationDateFrom) = 7 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN (vacationType='Vacation' or vacationType='Optional' or vacationType='Comp-off') and MONTH(vacationDateFrom) = 8 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN (vacationType='Vacation' or vacationType='Optional' or vacationType='Comp-off') and MONTH(vacationDateFrom) = 9 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN (vacationType='Vacation' or vacationType='Optional' or vacationType='Comp-off') and MONTH(vacationDateFrom) = 10 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN (vacationType='Vacation' or vacationType='Optional' or vacationType='Comp-off') and MONTH(vacationDateFrom) = 11 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+ 
				"SUM(CASE WHEN (vacationType='Vacation' or vacationType='Optional' or vacationType='Comp-off') and MONTH(vacationDateFrom) = 12 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END)"+
				"from Vacation t where employee.employeeId = :employeeId group by employee.employeeId");
				query.setParameter("employeeId",employeeId);
		
                return query.getResultList();
	}

	@Override
	public List<Object> getUnPlannedVacationMonths(String employeeId) {
		Query query = entityManager.createQuery("select "+
			    "SUM(CASE WHEN vacationType='Sick' and MONTH(vacationDateFrom) = 1 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN vacationType='Sick' and MONTH(vacationDateFrom) = 2 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN vacationType='Sick' and MONTH(vacationDateFrom) = 3 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN vacationType='Sick' and MONTH(vacationDateFrom) = 4 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN vacationType='Sick' and MONTH(vacationDateFrom) = 5 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN vacationType='Sick' and MONTH(vacationDateFrom) = 6 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN vacationType='Sick' and MONTH(vacationDateFrom) = 7 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN vacationType='Sick' and MONTH(vacationDateFrom) = 8 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN vacationType='Sick' and MONTH(vacationDateFrom) = 9 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN vacationType='Sick' and MONTH(vacationDateFrom) = 10 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+
				"SUM(CASE WHEN vacationType='Sick' and MONTH(vacationDateFrom) = 11 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END),"+ 
				"SUM(CASE WHEN vacationType='Sick' and MONTH(vacationDateFrom) = 12 and YEAR(vacationDateFrom) = '2018' THEN 1 ELSE 0 END)"+
				"from Vacation t where employee.employeeId = :employeeId group by employee.employeeId");
				query.setParameter("employeeId",employeeId);
		
                return query.getResultList();
	}




}
