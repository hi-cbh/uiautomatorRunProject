package com.gt.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.uitest.uiautomatorUtil.CommonUtil;

public class GTUtil {

	/**
	 * 运行命令行
	 * @param cmd
	 */
    public static void cmdLine(String cmd){
    	try {
			Runtime.getRuntime().exec(cmd);
			CommonUtil.sleep(3000);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	
	/**
	 * 输出当前时间
	 * @return
	 */
    public static String currentTime(){
			Date date =new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			return   sdf.format(date);
    }
	
	

	/**
	 * @String 设置等待超时时间
	 */
	public static boolean readTimeOut(long timeoutMillis) {
		boolean isTimeOut = true;
		long timeout = System.currentTimeMillis() + timeoutMillis;
		while (System.currentTimeMillis() < timeout) {
			try {
				while (System.in.available() > 0) {
					System.in.read();
					isTimeOut = false;
				}
				if (!isTimeOut) {
					break;
				}
			} catch (IOException e) {

			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {

			}
		}
		return isTimeOut;
	}

	/**
	 * 休眠时间
	 */
	public static void sleep(int ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 启动GT
	 * 
	 * @param e
	 */
	public static boolean adbStartGT() {
		//adb shell am start -W -n com.tencent.wstt.gt/com.tencent.wstt.gt.activity.GTMainActivity
		ProcessBuilder pb1 = new ProcessBuilder("am", "start", "-W","-n", "com.tencent.wstt.gt/com.tencent.wstt.gt.activity.GTMainActivity");
		StringBuffer result = new StringBuffer();
		boolean isexists = false;;
			try {
				Process process =pb1.start();
			    Scanner scanner = new Scanner(process.getInputStream());
				while (scanner.hasNextLine()) {
				 result.append(scanner.nextLine());
				}
				scanner.close();
				if((!result.toString().contains("error: device not found")) && result.toString().contains("ok"))
				{
					isexists=true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return isexists;
	}

	//点击HOME键
	public static void adbHome() {
		//adb shell input keyevent KEYCODE_HOME
		ProcessBuilder pb1 = new ProcessBuilder("input", "keyevent","KEYCODE_HOME");
		try {
			pb1.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	//点击HOME键
	public static void adbBack() {
		//adb shell input keyevent KEYCODE_BACK
		ProcessBuilder pb1 = new ProcessBuilder("input", "keyevent","KEYCODE_BACK");
		try {
			pb1.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * <br>使gt可以采集该应用的性能信息；pkgName是包名；verName是版本号（可选参数）
	 * <br>adb shell am broadcast -a com.tencent.wstt.gt.baseCommand.startTest --es pkgName "cn.cj.pe" 
	 * 
	 */
	public static boolean adbGTAddPkg(String pkgName) {
		//使gt可以采集该应用的性能信息；pkgName是包名；verName是版本号（可选参数）
		//adb shell am broadcast -a com.tencent.wstt.gt.baseCommand.startTest --es pkgName "cn.cj.pe" 
		ProcessBuilder pb1 = new ProcessBuilder( "am", "broadcast","-a","com.tencent.wstt.gt.baseCommand.startTest","--es","pkgName",pkgName);
		StringBuffer result = new StringBuffer();
		boolean isexists = false;;
			try {
				Process process =pb1.start();
			    Scanner scanner = new Scanner(process.getInputStream());
				while (scanner.hasNextLine()) {
				 result.append(scanner.nextLine());
				}
				scanner.close();
				if((!result.toString().contains("error: device not found")) && result.toString().contains("completed"))
				{
					isexists=true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return isexists;
	}
	
	/**
	 * 
	 * @param name
	 * @param value
	 *	<p>adb shell am broadcast -a com.tencent.wstt.gt.baseCommand.sampleData --ei cpu 1        开启CPU采集
	 *	<br>adb shell am broadcast -a com.tencent.wstt.gt.baseCommand.sampleData --ei cpu 0        停止CPU采集
	 *	<br>adb shell am broadcast -a com.tencent.wstt.gt.baseCommand.sampleData --ei pri 1        开启PrivateDirty采集
	 *	<br>adb shell am broadcast -a com.tencent.wstt.gt.baseCommand.sampleData --ei pri 0        停止PrivateDirty采集
	 */
	public static boolean adbGTbaseCommand(String name, String value) {
		ProcessBuilder pb1 = new ProcessBuilder("am", "broadcast","-a","com.tencent.wstt.gt.baseCommand.sampleData","--ei",name,value);
		StringBuffer result = new StringBuffer();
		boolean isexists = false;;
			try {
				Process process =pb1.start();
			    Scanner scanner = new Scanner(process.getInputStream());
				while (scanner.hasNextLine()) {
				 result.append(scanner.nextLine());
				}
				scanner.close();
				if((!result.toString().contains("error: device not found")) && result.toString().contains("completed"))
				{
					isexists=true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return isexists;
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * 	结束采集并保存，同时删除数据记录：
	 * 	adb shell am broadcast -a com.tencent.wstt.gt.baseCommand.endTest --es saveFolderName "/sdcard/GT/GW/cn.cj.pe/6.6.2"  --es desc "CPU"       
	 * 	saveFolderName为保存目录的名称，最长可以自定义三级目录，以"/"分割，此三级目录会保存在/sdcard/GT/GW/下，每次保存后，GT会把缓存的本次测试数据清空。
	 * 
	 */
	public static boolean adbGTSave(String path, String filename) {
		ProcessBuilder pb1 = new ProcessBuilder("am", "broadcast","-a","com.tencent.wstt.gt.baseCommand.endTest","--es", "saveFolderName",path,"--es","desc",filename);
		StringBuffer result = new StringBuffer();
		boolean isexists = false;;
			try {
				Process process =pb1.start();
			    Scanner scanner = new Scanner(process.getInputStream());
				while (scanner.hasNextLine()) {
				 result.append(scanner.nextLine());
				}
				scanner.close();
				if((!result.toString().contains("error: device not found")) && result.toString().contains("completed"))
				{
					isexists=true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return isexists;
	}
	
	/**
	 * 关闭GT
	 * @param path
	 * @param filename
	 */
	public static boolean adbGTExit() {
		ProcessBuilder pb1 = new ProcessBuilder("am", "broadcast","-a","com.tencent.wstt.gt.baseCommand.exitGT");
		StringBuffer result = new StringBuffer();
		boolean isexists = false;;
			try {
				Process process =pb1.start();
			    Scanner scanner = new Scanner(process.getInputStream());
				while (scanner.hasNextLine()) {
				 result.append(scanner.nextLine());
				}
				scanner.close();
				if((!result.toString().contains("error: device not found")) && result.toString().contains("completed"))
				{
					isexists=true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return isexists;
	}
	

	/**
	 * 清除缓存
	 */
	public static void adbClear() {
		ProcessBuilder pb1 = new ProcessBuilder("pm", "clear","com.tencent.wstt.gt");
		try {
			pb1.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 从终端拉去文件
	 */
	public static boolean adbPull(String srcpath, String despath) {
		ProcessBuilder pb1 = new ProcessBuilder("cp", srcpath, despath);
		StringBuffer result = new StringBuffer();
		boolean isexists = false;
			try {
				Process process =pb1.start();
			    Scanner scanner = new Scanner(process.getInputStream());
				while (scanner.hasNextLine()) {
				 result.append(scanner.nextLine());
				 //System.out.println("scanner" + scanner);
				}
				//System.out.println("result" + result);
				scanner.close();
				if((!result.toString().contains("error: device not found")))
				{
					isexists=true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return isexists;
	}
	/**
	 * 文件是否存在
	 * @param path
	 * @param filename
	 * @return
	 */
    public static boolean adbFindFile(String path,String filename) {
			ProcessBuilder pb1 = new ProcessBuilder( "ls", path);
			//System.out.println("path:"+ path);
			StringBuffer result = new StringBuffer();
			boolean isexists = false;
				try {
					Process process =pb1.start();
				    Scanner scanner = new Scanner(process.getInputStream());
					while (scanner.hasNextLine()) {
					 result.append(scanner.nextLine());
					}
					//System.out.println("result:"+result);
					scanner.close();
					if((!result.toString().contains("No such file or directory")) && result.toString().contains(filename))
					{
						isexists=true;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return isexists;
    }
	
}
