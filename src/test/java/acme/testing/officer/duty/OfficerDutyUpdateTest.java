package acme.testing.officer.duty;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class OfficerDutyUpdateTest extends AcmeEndeavoursTest{
	
	/* CASO POSITIVO.
	 * Se autentifica como officer, usa el menu desplegable de officer y accede a la lista de tareas propias, accede a la tarea con posición indicada en el archivo .csv
	 * e introduce datos validos para actualizar la tarea.
	 * CASO NEGATIVO.
	 * Se autentifica como officer, usa el menu desplegable de officer y accede a la lista de tareas propias, accede a la tarea con posición indicada en el archivo .csv
	 * e introduce datos no validos para comprobar que se cumplen las restricciones.
	 * 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duty/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updatePositive(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String description, final String visibility, final String link) {		
		super.signIn("officer", "officer");
		
		super.clickOnMenu("Officer", "My duties");		
				
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("initialMoment", iniMoment);
		super.fillInputBoxIn("endMoment", endMoment);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("visibility", visibility);
		super.clickOnSubmitButton("Update");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, iniMoment);
		super.checkColumnHasValue(recordIndex, 2, endMoment);
		super.checkColumnHasValue(recordIndex, 3, exePeriod);
		super.checkColumnHasValue(recordIndex, 4, workload);
		super.checkColumnHasValue(recordIndex, 5, description);
		super.checkColumnHasValue(recordIndex, 6, visibility);
		
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duty/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updateNegative(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String description, final String visibility, final String link) {		
		super.signIn("officer", "officer");
		
		super.clickOnMenu("Officer", "My duties");		
		
		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("initialMoment", iniMoment);
		super.fillInputBoxIn("endMoment", endMoment);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("visibility", visibility);
		super.clickOnSubmitButton("Update");
		super.checkErrorsExist();

		
		super.signOut();
	}

}
