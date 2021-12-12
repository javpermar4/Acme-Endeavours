package acme.features.officer.duty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.duties.Duty;
import acme.entities.roles.Officer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/officer/duty/")
public class OfficerDutyController extends AbstractController<Officer, Duty>{
	// Internal state ---------------------------------------------------------

			@Autowired
			protected OfficerDutyListService		listService;
			
			@Autowired
			protected OfficerDutyShowService		showService;
			
			@Autowired
			protected OfficerDutyCreateService		createService;
			
			@Autowired
			protected OfficerDutyDeleteService		deleteService;
			
			@Autowired
			protected OfficerDutyUpdateService		updateService;
			
			@Autowired 
			protected OfficerDutyListMineService 	listMineService;
			
			
			// Constructors -----------------------------------------------------------


			@PostConstruct
			protected void initialise() {
				super.addBasicCommand(BasicCommand.LIST, this.listService);
				super.addBasicCommand(BasicCommand.SHOW, this.showService);
				super.addBasicCommand(BasicCommand.CREATE, this.createService);
				super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
				super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
				
				
				super.addCustomCommand(CustomCommand.LIST_MINE,BasicCommand.LIST,this.listMineService);

			}
}
