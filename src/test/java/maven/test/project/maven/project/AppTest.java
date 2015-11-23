package maven.test.project.maven.project;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
	public void testApp() {
		App appObject = new App();
		Assert.assertEquals(appObject.reverseString("Test!"), "!tseT");
	}
}
