/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	//--------------------------------------TASK---------------------------------------------
	@Query("select count(d) from Duty d where d.visibility = 0")
	Integer totalPublicDuties();
	
	@Query("select count(d) from Duty d where d.visibility = 1")
	Integer totalPrivateDuties();
	
	@Query("select count(d) from Duty d")
	Integer totalNumberOfPublicPrivateDuties();
	
	@Query("select count(d) from Duty d where d.endMoment < current_timestamp()")
	Integer totalNonFinishedDuties();
	
	@Query("select count(d) from Duty d where d.endMoment > current_timestamp()")
	Integer totalFinishedDuties();
	
	@Query("select count(d) from Duty d")
	Double totalNumberOfFinishedNonFinishedDuties();
	
	@Query("select avg(DATEDIFF(d.endMoment, d.initialMoment)) from Duty d")
	Double averageNumberOfDutyExecutionPeriods();
	
	@Query("select stddev(DATEDIFF(d.endMoment, d.initialMoment)) from Duty d")
	Double stdDevDutyExecutionPeriods();
	
	@Query("select min(DATEDIFF(d.endMoment, d.initialMoment)) from Duty d")
	Integer minExecutionPeriod();

	@Query("select max(DATEDIFF(d.endMoment, d.initialMoment)) from Duty d")
	Integer maxExecutionPeriod();
	
	@Query("select max(d.workload) from Duty d")
	Double maxWorkload();
	
	@Query("select min(d.workload) from Duty d")
	Double minWorkload();
	
	@Query("select avg(d.workload) from Duty d")
	Double averageNumberOfDutyWorkloads();
	
	@Query("select stddev(d.workload) from Duty d")
	Double stdDevDutyWorkloads();

}
