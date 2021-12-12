package acme.features.officer.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class OfficerDutyListMineService implements AbstractListService<Officer,Duty> {
	
	@Autowired
	protected OfficerDutyRepository repository;

	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "initialMoment","endMoment", "workload", "description", "visibility", "executionPeriod");
	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;
		
		Collection<Duty> result;
		Principal principal;
		
		principal = request.getPrincipal();
		result = this.repository.findManyDutiesByOfficerId(principal.getActiveRoleId());
		
		return result;
	}

}
