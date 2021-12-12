package acme.forms;



import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes TASKS-------------------------------------------------------------

	Integer						totalPublicDuties;
	Integer						totalPrivateDuties;
	Integer						totalNumberOfPublicPrivateDuties;
	Integer						totalNonFinishedDuties;
	Integer						totalFinishedDuties;
	Double						totalNumberOfFinishedNonFinishedDuties;
	Double						averageNumberOfDutyExecutionPeriods;
	Double						stdDevDutyExecutionPeriods;
	Integer						minExecutionPeriod;
	Integer						maxExecutionPeriod;
	Double 						maxWorkload;
	Double 						minWorkload;
	Double 						averageNumberOfDutyWorkloads;
	Double 						stdDevDutyWorkloads;
	

// Derived attributes -----------------------------------------------------

// Relationships ----------------------------------------------------------

}