package acme.features.officer.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Officer;
import acme.entities.tasks.Task;
import acme.entities.tasks.TaskVisibility;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class OfficerTaskShowService implements AbstractShowService<Officer, Task> {

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

			result = officer.getUserAccount().getId() == principal.getAccountId() || task.getVisibility()==TaskVisibility.PUBLIC;
			return result;
		}

		// AbstractShowService<Officer, Task> interface --------------------------

		@Override
		public void unbind(final Request<Task> request, final Task entity, final Model model) {
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
		public Task findOne(final Request<Task> request) {
			assert request != null;
//			final Double executionPeriod;
			Task result;
			int id;
			

			id = request.getModel().getInteger("id");
//			executionPeriod = this.repository.getExecutionPeriod(id);
			result = this.repository.findOneTaskById(id);
//			result.setWorkload((double) (result.endMoment.getTime() - result.initialMoment.getTime()) / 3600000);
//			result.setExecutionPeriod(executionPeriod);
			
			

			return result;
		}
}
