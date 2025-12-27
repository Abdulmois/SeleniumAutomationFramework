package BasePackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverPackage.DriverManagerTL;

public class CommonToAllPages {


	protected WebDriver driver;
	protected WebDriverWait wait;

	public CommonToAllPages() {
		this.driver = DriverManagerTL.getDriver();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}



	protected void clickElement(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	protected void enterInput(By locator, String value) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
		element.sendKeys(value);
	}

	protected WebElement getElement(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}


	protected WebElement presenceOfElement(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	protected WebElement visibilityOfElement(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	protected void waitForUrlContains(String urlPart) {
		wait.until(ExpectedConditions.urlContains(urlPart));
	}


	protected void selectDropdown(By locator, String visibleText) {
		WebElement dropdown = getElement(locator);
		Select select = new Select(dropdown);
		select.selectByVisibleText(visibleText);
	}
}

