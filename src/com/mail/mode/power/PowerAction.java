package com.mail.mode.power;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.mail.pageobject.power.PowerPageObject;
import com.uitest.uiautomatorUtil.CommonUtil;
import com.uitest.uiautomatorUtil.ImageManager;

public class PowerAction {
	//private final static int timeout =  60;
	
	/**
	 * 重置，并启动
	 * @throws UiObjectNotFoundException 
	 */
	public static void execute() throws UiObjectNotFoundException{
		PowerPageObject ppo = new PowerPageObject();
		
		CommonUtil.adbClearCache("edu.umich.PowerTutor");
		
		
		
		CommonUtil.adbStartAPP("edu.umich.PowerTutor","edu.umich.PowerTutor.ui.UMLogger");
		
		ppo.into();
		
		ppo.clickStart();
		
		CommonUtil.sleep(2000);

		ppo.clickViewer();
		
		ppo.clickLW3();
		
		CommonUtil.adbBack();
		
		
		ppo.clickStart();
		
		ppo.clickStart();
		
//		ppo.clickViewer();
//		
//		CommonUtil.sleep(5000);
		
		CommonUtil.adbHome();
		
	}
	
	
	/**
	 * 打开，并记录
	 * @param 
	 * @param findText
	 * @return
	 * @throws UiObjectNotFoundException 
	 */
	public static String execute2() throws UiObjectNotFoundException{
		PowerPageObject pp = new PowerPageObject();

		String  strpowertotal="";
		String result = "";
		
		
		CommonUtil.adbHome();
		
		CommonUtil.adbStartAPP("edu.umich.PowerTutor","edu.umich.PowerTutor.ui.UMLogger");
		
		pp.clickViewer();
		
		
		CommonUtil.sleep(3000);
		
		//这里添加截图
		ImageManager.snapshot("PowerPic", "Standby for 30 minutes");
			
		CommonUtil.adbBack();
		
		
		pp.clickStart();
		
		System.out.println("流量值：" + strpowertotal);
		return strpowertotal;
	}

	
}
