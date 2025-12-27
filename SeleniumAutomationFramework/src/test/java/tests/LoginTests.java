package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.CommonToAllTest;
import PageObjectModel.AppointmentPage_POM;
import PageObjectModel.LoginPage_POM;
import UtilsPackage.PropertyReader;

public class LoginTests extends CommonToAllTest{
	
    @Test(priority = 1)
    public void testLoginNegative() throws Exception {

        LoginPage_POM home = new LoginPage_POM();
        home.openurl(PropertyReader.readKey("url"));

        String error_message = home.loginWithInvalidCredentials();
        Assert.assertTrue(error_message.contains(
            PropertyReader.readKey("error_message")
        ));
    }
	
		
	@Test(priority = 2)	
	public void testLoginPositive() throws Exception {

	    LoginPage_POM loginPage = new LoginPage_POM();
	    loginPage.openurl(PropertyReader.readKey("url"));

	    AppointmentPage_POM appointmentPage = loginPage.loginWithValidCredentials();

	    Assert.assertTrue(
	            appointmentPage.isBookAppointmentButtonDisplayed(),
	            "Login failed or Make Appointment button not visible"
	    );
	}

	

}

