package com.uitest.testdemo;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.donot.change.UiAutomatorHelper;
import com.gt.util.GTTest;
import com.mail.mode.power.PowerAction;
import com.uitest.data.UserConfig;
import com.uitest.log.UiautomatorAssistant;
import com.uitest.uiautomatorUtil.CommonUtil;
import com.uitest.uiautomatorUtil.DriverManager;

public class PowerFlowCpuMen extends UiAutomatorTestCase {

	public static void main(String[] args) {
		String jarName = "PowerFlowCpuMen";
		String testClass = "com.uitest.testdemo.PowerFlowCpuMen";
		String testName = "test";
		String androidId = UserConfig.androidId;
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	

	}
	/**
	 * 检测CPU、内存、电量消耗、流量等方法，可长时间运行不报错，替代appium（长时间容易报错）
	 * @throws Exception
	 */
	public void test() throws Exception{  
		String appPackage139 = "cn.cj.pe";
		
		CommonUtil.sleep(3000);
		DriverManager.pressHome();

		GTTest gt = new GTTest(appPackage139);

		for(int i =0; i<2; i++){
			CommonUtil.sleep(3000);
			PowerAction.execute();
			
			gt.startGT();
			CommonUtil.sleep(3000);
			System.out.println("等待30分钟.....");
			for(int j = 0; j < 1; j++){
				System.out.println("等待分钟: "+ (j+1));
				CommonUtil.sleep(1*60*1000);
			}
			System.out.println("开始记录.....");
			PowerAction.execute2();
			UiautomatorAssistant.saveData("CPU#内存峰值：" + gt.endGT());
			
			System.out.println("kill 电量监测工具");
			CommonUtil.sleep(3000);
			CommonUtil.adbStopApp("edu.umich.PowerTutor");

		}	
		
	}
}
