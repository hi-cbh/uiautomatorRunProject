package com.uitest.testdemo;


import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.donot.change.By;
import com.donot.change.UiAutomatorHelper;
import com.uitest.data.UserConfig;
import com.uitest.log.UiautomatorAssistant;
import com.uitest.uiautomatorUtil.CommonUtil;
import com.uitest.uiautomatorUtil.DriverManager;
import com.uitest.uiautomatorUtil.Ele;
import com.uitest.uiautomatorUtil.TimeUtil;

public class OpenMail  extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "OpenMail";
		String testClass = "com.uitest.testdemo.OpenMail";
		String testName = "testEmail";
		String androidId = UserConfig.androidId;
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	
    public void testEmail() throws Exception{  
    	String time1 = "";
    	String time2 = "";
    	long valuetime = 0;
    	for(int i = 0; i < 10; i++){
        	DriverManager.pressHome();
        	
        	CommonUtil.sleep(3000);
        	
        	UiObject uo =  Ele.waitForExistst(By.NAME, "139邮箱");
        	
        	time1 = TimeUtil.getCurrentSysTime();
        	uo.click();
        	
        	Ele.waitForExistst(By.NAME, "收件箱", 30);
        	time2 = TimeUtil.getCurrentSysTime();
        	
        	DriverManager.pressHome();
        
        	valuetime =  TimeUtil.getTimeDistance(time2, time1);
        	
        	System.out.println("杀进程启动时间: " + (valuetime/1000.0) );
        	//UiautomatorAssistant.saveData("time: "+valuetime/1000.0);
        	
        	CommonUtil.sleep(2000);
        	
        	CommonUtil.adbStopApp("cn.cj.pe");
        	
        	CommonUtil.sleep(2000);
    	}


    } 
	
}
