package acme.features.officer.duty;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class OfficerDutyDeleteService implements AbstractDeleteService<Officer, Duty> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected OfficerDutyRepository repository;
	

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
	
	request.unbind(entity, model, "title", "initialMoment","endMoment", "workload", "description", "visibility", "executionPeriod");
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
	if (!errors.hasErrors("endMoment")&& entity.getEndMoment()!=null) {
		boolean res;
		final Date now = new Date();
		res=entity.getEndMoment().before(now);
		errors.state(request, !res, "endMoment", "officer.duty.form.error.ended");
	}
	
}

@Override
public void delete(final Request<Duty> request, final Duty entity) {
	assert request != null;
	assert entity != null;
	
	this.repository.delete(entity);
}

}
