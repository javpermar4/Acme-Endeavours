package acme.features.anonymous.duty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.duties.Duty;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/duty/")
public class AnonymousDutyController extends AbstractController<Anonymous, Duty>{
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnonymousDutyListService		listService;
		
		@Autowired
		protected AnonymousDutyShowService		showService;


		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {

			super.addBasicCommand(BasicCommand.LIST, this.listService);
			super.addBasicCommand(BasicCommand.SHOW, this.showService);


		}
}
