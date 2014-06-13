package test;

import static org.junit.Assert.*;
import main.Controller;
import org.junit.Test;


public class IntegrationTest {
	
	
	@Test(expected=NullPointerException.class)
	public void testNullControllerConstructor() {
		Controller con = new Controller(null, null);
	}
	
}
