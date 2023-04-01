package com.qa.test;

import org.testng.annotations.Test;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTests extends BaseTest{
	LoginPage loginPage;
	ProductPage productPage;
  
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }
  @BeforeMethod
  public void beforeMethod(Method m) {
	  loginPage= new LoginPage();
	  System.out.println("\n"+"******* starting test:" +m.getName() + "************" + "\n");
  }

  @AfterMethod
  public void afterMethod() {
  }
  
  @Test
  public void invalidUserName() {
	  loginPage.enterUserName("invalidusername");
	  loginPage.enterPassword("secret_sauce");
	  loginPage.pressLoginBtn();
	  
	  String actualErrortxt=loginPage.getErrorTxt();
	  String expectedtxt="Username and password do not match any user in this service";
	  System.out.println("Actual Error text-"+actualErrortxt + "\n" + "Expected Error txt-"+ expectedtxt);
	  Assert.assertEquals(actualErrortxt, expectedtxt);
  
  
  }
  public void invalidPassword() {
	  loginPage.enterUserName("standard_user");
	  loginPage.enterPassword("invalidpassword");
	  loginPage.pressLoginBtn();
	  
	  String actualErrortxt=loginPage.getErrorTxt();
	  String expectedtxt="Username and password do not match any user in this service";
	  System.out.println("Actual Error text-"+actualErrortxt + "\n" + "Expected Error txt-"+ expectedtxt);
	  Assert.assertEquals(actualErrortxt, expectedtxt);
  
  
  }
  public void successfilLogin() {
	  loginPage.enterUserName("standard_user");
	  loginPage.enterPassword("secret_sauce");
	 productPage= loginPage.pressLoginBtn();
	  
	  String actualProductTitle=productPage.GetTitle();
	  String expectedProductTitle="PRODUCTS";
	  System.out.println("Actual Title-"+actualProductTitle + "\n" + "Expected Error txt-"+ expectedProductTitle);
	  Assert.assertEquals(actualProductTitle, expectedProductTitle);
  
  
  }
}
