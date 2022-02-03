package com.devon.utils;

import org.testng.AssertJUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.IllegalFormatException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import com.devon.base.Base;
import org.testng.Assert;
public class TestUtil extends Base {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	public static SoftAssert softAssert;

	public static String TESTDATA_SHEET_PATH = "/Users/naveenkhunteta/Documents/workspace"
			+ "/FreeCRMTest/src/main/java/com/crm/qa/testdata/FreeCrmTestData.xlsx";

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;

	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IllegalFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	// Click Webelement
	  public static void clickElement(WebElement element) {
	    if (element.isEnabled()) {
	      element.click();
	    } else {
	    	softAssert.assertTrue(element.isEnabled(), element + " not enabled so unable to click");
	    }
	  }
	  
	  //waitFor Element
	  public static void waitForElementToBeVisble(By locator) {
		  WebDriverWait wait = new WebDriverWait(driver, PAGE_LOAD_TIMEOUT);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	  }
	  
	//waitFor Element
	  public static void waitForElementToBeVisble(WebElement ele) {
		  WebDriverWait wait = new WebDriverWait(driver, PAGE_LOAD_TIMEOUT);
		  wait.until(ExpectedConditions.visibilityOf(ele));
	  }
	  
	  // ClearText
	  public static void clearTextField(By ele) {
//		  if(!ele.getAttribute("value").isEmpty())
//			  ele.clear();
		  JavascriptExecutor js = (JavascriptExecutor)driver;
		  js.executeScript("arguments[0].value = '';", ele);
	  }

	

}