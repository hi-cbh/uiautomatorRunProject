package com.uitest.log;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.uitest.data.UserConfig;
import com.uitest.uiautomatorUtil.ImageManager;

/**
 * 
 * 记录app运行日志
 * 
 */
public class UiautomatorAssistant extends UiAutomatorTestCase {
	/* UiDevice对象 */


	/* 添加了是否写入日志，默认为true */
	private static boolean FLAG = UserConfig.isWriteLog;

	public static boolean isFLAG() {
		return FLAG;
	}

	public static void setFLAG(boolean fLAG) {
		FLAG = fLAG;
	}

	/** 打log记录在手机中,前缀没有时间 */
	public static void saveData(String str) {
		if (isFLAG() != true) {
			return;
		}

		// 取得当前时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		String datestr = calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND) + ":";

		String datestr2 = datestr.replaceAll(":", "_");
		String snapshotPic = "not_find_"+datestr2;
		
		
		FileWriter fwlog = null;
		try {
			String path = UserConfig.saveAppLogPath + "PerformanceLog.txt";
			fwlog = new FileWriter(path, true);
			//写入文件
			fwlog.write(str + "\r\n");
			
			//判断是否找不到对象，截图“not-find”是关键字
			if(str.contains("not-find") || str.contains("失败")){

				ImageManager.takesnapshot(UserConfig.saveAppLogPath, snapshotPic );
				//写入截图名字
				fwlog.write("TakeScreen: " + snapshotPic + ".jpg" + "\r\n");
			}

			/**
			 * 验证后，添加分隔行
			 */
			if (str.contains("开始") || str.contains("结束")) {
				fwlog.write("------------------" + "\r\n");
			}
			//System.out.println(str);

			fwlog.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fwlog.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/* 打log记录在手机中 */
	public static void UiAutomationLog(String str) {
		if (isFLAG() != true) {
			return;
		}

		// 取得当前时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		String datestr = calendar.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendar.get(Calendar.MINUTE) + ":"
				+ calendar.get(Calendar.SECOND) + ":";

		String datestr2 = datestr.replaceAll(":", "_");
		String snapshotPic = "not_find_"+datestr2;
		
		
		FileWriter fwlog = null;
		try {
			String path = UserConfig.saveAppLogPath + "PerformanceLog.txt";
			fwlog = new FileWriter(path, true);
			//写入文件
			fwlog.write(datestr + str + "\r\n");
			
			//判断是否找不到对象，截图“not-find”是关键字
			if(str.contains("not-find") || str.contains("失败")){

				ImageManager.takesnapshot(UserConfig.saveAppLogPath, snapshotPic );
				//写入截图名字
				fwlog.write("TakeScreen: " + snapshotPic + ".jpg" + "\r\n");
			}

			/**
			 * 验证后，添加分隔行
			 */
			if (str.contains("开始") || str.contains("结束")) {
				fwlog.write("------------------" + "\r\n");
			}
			//System.out.println(str);

			fwlog.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fwlog.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}