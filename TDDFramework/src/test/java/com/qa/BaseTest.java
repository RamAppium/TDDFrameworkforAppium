package com.qa;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class BaseTest {
  protected static AppiumDriver driver;
  protected  static Properties props;
  InputStream inputStream;
	 
  public BaseTest() {
	  PageFactory.initElements(new AppiumFieldDecorator(driver),this);
  }
  @Parameters({"platformName","platformVersion","deviceName"})
  @BeforeTest
  public void beforeTest( String platformName, String platformVersion, String deviceName) throws Exception {
	  try {
		 props = new Properties();
		 String propFileName="config.properties";
		 inputStream =getClass().getClassLoader().getResourceAsStream(propFileName);
		 props.load(inputStream);
		 
		 
		 DesiredCapabilities caps= new DesiredCapabilities();
		 caps.setCapability("PLATFORM_NAME", platformName);
		 caps.setCapability("PLATFORM_VERSION",platformVersion);
	     caps.setCapability("DEVICE_NAME", deviceName);
	     caps.setCapability("AUTOMATION_NAME",props.getProperty("androidAutomationName"));
	     caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
		 caps.setCapability("appActivity",props.getProperty("androidAppActivity"));
	     caps.setCapability("avd","pixel_6_pro_API_32");
	     
	     String appUrl=getClass().getResource(props.getProperty("androidAppLocation")).getFile();
	     caps.setCapability("app",appUrl);	     
	     URL url=new URL(props.getProperty("appiumURL"));
	     driver= new AndroidDriver(url,caps );
	     String sessionID=driver.getSessionId().toString();
	    // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  	  
	  }
	  catch (Exception e) {
		  e.printStackTrace();
		  throw e;
	  }	  
  }

  	public void waitForVisibility(WebElement e) {
  		WebDriverWait wait= new WebDriverWait (driver,Duration.ofSeconds(10));
  		wait.until(ExpectedConditions.visibilityOf(e));
  	}
  	
  	public void Click(WebElement e) {
  		waitForVisibility(e);
  		e.click();
  		
  	}
  	
  	public void sendKeys(WebElement e, String txt) {
  		waitForVisibility(e);
  		e.sendKeys(txt);
  	}
  	
  	public String getAttribute(WebElement e, String attribute) {
  		waitForVisibility(e);
  		return e.getAttribute(attribute);
  	}
  @AfterTest
  public void afterTest() {
	  //driver.quit();  
	  
  }	  

}
