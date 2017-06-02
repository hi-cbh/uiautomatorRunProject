package com.gt.model;

import com.gt.util.GTUtil;
import com.gt.util.GetCSVData;
import com.uitest.uiautomatorUtil.CommonUtil;


public class GTAction {

	/**
	 * 记录CPU内存信息，并打印出来
	 * 
	 * @param pkgname
	 *            包名
	 * @param time
	 *            秒，运行多长时间
	 */
	public static void execute(String pkgname, int time) {

		try {
			// System.out.println("adbStartGT");
			GTUtil.adbStartGT();

			// System.out.println("adbGTAddPkg");
			GTUtil.adbGTAddPkg(pkgname);

			// System.out.println("adbGTbaseCommand");
			GTUtil.adbGTbaseCommand("cpu", "1");

			// System.out.println("adbGTbaseCommand");
			GTUtil.adbGTbaseCommand("pri", "1");

			// System.out.println("sleep");
			GTUtil.sleep(2 * 1000);

			// System.out.println("adbHome");
			GTUtil.adbBack();

			System.out.println(GTUtil.currentTime() + " GT开始记录.....");
			GTUtil.sleep(2 * 1000);
			
			
			System.out.println("等待：" + time + " 秒");
			CommonUtil.sleep(time * 1000);

			String path = "/mnt/sdcard/GT/GW/";
			String desPath = System.getProperty("user.dir") + "/logs/";

			// System.out.println("adbGTbaseCommand");
			GTUtil.adbGTbaseCommand("cpu", "0");
			// System.out.println("adbGTbaseCommand");
			GTUtil.adbGTbaseCommand("pri", "0");
			// System.out.println("adbGTSave");
			String fileName = GTUtil.currentTime();

			System.out.println("fileName:" + fileName);
			GTUtil.adbGTSave("/runcode/" + fileName, fileName);

			 //有清除缓存的作用
			 System.out.println("adbGTExit");
			 GTUtil.adbGTExit();

			System.out.println("adb pull" + path + pkgname + "/runcode/"
					+ fileName + "/" + desPath + fileName + "/");
			GTUtil.adbPull(path + pkgname + "/runcode/" + fileName + "/",
					desPath + fileName + "/");

			GetCSVData gsd = new GetCSVData();

			gsd.getCPUValue(desPath + fileName + "/", pkgname);
			gsd.getMEMValue(desPath + fileName + "/", pkgname);

		} catch (Exception e) {
			System.out.println("第一次运行出错，或者没有启动测试APP");
		}

	}

}
