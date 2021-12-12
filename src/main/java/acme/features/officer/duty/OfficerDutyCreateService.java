
package acme.features.officer.duty;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.duties.DutyVisibility;
import acme.entities.roles.Officer;
import acme.features.administrator.spam.AdministratorSpamWordListService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;
import acme.spam.SpamRead;

@Service
public class OfficerDutyCreateService implements AbstractCreateService<Officer, Duty> {
	// Internal state ----------------------------------------------------

	@Autowired
	protected OfficerDutyRepository			repository;

	// Other Services----------------

	
	@Autowired
	protected AdministratorSpamWordListService spamService;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		return true;
	}

	// AbstractCreateService<Officer, Duty> interface --------------------------

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}
	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "initialMoment", "endMoment", "workload", "description", "visibility", "executionPeriod");

	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		assert request != null;

		Duty result;
		final Officer officer;
		Date initialMoment;
		Date endMoment;
		Calendar initialCalendar;
		Calendar endCalendar;

		/*
		 * initialMoment = new Date(System.currentTimeMillis() -1);
		 * endMoment = new Date(System.currentTimeMillis() + 10000000);
		 */
		officer = this.repository.findOneOfficerByUserAccountId(request.getPrincipal().getAccountId());

		initialMoment = new Date();
		initialCalendar = Calendar.getInstance();
		initialCalendar.setTime(initialMoment);
		initialCalendar.add(Calendar.SECOND, 60);
		initialMoment = initialCalendar.getTime();

		endMoment = new Date();
		endCalendar = Calendar.getInstance();
		endCalendar.setTime(endMoment);
		endCalendar.add(Calendar.HOUR, 24);
		endMoment = endCalendar.getTime();

		result = new Duty();
		result.setTitle("Duty-01");
		result.setInitialMoment(initialMoment);
		result.setEndMoment(endMoment);
		result.setDescription("This is a description");
		result.setLink("This is a link");
		result.setWorkload(8.0);
		result.setVisibility(DutyVisibility.PUBLIC);
		result.setOfficer(officer);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		List<String> spamList;
		double umbral;

		spamList = this.spamService.findAllSpamWord();
		umbral = this.spamService.umbral();
		if (!errors.hasErrors("initialMoment")) {

			boolean res;
			res = entity.getInitialMoment() == null;

			errors.state(request, !res, "initialMoment", "officer.duty.form.error.nullinitialMoment");

		}
		if (!errors.hasErrors("endMoment")) {

			boolean res;
			res = entity.getEndMoment() == null;

			errors.state(request, !res, "endMoment", "officer.duty.form.error.nullendMoment");

		}
		if (!errors.hasErrors("workload")) {

			boolean res;
			res = entity.getWorkload() == null;

			errors.state(request, !res, "workload", "officer.duty.form.error.nullworkload");

		}
		if (!errors.hasErrors("initialMoment")) {
			final Date now = new Date();
			final boolean res = entity.getInitialMoment().after(now);
			errors.state(request, res, "initialMoment", "officer.duty.form.error.initialMoment");
		}

		if (!errors.hasErrors("endMoment")) {
			final boolean res = entity.getInitialMoment().after(entity.getEndMoment());
			errors.state(request, !res, "endMoment", "officer.duty.form.error.endMoment");

		}

		if (!errors.hasErrors("workload")) {
			final long endMoment = entity.getEndMoment().getTime();
			final long initialMoment = entity.getInitialMoment().getTime();

			final long diff = endMoment - initialMoment;
			final double horas = (Math.abs(diff) * 1.0) / 3600000;
			boolean res;
			res = horas < entity.getWorkload();

			errors.state(request, !res, "workload", "officer.duty.form.error.workload");

		}
		if (!errors.hasErrors("workload")) {
			final double workload = entity.getWorkload();
			final boolean res;

			res = workload > 0.0;

			errors.state(request, res, "workload", "officer.duty.form.error.negativeworkload");

		}
		
		if (!errors.hasErrors("workload")) {
			final double workload = entity.getWorkload();
			final long workload2 = (long) workload;
			boolean res;

			if(workload > 99.59) {
				res=false;
				errors.state(request, res, "workload", "officer.duty.form.error.biggerThanLimit");
			}
			if(workload - workload2>0.59) {
				res=false;
				errors.state(request, res, "workload", "officer.duty.form.error.fractionError");
			}
			
		}

		if (!errors.hasErrors("description")) {
			boolean res;

			res = SpamRead.isSpam(umbral, entity.getDescription(), spamList);
			errors.state(request, !res, "description", "officer.duty.form.error.description");
		}
		if (!errors.hasErrors("title")) {
			boolean res;

			res = SpamRead.isSpam(umbral, entity.getTitle(), spamList);
			errors.state(request, !res, "title", "officer.duty.form.error.title");
		}

	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		final long endMoment = entity.getEndMoment().getTime();
		final long initialMoment = entity.getInitialMoment().getTime();

		final long diff = endMoment - initialMoment;
		final double horas = (Math.abs(diff) * 1.0) / 3600000;


		entity.setExecutionPeriod(horas);

		this.repository.save(entity);
	}

}
