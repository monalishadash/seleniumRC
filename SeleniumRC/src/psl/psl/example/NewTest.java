package psl.psl.example;

import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestBase;

public class NewTest extends SeleneseTestBase {
	SeleniumServer server;

	@BeforeTest
	public void setUp() throws Exception {
		server = new SeleniumServer();
		server.boot();
		server.start();
		selenium = new DefaultSelenium("localhost", 4444, "*chrome",
				"http://wordpress.com/");
		selenium.start();
	}

	@Test
	public void f() {
		try {
			selenium.open("/");
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Log In");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=user_login", "monadash");
		selenium.type("id=user_pass", "Secret123$");
		selenium.click("id=rememberme");
		selenium.click("id=wp-submit");
		selenium.waitForPageToLoad("30000");
		try{
		String actualTitle = selenium.getTitle();
		String expectdTitle = "Wordpress";
		assertEquals(expectdTitle, actualTitle);
		}catch(Exception e)
		{
			System.out.println("Error: "+ e);
			String actualTitle = selenium.getTitle();
			String expectdTitle = "Blogs I Follow — WordPress.com";
			assertEquals(expectdTitle, actualTitle);
		}
		selenium.click("link=Sign Out");
		selenium.waitForPageToLoad("30000");
	}

	@AfterTest
	public void tearDown() {
		server.stop();
	}
}
