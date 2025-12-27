package PageObjectModel;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
		clickElement(visitDateField);  

		WebElement monthYearElement = DriverManagerTL.getDriver().findElement(By.className("datepicker-switch"));
		WebElement nextButton = DriverManagerTL.getDriver().findElement(By.className("next"));

		while (!monthYearElement.getText().equals(monthYear)) {
			nextButton.click();
			try { Thread.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
		}

		clickElement(By.xpath("//td[not(contains(@class,'old')) and not(contains(@class,'new'))][text()='" + day + "']"));
	}


	public boolean isConfirmationDisplayed() {
		return DriverManagerTL.getDriver()
				.findElements(appointmentConfirmation)
				.size() > 0;
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

