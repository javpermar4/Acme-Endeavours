package acme.features.authenticated.officer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Officer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedOfficerCreateService implements AbstractCreateService<Authenticated, Officer>{
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AuthenticatedOfficerRepository repository;

		// AbstractCreateService<Authenticated, Provider> interface ---------------


		@Override
		public boolean authorise(final Request<Officer> request) {
			assert request != null;

			return true;
		}

		@Override
		public void bind(final Request<Officer> request, final Officer entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors);
		}

		@Override
		public void unbind(final Request<Officer> request, final Officer entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model);
		}

		@Override
		public Officer instantiate(final Request<Officer> request) {
			assert request != null;

			Officer result;
			Principal principal;
			int userAccountId;
			UserAccount userAccount;

			principal = request.getPrincipal();
			userAccountId = principal.getAccountId();
			userAccount = this.repository.findOneUserAccountById(userAccountId);

			result = new Officer();
			result.setUserAccount(userAccount);

			return result;
		}

		@Override
		public void validate(final Request<Officer> request, final Officer entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
		}

		@Override
		public void create(final Request<Officer> request, final Officer entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}

		@Override
		public void onSuccess(final Request<Officer> request, final Response<Officer> response) {
			assert request != null;
			assert response != null;

			if (request.isMethod(HttpMethod.POST)) {
				PrincipalHelper.handleUpdate();
			}
		}
}
