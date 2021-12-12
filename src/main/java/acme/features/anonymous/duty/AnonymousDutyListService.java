package acme.features.anonymous.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousDutyListService implements AbstractListService<Anonymous, Duty>{

	
	// Internal state ---------------------------------------------------------

	@Autowired

	protected AnonymousDutyRepository repository;
	
	public Double getWorkload(final Request<Duty> request,  final Duty entity, final Model model){
		return (double) (entity.getEndMoment().getTime() - entity.getInitialMoment().getTime()) / 3600000;
	}
		
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
		request.unbind(entity, model, "title", "initialMoment","endMoment", "description");
	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;
		Collection<Duty> result;
		result = this.repository.findMany();
		return result;
	}

}
