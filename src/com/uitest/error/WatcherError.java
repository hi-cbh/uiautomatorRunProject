package com.uitest.error;

import android.graphics.Rect;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiWatcher;
import com.donot.change.By;
import com.uitest.uiautomatorUtil.Ele;


/**
 * 捕获异常
 * 
 * @author Administrator
 * 
 */
public class WatcherError {

	/**
	 * 注册事件 系统已停止
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public static void registerHasStopped() {
		// 注册事件，必须声明在用例前，否则不会执行
		UiDevice.getInstance().registerWatcher("stop", new UiWatcher() {
            public boolean checkForCondition() {
            	
            	UiObject msg = Ele.waitForExistst(By.ID, "android:id/message");
            	
            	if(isContains(msg, "已停止")){
            		
            	}
            	else{
            		return false;
            	}
            		
            		
                UiObject uo = Ele.waitForExistst(By.ID, "android:id/button1");
                Rect rt;
                int x,y;
                if(uo.exists())
                {
                    try {
                        System.out.println("已停止被触发");
                        rt = uo.getBounds();
                        x = rt.centerX();
                        y = rt.centerY();
                        
                        Ele.click(x, y);
                        
                        return true;
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
	}

	/**
	 * 取消监听 stop
	 */
	public static void stopHasStopped() {
		//
		UiDevice.getInstance().removeWatcher("stop");
	}

	/**
	 * 注册事件 无卡拨号出现确定
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public static void registerCall() {
		// 注册事件，必须声明在用例前，否则不会执行
		UiDevice.getInstance().registerWatcher("call", new UiWatcher() {
            public boolean checkForCondition() {
//            	UiObject msg = ElementManager.getUiObjectByResourceId("android:id/message");
//            	if(isContains(msg, "注册") || isContains(msg, "呼叫")){
//            		
//            	}
//            	else{
//            		return false;
//            	}
            	
                UiObject uo = Ele.waitForExistst(By.ID, "android:id/button1");
                Rect rt;
                int x,y;
                if(uo.exists())
                {
                    try {
                        System.out.println("电话监听器被触发");
                        rt = uo.getBounds();
                        x = rt.centerX();
                        y = rt.centerY();
                        
                        Ele.click(x, y);
                        Ele.click(x, y);
                        return true;
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
	}

	/**
	 * 取消监听 call
	 */
	public static void stopCall() {
		//
		UiDevice.getInstance().removeWatcher("call");
	}

	
	public static boolean isContains(UiObject uo, String ct2){
		String ct1 = null;
		try {
			ct1 = uo.getText();
			System.out.println("显示：" + ct1);
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}
		
		if(ct1.contains(ct2)){
			return true;
		}
		
		return false;
	}
	
	
}
