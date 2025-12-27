package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.CommonToAllTest;
import PageObjectModel.AppointmentPage_POM;
import PageObjectModel.LoginPage_POM;
import UtilsPackage.PropertyReader;

public class AppointmentTests extends CommonToAllTest {

    @Test
    public void testFullAppointmentFlowPositive() throws Exception {

        LoginPage_POM login = new LoginPage_POM();
        login.openurl(PropertyReader.readKey("url"));

        AppointmentPage_POM appointment = login.loginWithValidCredentials();

        appointment.bookAppointment(
                PropertyReader.readKey("facility"),
                Boolean.parseBoolean(PropertyReader.readKey("readmission")),
                PropertyReader.readKey("program"),
                PropertyReader.readKey("visitDateDay"),
                PropertyReader.readKey("visitDateMonthYear"),
                PropertyReader.readKey("comment")
        );

        Assert.assertTrue(
                appointment.isConfirmationDisplayed(),
                "Appointment confirmation not displayed!"
        );
    }

    @Test
    public void testBookAppointmentNegative() throws Exception {

        LoginPage_POM login = new LoginPage_POM();
        login.openurl(PropertyReader.readKey("url"));
        AppointmentPage_POM appointment = login.loginWithValidCredentials();

        appointment.clickBookAppointmentButtonOnly();

        Assert.assertFalse(
                appointment.isConfirmationDisplayed(),
                "Appointment booked even with missing data!"
        );

        String message = appointment.getValidationMessage(By.id("txt_visit_date"));
        Assert.assertTrue(
                message.contains("Please fill out this field"),
                "Validation message not displayed"
        );
    }
}
