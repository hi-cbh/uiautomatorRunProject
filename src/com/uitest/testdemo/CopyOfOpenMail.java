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

public class CopyOfOpenMail  extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "OpenMail";
		String testClass = "com.uitest.testdemo.OpenMail";
		String testName = "testEmail";
		String androidId = UserConfig.androidId;
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	
	/**
	 * 对数测试
	 * 13501538531  chinasofti139
	 * @throws Exception
	 */
    public void testEmail() throws Exception{  

    	for(int i = 0; i < 2; i++){
    		
        	CommonUtil.sleep(2000);
        	
        	CommonUtil.adbStopApp("cn.cj.pe");
        	
        	CommonUtil.sleep(2000);
        	
        	Ele.waitForExistst(By.NAME, "139邮箱").click();
        	
        	CommonUtil.sleep(3000);
        	
        	System.out.println("点击屏幕");
        	CommonUtil.tap(500, 500);
        	UiautomatorAssistant.saveData(TimeUtil.getCurrentSysTime() +" 第 " + i + " 次添加屏幕");
        	
        	UiObject uo =  Ele.waitForExistst(By.NAME, "139邮箱", 20);
        	
        	CommonUtil.sleep(2000);
        	
        	uo.click();
        	
        	Ele.waitForExistst(By.ID,"cn.cj.pe:id/actionbar_right_view",20);
        	
        	Ele.waitForExistst(By.NAME, "收件箱", 20);
        	
        	DriverManager.pressHome();

        	CommonUtil.sleep(2000);
    	}


    } 
	
}
