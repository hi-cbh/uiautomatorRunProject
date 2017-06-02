package com.gt.util;


/**
 * GT启动、停止方法
 * @author Administrator
 *
 */
public class GTTest {

	private String pkgname = "";
	private String mem="";
	private String cpu = "";
	
	public GTTest(String pkg){
	this.pkgname = pkg;	
	}
	
	
	
	public void startGT(){
		//System.out.println("adbStartGT");
		GTUtil.adbStartGT();
		
		//System.out.println("adbGTAddPkg");
		GTUtil.adbGTAddPkg(pkgname);
		
		//System.out.println("adbGTbaseCommand");
		GTUtil.adbGTbaseCommand("cpu", "1");
		
		//System.out.println("adbGTbaseCommand");
		GTUtil.adbGTbaseCommand("pri", "1");
		
		//System.out.println("sleep");
		GTUtil.sleep(2*1000);
		
		//System.out.println("adbHome");
		GTUtil.adbBack();
		
		//System.out.println("sleep");
		GTUtil.sleep(2*1000);
		
		System.out.println(GTUtil.currentTime() + " GT开始记录.....");
	}
	
	public String endGT(){
		
		try{

			String path = "/mnt/sdcard/GT/GW/";
			//String desPath = "D:/workspace/workspace_work/GTTest/logs/";
			String desPath = "/mnt/sdcard" + "/logs/";
			//String p = System.getProperty("user.dir") + "/logs/";
			//System.out.println("路径：" + p);
			//desPath = p;
			//System.out.println("adbGTbaseCommand");
			GTUtil.adbGTbaseCommand("cpu", "0");
			//System.out.println("adbGTbaseCommand");
			GTUtil.adbGTbaseCommand("pri", "0");
			//System.out.println("adbGTSave");
			String fileName = GTUtil.currentTime();
			System.out.println("fileName:" + fileName);
			GTUtil.adbGTSave("/runcode/"+fileName, fileName);
			
			//有清除缓存的作用
			//System.out.println("adbGTExit");
//			GTUtil.adbGTExit();

			
			//System.out.println("cp"+path+pkgname+"/runcode/"+fileName+"/  "+ desPath);
			//GTUtil.adbPull(path+pkgname+"/runcode/"+fileName, desPath);
			
			GetCSVData gsd = new GetCSVData();
			
			String cpuValue = "";
			String memValue = "";
			
			cpuValue = gsd.getCPUValue(path+pkgname+"/runcode/"+fileName+"/",pkgname);
			memValue = gsd.getMEMValue(path+pkgname+"/runcode/"+fileName+"/",pkgname);
						
			return cpuValue + ";" + memValue;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("第一次运行出错，或者没有启动测试APP，请关闭该重新，重新打开！");
		}
		
		return "";
	}
}
