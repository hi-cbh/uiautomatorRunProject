package com.uitest.uiautomatorUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.ParseException;


/*
 * 用于获取当前时间，计算时间差
 * 
 * 日志记录：
 * 版本                  日期                         修改者    更新内容
 * 1.0       2016-06-04   cbh  模块重新整理
 * 
 */
public class TimeUtil {

	/**
	 * 获取当前系统时间（常用）
	 * @return
	 */
	public static String getCurrentSysTime() {
		SimpleDateFormat formattime = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss:SSS");
		//long ctime = System.currentTimeMillis();
		long ctime = System.nanoTime()/1000000L;
		String currenttime = formattime.format(new Date(ctime));
		return currenttime;
	}
	
	/**
	 * 获取当前系统时间（以2015开始），用于保存到日志里
	 * @return
	 */
	public static String getCurrentSysTimeRunTime() {
		//SimpleDateFormat formattime1 = new SimpleDateFormat(
		//		"yyyy-MM-dd HH:mm:ss:SSS");
		SimpleDateFormat formattime1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		long ctime = System.currentTimeMillis();
		//long ctime = System.nanoTime()/1000000L;
		String currenttime = formattime1.format(new Date(ctime));
		return currenttime;
	}
	
	/**
	 * 获取已1970开始的时间（创建文件为主）
	 * @return
	 */
	public static String getCurrentSysSSSTime() {
		SimpleDateFormat formattime1 = new SimpleDateFormat(
				"yyyy.MM.dd_HH-mm-ss-SSS");

		long ctime = System.nanoTime()/1000000L;
		String currenttime = formattime1.format(new Date(ctime));
		return currenttime;

	}
	
	/**
	 * 获取时间，无格式
	 * @return
	 */
	public  static String getCurrentSysTimeUnsigned() {
		SimpleDateFormat formattime1 = new SimpleDateFormat(
				"yyyyMMdd_HHmmss");
		long ctime = System.currentTimeMillis();
		String currenttime = formattime1.format(new Date(ctime));
		return currenttime;
	}
	
	
	/**
	 * 获取当前系统时间（常用）
	 * @return
	 */
	public static  String getCurrentSysTimeLog() {
		SimpleDateFormat formattime = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss:SSS");
		long ctime = System.currentTimeMillis();
		//long ctime = System.nanoTime()/1000000L;
		String currenttime = formattime.format(new Date(ctime));
		return currenttime;
	}
	
	/**
	 * 获取两个时间差
	 * @param time1  //结束时间
	 * @param time2  //开始时间
	 * @return       //时间差
	 * @throws ParseException
	 * @throws java.text.ParseException
	 */
	public  static long getTimeDistance(String time1, String time2)
			throws ParseException, java.text.ParseException {
		SimpleDateFormat formattime1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss:SSS");
		Date t1 = formattime1.parse(time1);
		Date t2 = formattime1.parse(time2);
		long d = t1.getTime() - t2.getTime();
		return d;

	}
	

}
