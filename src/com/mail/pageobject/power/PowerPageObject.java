package com.mail.pageobject.power;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.donot.change.By;
import com.uitest.uiautomatorUtil.Ele;


public class PowerPageObject {

	private final static int timeout =  60;
	/**
	 * 重置app后，首次进入，点击按钮
	 * @throws UiObjectNotFoundException 
	 */
	public void into() throws UiObjectNotFoundException{
		Ele.waitForExistst(By.NAME, "Ok").click();
		Ele.waitForExistst(By.NAME, "Agree").click();
	}
	
	/**
	 * 点击开始记录按钮
	 * @param driver
	 * @throws UiObjectNotFoundException 
	 */
	public void clickStart() throws UiObjectNotFoundException{
		Ele.waitForExistst(By.ID, "edu.umich.PowerTutor:id/servicestartbutton").click();
	}
	

	/**
	 * 点击application viewer
	 * @param driver
	 * @throws UiObjectNotFoundException 
	 */
	public void clickViewer() throws UiObjectNotFoundException{
		Ele.waitForExistst(By.ID, "edu.umich.PowerTutor:id/appviewerbutton").click();
	}

	
	/**
	 * 点击取消 LCD、wifi、3G监控
	 * @throws UiObjectNotFoundException 
	 */
	public void clickLW3() throws UiObjectNotFoundException{
		Ele.waitForExistst(By.NAME, "LCD").click();
		Ele.waitForExistst(By.NAME, "Wifi").click();
		Ele.waitForExistst(By.NAME, "3G").click();
	}
}
