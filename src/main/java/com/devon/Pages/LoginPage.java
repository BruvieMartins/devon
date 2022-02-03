package com.devon.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.devon.base.Base;
import com.devon.utils.TestUtil;

public class LoginPage extends Base{
	//Page Factory - OR:
		private static By username = By.name("user-name");
		private static By password = By.name("password");
		private static By loginBtn = By.name("login-button");
		private static By lockedUserMessage = By.xpath("//h3[contains(text(),'this user has been locked out.')]");
		
	//Initializing the Page Objects:
		public LoginPage(){
			PageFactory.initElements(driver, this);
		}
		
	//Actions:
		public static void login(String un, String pwd){
			TestUtil.clearTextField(LoginPage.username);
			find(username).sendKeys(un);
			TestUtil.clearTextField(LoginPage.password);
			find(password).sendKeys(pwd);			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", loginBtn); 	
			
		}
		
		public static String getLockedOutMessage() {
			return find(lockedUserMessage).getText();
		}
		
		public static String validateLoginPageTitle(){
			return driver.getTitle();
		}
		
		public WebElement getUsername() {
			return find(username);
		}

		public static void setUsername(By username) {
			LoginPage.username = username;
		}

		public WebElement getPassword() {
			return find(password);
		}

		public void setPassword(By password) {
			LoginPage.password = password;
		}

		public WebElement getLoginBtn() {
			return find(loginBtn);
		}

		public void setLoginBtn(By loginBtn) {
			LoginPage.loginBtn = loginBtn;
		}

		public static WebElement getLockedUserMessage() {
			return find(lockedUserMessage);
		}

		public static void setLockedUserMessage(By lockedUserMessage) {
			LoginPage.lockedUserMessage = lockedUserMessage;
		}
}
