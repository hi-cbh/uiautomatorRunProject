package com.uitest.uiautomatorUtil;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.uitest.log.UiautomatorAssistant;

/**
 * 用于页面滑动，休眠唤醒。
 * 
 * 日志记录：
 * 版本                  日期                         修改者    更新内容
 * 1.0       2016-06-04   cbh  模块重新整理
 * 
 */


public class DriverManager{
	/*
	 * 日志记录：
	 * 版本                  日期                         修改者    更新内容
	 * 1.0       2016-06-04   cbh  模块重新整理
	 * 
	 */

	/**
	 * 右滑
	 */
	public static void swipeToRight() {
		System.out.println("drag");
		int width = UiDevice.getInstance().getDisplayWidth();
		int height = UiDevice.getInstance().getDisplayHeight();
		UiautomatorAssistant.UiAutomationLog("swipeToRight");
		UiDevice.getInstance().swipe(width / 2, height * 4 / 5, width, height * 4 / 5, 60);
		System.out.println("[ doing ] swipeToRight ");
	}
	
	public static void swipe(){
		System.out.println("swipe");
		int width = UiDevice.getInstance().getDisplayWidth();
		int height = UiDevice.getInstance().getDisplayHeight();
		UiautomatorAssistant.UiAutomationLog("swipe");
		UiDevice.getInstance().swipe(width / 2, height * 4 / 5, width, height * 4 / 5, 15);
		System.out.println("[ doing ] swipe ");
	}
	
	public static void swipe(int x1, int y1, int x2, int y2){
		UiautomatorAssistant.UiAutomationLog("swipe");
		UiDevice.getInstance().swipe(x1, y1, x2, y2, 15);
	}
	
	
	/**
	 * 左滑
	 */
	public static void swipeToLeft() {
		System.out.println("drag");
		int width = UiDevice.getInstance().getDisplayWidth();
		int height = UiDevice.getInstance().getDisplayHeight();
		UiDevice.getInstance().swipe(width* 4 / 5, height * 4 / 5, width / 5, height * 4 / 5, 30);
		UiautomatorAssistant.UiAutomationLog("swipeToLeft");
		System.out.println("[ doing ] swipeToRight ");
	}
	
	/**
	 * 下滑
	 */
	public static void swipeToDown() {
		System.out.println("drag");
		int width = UiDevice.getInstance().getDisplayWidth();
		int height = UiDevice.getInstance().getDisplayHeight();
		UiDevice.getInstance().swipe(width / 2, height / 5, width / 2, height * 4 / 5, 60);
		UiautomatorAssistant.UiAutomationLog("swipeToDown");
		System.out.println("[ doing ] swipeToDown ");
	}

	/**
	 * 上滑
	 */
	public void swipeToUp() {
		System.out.println("drag");
		int width = UiDevice.getInstance().getDisplayWidth();
		int height = UiDevice.getInstance().getDisplayHeight();
		UiDevice.getInstance().swipe(width / 2, height  * 4 / 5, width / 2, height / 5, 60);
		UiautomatorAssistant.UiAutomationLog("swipeToUp");
		System.out.println("[ doing ] swipeToUp ");
	}
	

	
	/**
	 * 解锁唤醒
	 */
	public static void wakeAndUnlock() {
		System.out.println("wakeAndUnlock");
		try {
			boolean bl =  UiDevice.getInstance().isScreenOn();
			System.out.println("isScreenOn: " + bl);
			if (!bl) {
				System.out.println("wakeUp");
				UiDevice.getInstance().wakeUp();
				
				swipe();
			}
			// 点击
			UiDevice.getInstance().pressHome();
		} catch (RemoteException e) {
			System.out.println("不能唤醒或开锁");
		}
	}

	/**
	 * 水平滑动屏幕
	 * @param num
	 */
	public static void switeHorizontal(int num){
	    switeHorizontal("android.support.v4.view.ViewPager",num);
	}
	
	/**
	 * 垂直滑动屏幕
	 * @param num
	 */
	public void switeVertical(String ClassName, int num){
		System.out.println("switeVertical " + num);
		UiScrollable scroll=new UiScrollable(new UiSelector().className(ClassName));
	    
	    
    	for(int i = 0; i < num; i++){
    		//向前滑动
    		try {
				scroll.scrollForward();
				UiDevice.getInstance().wait(2000);
			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	   
	    
	}
	
	/**
	 * 水平滑动屏幕
	 * @param num
	 */
	public static void switeHorizontal(String ClassName, int num){
		System.out.println("switeVertical " + num);
		UiScrollable scroll=new UiScrollable(new UiSelector().className(ClassName));
	    scroll.setAsHorizontalList();
	    
    	for(int i = 0; i < num; i++){
    		//向前滑动
    		try {
				scroll.scrollForward();
				UiDevice.getInstance().wait(2000);
			} catch (UiObjectNotFoundException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    scroll.setAsVerticalList();
	    
	}
	
	/**
	 * 点击物理返回键
	 */
	public static void pressBack(){
		//UiautomatorAssistant.UiAutomationLog("press Back");
		UiDevice.getInstance().pressBack();
	}
	
	/**
	 * 点击物理Home
	 */
	public static void pressHome(){
		//UiautomatorAssistant.UiAutomationLog("press Home");
		UiDevice.getInstance().pressHome();
	}
	
	/**
	 * 点击物理菜单
	 */
	public static void pressMenu(){
		//UiautomatorAssistant.UiAutomationLog("press Menu");
		UiDevice.getInstance().pressMenu();
	}
	
}
