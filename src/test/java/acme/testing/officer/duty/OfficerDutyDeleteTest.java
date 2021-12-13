
package acme.testing.officer.duty;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmeEndeavoursTest;

public class OfficerDutyDeleteTest extends AcmeEndeavoursTest {

	// Lifecycle management -------------------------------

	// Test cases -----------------------------------------

	/*
	 * CASO POSITIVO
	 * Se autentifica como officer, accede al menu desplegable de officer y navega hasta las tareas propias, una vez en el listado de tareas propias
	 * accede a la tarea indicada en el .csv y pulsa el boton "Delete", una vez borrada la tarea, vuelve a la lista de tareas propias y accede a la misma
	 * posicion introducida anteriormente y comprueba que los datos que se muestran son los de la tarea siguiente a la borrada.
	 * CASO NEGATIVO
	 * Se autentifica como otro officer e introduce la url de una tarea ajena con la query de borrarla, al intentar esta acción devolverá un "Access Not Authorized"
	 * por lo que devuelve un error frente a una accion ilegal.
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duty/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String description, final String visibility, final String link) {
		super.signIn("officerEx", "officerEx");

		super.clickOnMenu("Officer", "My duties");

		super.clickOnListingRecord(recordIndex);

		super.clickOnSubmitButton("Delete");

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

	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duty/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void deleteNegative(final int recordIndex) {
		super.signIn("officer", "officer");

		final String s = this.getBaseUrl();
		this.driver.get(s + "/officer/duty/delete?id=42");
		super.checkErrorsExist();
		super.signOut();

	}
	/*
	 * Nos logueamos como un officer, visitamos la pagina de eliminación de una tarea concreta del officer logueado,
	 * nos deslogueamos y volvemos a loguear con otro officer diferente y comprobamos que no existe el boton de eliminar.
	 * Por último nos deslogueamos
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duty/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void deleteNegative2(final int recordIndex) {
		super.signIn("officerEx", "officerEx");

		final String s = this.getBaseUrl();
		this.driver.get(s + "/officer/duty/delete?id=37");
		super.signOut();
		super.signIn("officer", "officer");
		super.checkNotExists(By.name("Delete"));
		super.signOut();

	}
}
