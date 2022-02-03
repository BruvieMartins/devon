package com.devon.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.devon.base.Base;

public class CheckoutPage extends Base {
	//Page Factory		
		private static By firstName = By.name("firstName");
		private static By lastName = By.name("lastName");
		private static By postalCode = By.name("postalCode");
		private static By checkoutBtn = By.name("checkout");
		private static By continueBtn = By.name("continue");
		private static By finish = By.name("finish");
		private static By backHomeBtn = By.id("back-to-products");
		
	//Initializing the Page Objects:
		public CheckoutPage(){
			PageFactory.initElements(driver, this);
		}
	
	//Actions:
		public static String checkoutPageTitle(){
			return driver.findElement(By.className("title")).getText();
		}
	
		public static boolean validateItemInCart(String itemName) {
			boolean result = false;
			List<WebElement> list = driver.findElements(By.xpath("//div[@class='cart_item']"
				+ "//div[@class='inventory_item_name']"));
			for(WebElement ele: list) {
				if(ele.getText().equalsIgnoreCase(itemName))
					result=true;
			}
			return result;
		
		}
	
		public void providePersonalDetails(String FirstName, String LastName, String zipcode) {
			type(FirstName,firstName);
			type(LastName, lastName);
			type(zipcode,postalCode);
		}
	
		public static boolean isItemPresentInCart() {
			List<WebElement> list = driver.findElements(By.xpath("//a[@class='shopping_cart_link']/span"));
			if(list.size()>1)
				return true;
			else
				return false;
		}
		public static WebElement getBackHomeBtn() {
			return find(backHomeBtn);
		}

		public static WebElement getFirstName() {
			return find(firstName);
		}

		public static WebElement getLastName() {
			return find(lastName);
		}

		public static WebElement getPostalcode() {
			return find(postalCode);
		}

		public static WebElement getCheckoutBtn() {
			return find(checkoutBtn);
		}

		public static WebElement getContinueBtn() {
			return find(continueBtn);
		}

		public static WebElement getFinish() {
			return find(finish);
		}
}
