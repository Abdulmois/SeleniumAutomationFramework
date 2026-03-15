package DriverPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class DriverManagerTL {


	public static final ThreadLocal<WebDriver>dr=new ThreadLocal<WebDriver>();



	public static void setDriver(WebDriver driverRef) {
		dr.set(driverRef);
	}



	public static WebDriver getDriver() {
		return dr.get();
	}



	public static void unload() {
		dr.remove();
	}



	public static void down() {
		if(Objects.nonNull(DriverManagerTL.getDriver())) {
			getDriver().quit();
			unload();
		}
	}



	public static void init() {

	    if (Objects.isNull(DriverManagerTL.getDriver())) {

	        ChromeOptions options = new ChromeOptions();

	        options.addArguments("--disable-notifications");
	        options.addArguments("--disable-infobars");
	        options.addArguments("--disable-popup-blocking");
	        options.addArguments("--incognito");

	        
	        options.addArguments("--user-data-dir=C:/temp/automation-profile");

	        WebDriver driver = new ChromeDriver(options);
	        driver.manage().window().maximize();

	        setDriver(driver);
	    }
	}




}
