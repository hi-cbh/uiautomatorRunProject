package com.uitest.testdemo;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.donot.change.UiAutomatorHelper;
import com.gt.model.GTAction;
import com.gt.util.GTTest;
import com.uitest.data.UserConfig;
import com.uitest.log.UiautomatorAssistant;
import com.uitest.uiautomatorUtil.CommonUtil;
import com.uitest.uiautomatorUtil.ImageManager;

public class Pic extends UiAutomatorTestCase{

	public static void main(String[] args) {
		String jarName = "test";
		String testClass = "com.uitest.testdemo.Pic";
		String testName = "test";
		String androidId = UserConfig.androidId;
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	
	public void test(){
		GTTest gt = new GTTest("cn.cj.pe");
		gt.startGT();
		
		CommonUtil.sleep(10000);
		
		
		UiautomatorAssistant.saveData(gt.endGT());
		
		
	}
	
}
