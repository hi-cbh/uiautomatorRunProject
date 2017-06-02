package com.gt.util;

import com.gt.model.GTAction;

public class TestDemo {

	public static void main(String[] args) {
		String appPackage = "com.netease.mail"; // 程序的package
		String appPackage2 = "com.tencent.androidqqmail";
		 String appPackage3 = "com.corp21cn.mail189";
		 String app139 = "cn.cj.pe";
		GTTest gt = new GTTest(appPackage3);
		
		
		for(int i=0; i<1; i++){
			GTAction.execute(app139, 10);
			
//			gt.startGT();
//			
//			//CommonUtil.sleep(5000);
//			for(int j=0; j<20;j++){
//				System.out.println("等待" + j + "分钟");
//				CommonUtil.sleep(1000);
//			}
//			
//			
//			gt.endGT();
//			CommonUtil.sleep(5000);
			
		}

		
		
		
	}

}
