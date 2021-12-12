package acme.features.authenticated.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedDutyShowService implements AbstractShowService<Authenticated, Duty> {

	// Internal state ---------------------------------------------------------

		@Autowired
		protected AuthenticatedDutyRepository repository;


		@Override
		public boolean authorise(final Request<Duty> request) {
			assert request != null;

			return true;
		}

		// AbstractShowService<Anonymous, Task> interface --------------------------

		@Override
		public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "title", "initialMoment","endMoment", "executionPeriod", "workload", "description", "visibility");
		}

		@Override
		public Duty findOne(final Request<Duty> request) {
			assert request != null;
//			final Double executionPeriod;
			Duty result;
			int id;
			

			id = request.getModel().getInteger("id");
//			executionPeriod = this.repository.getExecutionPeriod(id);
			result = this.repository.findOneDutyById(id);
//			result.setWorkload((double) (result.endMoment.getTime() - result.initialMoment.getTime()) / 3600000);
//			result.setExecutionPeriod(executionPeriod);
			
			

			return result;
		}
}
