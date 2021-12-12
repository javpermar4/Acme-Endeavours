package acme.features.officer.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Officer;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/officer/task/")
public class OfficerTaskController extends AbstractController<Officer, Task>{
	// Internal state ---------------------------------------------------------

			@Autowired
			protected OfficerTaskListService		listService;
			
			@Autowired
			protected OfficerTaskShowService		showService;
			
			@Autowired
			protected OfficerTaskCreateService		createService;
			
			@Autowired
			protected OfficerTaskDeleteService		deleteService;
			
			@Autowired
			protected OfficerTaskUpdateService		updateService;
			
			@Autowired 
			protected OfficerTaskListMineService 	listMineService;
			
			
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
