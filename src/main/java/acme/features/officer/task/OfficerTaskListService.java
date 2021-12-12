package acme.features.officer.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Officer;
import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class OfficerTaskListService 
	implements AbstractListService<Officer, Task>{
	// Internal state ---------------------------------------------------------

		@Autowired

		protected OfficerTaskRepository repository;
		
	// AbstractListService<Officer, Task> interface ---------------------------
			
		@Override
		public boolean authorise(final Request<Task> request) {
			assert request != null;
			return true;
		}

		@Override
		public void unbind(final Request<Task> request, final Task entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			request.unbind(entity, model, "title", "initialMoment","endMoment", "workload", "description", "executionPeriod", "visibility");
		}


		@Override
		public Collection<Task> findMany(final Request<Task> request) {
			assert request != null;
			
			Collection<Task> result;
			
			result = this.repository.findMany();
			return result;
		}

}