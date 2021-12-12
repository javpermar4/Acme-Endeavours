package acme.features.officer.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Officer;
import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class OfficerTaskDeleteService implements AbstractDeleteService<Officer, Task> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected OfficerTaskRepository repository;
	

@Override
public boolean authorise(final Request<Task> request) {
	assert request != null;
	boolean result;
	final int taskId;
	final Task task;
	final Officer officer;
	final Principal principal;
	
	taskId = request.getModel().getInteger("id");
	task = this.repository.findOneTaskById(taskId);
	officer = task.getOfficer();
	principal = request.getPrincipal();
	
	result = officer.getUserAccount().getId() == principal.getAccountId();
	return result;
}

@Override
public void bind(final Request<Task> request, final Task entity, final Errors errors) {
	assert request != null;
	assert entity != null;
	assert errors != null;

	request.bind(entity, errors);
}

@Override
public void unbind(final Request<Task> request, final Task entity, final Model model) {
	assert request != null;
	assert entity != null;
	assert model != null;
	
	request.unbind(entity, model, "title", "initialMoment","endMoment", "workload", "description", "visibility", "executionPeriod");
}

@Override
public Task findOne(final Request<Task> request) {
	assert request != null;
	
	Task result;
	int taskId;
	
	taskId = request.getModel().getInteger("id");
	result = this.repository.findOneTaskById(taskId);
	
	return result;
}

@Override
public void validate(final Request<Task> request, final Task entity, final Errors errors) {
	assert request != null;
	assert entity != null;
	assert errors != null;
	if (!errors.hasErrors("endMoment")&& entity.getEndMoment()!=null) {
		boolean res;
		final Date now = new Date();
		res=entity.getEndMoment().before(now);
		errors.state(request, !res, "endMoment", "officer.task.form.error.ended");
	}
	
}

@Override
public void delete(final Request<Task> request, final Task entity) {
	assert request != null;
	assert entity != null;
	
	this.repository.delete(entity);
}

}