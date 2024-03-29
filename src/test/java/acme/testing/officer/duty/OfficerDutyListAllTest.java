package acme.testing.officer.duty;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class OfficerDutyListAllTest extends AcmeEndeavoursTest{

	/*
	 * Se autentifica como officer, usa el menu desplegable de officer y accede a la lista de tareas, comprueba que las columnas de 
	 * la lista corresponden con las introducidas y va accediendo a las tareas una a una y comprobando que los datos del archivo .csv
	 * corresponden con los mostrados.
	 * 
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duty/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String description, final String visibility, final String link) {		
		super.signIn("officer", "officer");
		
		super.clickOnMenu("Officer", "Duties List");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, iniMoment);
		super.checkColumnHasValue(recordIndex, 2, endMoment);
		super.checkColumnHasValue(recordIndex, 3, exePeriod);
		super.checkColumnHasValue(recordIndex, 4, workload);
		super.checkColumnHasValue(recordIndex, 5, description);
		super.checkColumnHasValue(recordIndex, 6, visibility);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("initialMoment", iniMoment);
		super.checkInputBoxHasValue("endMoment", endMoment);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("executionPeriod", exePeriod);	
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("visibility", visibility);
			
		
		super.signOut();
	}
}
