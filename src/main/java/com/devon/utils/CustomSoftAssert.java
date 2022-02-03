package com.devon.utils;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

public class CustomSoftAssert extends SoftAssert {

	@Override
    public void onAssertFailure(IAssert<?> a, AssertionError ex) {
          Methods.takeScreenshot();
    }
    
	@Override
    public void onAssertSuccess(IAssert<?> a) {
		Methods.takeScreenshot();
	}
}