package PageObjectModel;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import BasePackage.CommonToAllPages;
import DriverPackage.DriverManagerTL;
import UtilsPackage.PropertyReader;

public class LoginPage_POM extends CommonToAllPages{


	By username=By.id("txt-username");
	By password=By.id("txt-password");
	By loginbutton=By.id("btn-login");
	By error_message=By.xpath("//p[contains(@class, 'lead') and contains(@class, 'text-danger')]");
	By makeAppointmentButton = By.id("btn-make-appointment");


	public LoginPage_POM openurl(String url) {
		DriverManagerTL.getDriver().get(url);
		return this;
	}


	public AppointmentPage_POM loginWithValidCredentials() throws Exception {
		clickElement(makeAppointmentButton);
		enterInput(username, PropertyReader.readKey("username"));
		enterInput(password, PropertyReader.readKey("password"));
		clickElement(loginbutton);

		return new AppointmentPage_POM();
	}


	public String loginWithInvalidCredentials() throws Exception {
		clickElement(makeAppointmentButton);
		enterInput(username, "admin");
		enterInput(password, PropertyReader.readKey("password"));
		clickElement(loginbutton);

		WebElement error = wait
				.until(ExpectedConditions.visibilityOfElementLocated(error_message));
		String errorMessage = error.getText();

		return errorMessage;
	}


	public boolean isErrorMessageDisplayed() {
		try {
			WebElement error = wait
					.until(ExpectedConditions.visibilityOfElementLocated(error_message));
			return error.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
