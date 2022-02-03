package com.project.devon;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.devon.Pages.CheckoutPage;
import com.devon.Pages.LoginPage;
import com.devon.Pages.ProductPage;
import com.devon.base.Base;
import com.devon.utils.CustomSoftAssert;
import com.devon.utils.TestUtil;

public class ItemTest extends Base {
	  public static CheckoutPage checkout= new CheckoutPage();
	  public static LoginPage loginPage;
	  public static ProductPage product;
	  
	  @BeforeClass
	  void LaunchApp() {
		  initialization();
	  }
	 
	 @Test(priority=1)
	 public void failedLoginTest() {
		 // declare variables
		 loginPage = new LoginPage();
		 String expectedTitle = "Swag Labs";
		 String lockedusername = prop.getProperty("lockedUser");
		 String password = prop.getProperty("password");
		 CustomSoftAssert softAssert = new CustomSoftAssert(); 
		 By path = By.xpath("//input[@type='submit']");
		 String expectedMessage = "Sorry, this user has been locked out.";
		 
		 TestUtil.waitForElementToBeVisble(path);
		 String actualTitle = loginPage.validateLoginPageTitle();
		 softAssert.assertEquals(actualTitle, expectedTitle);
		 loginPage.login(lockedusername, password);
		 String msg = loginPage.getLockedOutMessage();
		 softAssert.assertTrue(msg.contains(expectedMessage),"Locked Out Message Displayed Successfully");
		 softAssert.assertAll();
	 }
	 
	 @Test(priority=2,dependsOnMethods = { "failedLoginTest" })
	 public void UserLogin() {
		 // Declare Variables
		 loginPage = new LoginPage();
		 product = new ProductPage();
		 String username = prop.getProperty("username");
		 String password = prop.getProperty("password");
		 CustomSoftAssert softAssert = new CustomSoftAssert(); 
		 String expectedTitle= "PRODUCTS";
		
		 loginPage.login(username, password);
		 String actualTitle = product.productPageTitle();
		 softAssert.assertEquals(actualTitle, expectedTitle,"User is on"+actualTitle+" page");
		 softAssert.assertAll();
	 }
	 
	 @Test(priority=3)
	 public void addItemToList() {
		// Declare Variables
		 product = new ProductPage();
		 checkout = new CheckoutPage();
		 CustomSoftAssert softAssert = new CustomSoftAssert();
		 String productName = prop.getProperty("itemName");
		 
		 product.addItem(productName);
		 product.clickCartIcon();
		 TestUtil.waitForElementToBeVisble(checkout.getCheckoutBtn());
		 softAssert.assertTrue(checkout.validateItemInCart(productName),productName+" Item is added");	
		 softAssert.assertAll();
	 }
	 
	 @Test(priority=4)
	 public void usercheckOutItem() {
		// Declare Variables
			checkout = new CheckoutPage();
			CustomSoftAssert softAssert = new CustomSoftAssert(); 
			String productName = prop.getProperty("itemName");
			String firstName = prop.getProperty("firstName");
			String lastName = prop.getProperty("lastName");
			String zipcode = prop.getProperty("zipcode");
			String titleYourInfo = "CHECKOUT: YOUR INFORMATION";
			String titleOverview = "CHECKOUT: OVERVIEW";
			
			TestUtil.clickElement(checkout.getCheckoutBtn());
			TestUtil.waitForElementToBeVisble(checkout.getContinueBtn());
			String actualTitle = checkout.checkoutPageTitle();
			softAssert.assertEquals(actualTitle, titleYourInfo,"You are on Page"+actualTitle);
			checkout.providePersonalDetails(firstName, lastName, zipcode);
			TestUtil.clickElement(checkout.getContinueBtn());
			TestUtil.waitForElementToBeVisble(checkout.getFinish());
			String actualTitleFinish = checkout.checkoutPageTitle();
			softAssert.assertEquals(actualTitleFinish, titleOverview,"You are on Page"+actualTitleFinish);
			softAssert.assertAll();
	 }
	 
	 @Test(priority=5)
	 public void itemOrder() {
		// Declare Variables
			checkout = new CheckoutPage();
			CustomSoftAssert softAssert = new CustomSoftAssert();
			String expectedtitle = "CHECKOUT: COMPLETE!";
			
			TestUtil.clickElement(checkout.getFinish());
			TestUtil.waitForElementToBeVisble(checkout.getBackHomeBtn());
			String actualTitle = checkout.checkoutPageTitle();
			softAssert.assertEquals(actualTitle, expectedtitle);
			softAssert.assertFalse(checkout.isItemPresentInCart());
			softAssert.assertAll();
	 }
	 
	 @AfterClass
	  void tearDown() {
		  driver.quit();
	  }
}
