package DriverPackage;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


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
		if(Objects.isNull(DriverManagerTL.getDriver())) {
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			setDriver(driver);
		}
	}


}
