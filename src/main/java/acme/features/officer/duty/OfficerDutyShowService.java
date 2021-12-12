package acme.features.officer.duty;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.duties.DutyVisibility;
import acme.entities.roles.Officer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class OfficerDutyShowService implements AbstractShowService<Officer, Duty> {

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

			result = officer.getUserAccount().getId() == principal.getAccountId() || duty.getVisibility()==DutyVisibility.PUBLIC;
			return result;
		}

		// AbstractShowService<Officer, Duty> interface --------------------------

		@Override
		public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			final boolean isPrincipal = entity.getOfficer().getId() == request.getPrincipal().getActiveRoleId();
			final Date now = new Date();
			final boolean isFinished = entity.getEndMoment().before(now);

			model.setAttribute("checkP", isPrincipal);
			model.setAttribute("checkF", isFinished);

			
			request.unbind(entity, model, "title", "initialMoment","endMoment", "workload", "description", "visibility", "executionPeriod");
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
