package com.qa.pages;

import org.openqa.selenium.WebElement;

import com.qa.BaseTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {
	@AndroidFindBy (accessibility = "test-Username") private WebElement userNameTxtFld;
	@AndroidFindBy (accessibility = "test-Password") private WebElement PasswordTxtFld;
	@AndroidFindBy (accessibility = "test-LOGIN") private WebElement LoginBtn;
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\\\"test-Error message\\\"]/android.widget.TextView") private WebElement ErrorText;


	public LoginPage enterUserName(String userName) {
		sendKeys(userNameTxtFld , userName);
		return this;
	}
	public LoginPage enterPassword(String Password) {
		sendKeys(PasswordTxtFld , Password);
		return this;
	}
	public ProductPage pressLoginBtn() {
		Click(LoginBtn);
		return new ProductPage();
	}
	public String getErrorTxt() {
		return getAttribute(ErrorText, "text");
	}
}