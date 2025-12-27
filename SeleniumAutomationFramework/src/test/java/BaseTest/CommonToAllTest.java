 package BaseTest;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import DriverPackage.DriverManagerTL;

public class CommonToAllTest {

	
	@BeforeMethod(alwaysRun = true)
    protected void setup() {
        DriverManagerTL.init();
    }

    @AfterMethod(alwaysRun = true)
    protected void teardown() {
        DriverManagerTL.down();
    }
}
