
package acme.features.officer.duty;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.features.administrator.spam.AdministratorSpamWordListService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.spam.SpamRead;

@Service
public class OfficerDutyUpdateService implements AbstractUpdateService<Officer, Duty> {

	@Autowired
	protected OfficerDutyRepository				repository;

	// Other Services----------------

	@Autowired
	protected AdministratorSpamWordListService	spamService;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		boolean result;
		final int dutyId;
		final Duty duty;
		final Officer officer;
		final Principal principal;

		dutyId = request.getModel().getInteger("id");
		duty = this.repository.findOneDutyById(dutyId);
		officer = duty.getOfficer();
		principal = request.getPrincipal();

		result = officer.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

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
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty result;
		int dutyId;

		dutyId = request.getModel().getInteger("id");
		result = this.repository.findOneDutyById(dutyId);

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

		if (!errors.hasErrors("endMoment")) {
			final boolean res = entity.getInitialMoment().after(entity.getEndMoment());
			errors.state(request, !res, "endMoment", "officer.duty.form.error.endMoment");

		}
		if (!errors.hasErrors("initialMoment")) {
			final Date now = new Date();
			final boolean res = entity.getInitialMoment().after(now);
			errors.state(request, res, "initialMoment", "officer.duty.form.error.initialMoment");
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
		if (!errors.hasErrors("endMoment")&& entity.getEndMoment()!=null) {
			boolean res;
			final Date now = new Date();
			res=entity.getEndMoment().before(now);
			errors.state(request, !res, "endMoment", "officer.duty.form.error.ended");
		}

	}

	@Override
	public void update(final Request<Duty> request, final Duty entity) {
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
