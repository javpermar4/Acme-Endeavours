
package acme.testing.authenticated.becomeOfficer;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.AcmeEndeavoursTest;

public class AuthenticatedBecomeOfficerTest extends AcmeEndeavoursTest {

	
	/*
	 * Nos registramos con los datos correctos, posteriormente iniciamos sesión con ese nuevo usuario.
	 * Navegaremos para convertirnos en officer donde nos aparecerá una vista con el botón Register el cual al pulsarlo
	 * nos convertirá en officer. Finalmente, cerramos sesión.
	  */
	
	@Test
	@Order(50)
	public void becomeManager() {
		
		super.signUp("johnny", "johnny", "johnny", "johnny", "johnny@hotmail.com");
		
		super.signIn("johnny", "johnny");
		
		super.clickOnMenu("Account", "Become a officer");
		
		super.clickOnSubmitButton("Register");
		
		super.signOut();

	}

}
