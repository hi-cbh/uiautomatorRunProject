package com.gt.util;


public class StartTest {

	public static void main(String[] args) {
		String app163 = "com.netease.mail"; // 程序的package
		String appqq = "com.tencent.androidqqmail";
		String app189 = "com.corp21cn.mail189";
		String app139 = "cn.cj.pe";
		
		GTTest gt = new GTTest(app139);
		
		gt.startGT();

	}

}
