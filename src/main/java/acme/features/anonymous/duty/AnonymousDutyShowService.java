package acme.features.anonymous.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousDutyShowService implements AbstractShowService<Anonymous, Duty> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		return true;
	}

	// AbstractShowService<Anonymous, Duty> interface --------------------------

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "initialMoment","endMoment", "executionPeriod", "workload", "description","visibility");
	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;
//		Double executionPeriod;
//		final Date date = new Date();
		Duty result;
		int id;
		

		id = request.getModel().getInteger("id");
//		executionPeriod = this.repository.getExecutionPeriod(id);
		result = this.repository.findOneDutyById(id);
//		if(result.initialMoment.getTime()-date.getTime()>0) {
//			result.setWorkload(0.);
//		}else if(result.endMoment.getTime()-date.getTime()<0) {
//			result.setWorkload((double)(result.endMoment.getTime() - result.initialMoment.getTime()) / 3600000);
//		}else {
//			result.setWorkload((double) (date.getTime() - result.initialMoment.getTime()) / 3600000);
//		}
//		result.setExecutionPeriod(executionPeriod);
		
		

		return result;
	}
//	public Double getWorkload(final Duty entity){
//		return (double) (entity.endMoment.getTime() - entity.initialMoment.getTime()) / 3600000;
//	}
}
