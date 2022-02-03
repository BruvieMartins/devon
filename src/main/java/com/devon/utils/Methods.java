package com.devon.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.devon.base.Base;

public class Methods extends Base{
	public static void takeScreenshot() {

	    String pattern = "yyyy-MM-dd HH mm ss SSS";
	    SimpleDateFormat simpleDateFormat =
	            new SimpleDateFormat(pattern);

	    String date = simpleDateFormat.format(new Date());

	    try {
	    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    String currentDir = System.getProperty("user.dir");
	    System.out.println(currentDir);
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	    }
	    catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
