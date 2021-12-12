/*
 * AdministratorDashboardShowService.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalPublicDuties","totalPrivateDuties" ,"totalNumberOfPublicPrivateDuties",
			"averageNumberOfDutyExecutionPeriods", "stdDevDutyExecutionPeriods", "totalFinishedDuties","totalNonFinishedDuties",
      "totalNumberOfFinishedNonFinishedDuties","minExecutionPeriod", "maxExecutionPeriod", "maxWorkload", "minWorkload", 
      "averageNumberOfDutyWorkloads", "stdDevDutyWorkloads");

	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
		
		// -----------------------------TASK-------------------------------
		final Integer	totalPublicDuties;
		final Integer	totalPrivateDuties;
		Integer totalNumberOfPublicPrivateDuties;
		Double averageNumberOfDutyExecutionPeriods;
		final Integer totalFinishedDuties;
		final Integer totalNonFinishedDuties;
		Double totalNumberOfFinishedNonFinishedDuties;
		Double getStdDevDutyExecutionPeriods;
		Integer minExecutionPeriod;
		Integer maxExecutionPeriod;
		final Double  maxWorkload;
		final Double  minWorkload;
		final Double 	averageNumberOfDutyWorkloads;
		final Double 	stdDevDutyWorkloads;
		
		totalPublicDuties = this.repository.totalPublicDuties();
		totalPrivateDuties = this.repository.totalPrivateDuties();
		totalNumberOfPublicPrivateDuties = this.repository.totalNumberOfPublicPrivateDuties();
		averageNumberOfDutyExecutionPeriods = this.repository.averageNumberOfDutyExecutionPeriods();
		totalFinishedDuties = this.repository.totalFinishedDuties();
		totalNonFinishedDuties = this.repository.totalNonFinishedDuties();
		totalNumberOfFinishedNonFinishedDuties = this.repository.totalNumberOfFinishedNonFinishedDuties();
		getStdDevDutyExecutionPeriods = this.repository.stdDevDutyExecutionPeriods();
		minExecutionPeriod = this.repository.minExecutionPeriod();
		maxExecutionPeriod = this.repository.maxExecutionPeriod();
		maxWorkload = this.repository.maxWorkload();
		minWorkload = this.repository.minWorkload();
		averageNumberOfDutyWorkloads = this.repository.averageNumberOfDutyWorkloads();
		stdDevDutyWorkloads = this.repository.stdDevDutyWorkloads();

		result = new Dashboard();
		result.setTotalPublicDuties(totalPublicDuties);
		result.setTotalPrivateDuties(totalPrivateDuties);
		result.setTotalNumberOfPublicPrivateDuties(totalNumberOfPublicPrivateDuties);
		result.setAverageNumberOfDutyExecutionPeriods(averageNumberOfDutyExecutionPeriods);
		result.setTotalFinishedDuties(totalFinishedDuties);
		result.setTotalNonFinishedDuties(totalNonFinishedDuties);
		result.setTotalNumberOfFinishedNonFinishedDuties(totalNumberOfFinishedNonFinishedDuties);
		result.setStdDevDutyExecutionPeriods(getStdDevDutyExecutionPeriods);
		result.setMinExecutionPeriod(minExecutionPeriod);
		result.setMaxExecutionPeriod(maxExecutionPeriod);
		result.setMaxWorkload(maxWorkload);
		result.setMaxWorkload(maxWorkload);
		result.setMinWorkload(minWorkload);
		result.setAverageNumberOfDutyWorkloads(averageNumberOfDutyWorkloads);
		result.setStdDevDutyWorkloads(stdDevDutyWorkloads);

		return result;
	}
	

}
