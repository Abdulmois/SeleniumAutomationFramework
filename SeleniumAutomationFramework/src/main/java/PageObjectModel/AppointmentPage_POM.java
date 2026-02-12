package PageObjectModel;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePackage.CommonToAllPages;
import DriverPackage.DriverManagerTL;

public class AppointmentPage_POM extends CommonToAllPages{


	By makeAppointmentButton = By.id("btn-make-appointment");
	By facilityDropdown  = By.xpath("//select[@id='combo_facility']");
	By hospitalReAdmissionCheckBox = By.xpath("//input[@id='chk_hospotal_readmission']");
	By healthcareProgramRadiobutton = By.xpath("//input[@id='radio_program_medicare']");
	By visitDateField = By.xpath("//input[@id='txt_visit_date']");
	By commonField = By.xpath("//textarea[@id='txt_comment']");
	By bookAppointmentButton = By.id("btn-book-appointment");
	By appointmentConfirmation = By.xpath("//h2[.='Appointment Confirmation']");



	protected WebElement getElement(By locator) {
		return DriverManagerTL.getDriver().findElement(locator);
	}


	public boolean isBookAppointmentButtonDisplayed() {
		WebElement button = wait
				.until(ExpectedConditions.visibilityOfElementLocated(bookAppointmentButton));
		return button.isDisplayed();
	}	


	public void bookAppointment(String facility, boolean readmission, String program, String day, String monthYear, String comment) {
		selectDropdown(facilityDropdown, facility);

		if(readmission) clickElement(hospitalReAdmissionCheckBox);

		clickElement(By.xpath("//input[@name='programs' and @value='" + program + "']"));

		selectVisitDate(day, monthYear);

		enterInput(commonField, comment);

		clickElement(bookAppointmentButton);
	}


	public void selectVisitDate(String day, String monthYear) {

	    WebDriver driver = DriverManagerTL.getDriver();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    // Click to open calendar
	    clickElement(visitDateField);

	    // Wait until calendar is visible (entire widget)
	    wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.cssSelector(".datepicker-dropdown")));

	    int maxAttempts = 12;  // safety to prevent infinite loop

	    while (maxAttempts > 0) {

	        WebElement monthYearElement = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        By.xpath("//th[@class='datepicker-switch']")
	                )
	        );

	        String currentMonthYear = monthYearElement.getText().trim();

	        if (currentMonthYear.equalsIgnoreCase(monthYear)) {
	            break;
	        }

	        WebElement nextButton = wait.until(
	                ExpectedConditions.elementToBeClickable(
	                        By.xpath("//th[@class='next']")
	                )
	        );

	        nextButton.click();
	        maxAttempts--;
	    }

	    if (maxAttempts == 0) {
	        throw new RuntimeException("Desired month not found in datepicker.");
	    }

	    wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//td[not(contains(@class,'old')) and not(contains(@class,'new')) and text()='"
	                    + day + "']")
	    )).click();
	}




	public boolean isConfirmationDisplayed() {

	    WebDriver driver = DriverManagerTL.getDriver();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        WebElement heading = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        By.xpath("//h2[text()='Appointment Confirmation']")
	                )
	        );
	        return heading.isDisplayed();

	    } catch (Exception e) {
	        return false;
	    }
	}






	public String getValidationMessage(By locator) {
		WebElement element = DriverManagerTL.getDriver().findElement(locator);
		return (String) ((JavascriptExecutor) DriverManagerTL.getDriver())
				.executeScript("return arguments[0].validationMessage;", element);
	}



	public void clickBookAppointmentButtonOnly() {
		WebElement bookbutton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(bookAppointmentButton));
		bookbutton.click();
	}


}

