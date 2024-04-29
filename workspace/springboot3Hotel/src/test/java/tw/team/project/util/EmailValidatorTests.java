package tw.team.project.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailValidatorTests {
	
	@Test
	public void testEmail() {
		String email = "exefv23ample@eevev.com";
		if (EmailValidator.isValidEmail(email)) {
		    System.out.println(email + " is a valid email address.");
		} else {
		    System.out.println(email + " is not a valid email address.");
		}
	}

}
