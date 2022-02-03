package com.devon.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.devon.base.Base;

public class ProductPage extends Base{
		//Page Factory - OR:
			private static By cart = By.name("shopping_cart_link");
		
		//Initializing the Page Objects:
			public ProductPage(){
				PageFactory.initElements(driver, this);
			}
			
			public static String productPageTitle(){
				return find(By.className("title")).getText();
			}
		
			
		//Actions:		
			public void addItem(String itemName) {
				WebElement element = find(By.xpath("//a[div[text()='"+itemName+"']]"
						+ "/parent::div/following-sibling::div//button"));
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView(true);",element); 
				element.click();
			}
			
			public static void clickCartIcon() {
				find(cart).click();
			}
}
