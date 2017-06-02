package com.uitest.uiautomatorUtil;


import junit.framework.Assert;

import com.uitest.log.UiautomatorAssistant;

/**
 * 用于判断是否通过验证，截图图片并添加说明。
 * 
 * 日志记录：
 * 版本                  日期                         修改者    更新内容
 * 1.0       2016-08-12   cbh  新增Myassert方法
 * 1.1       2016-08-16   cbh  修改Myassert方法，
 * 调用Assert.assertEquals（被junt中的TestListener捕获）
 * 1.2       2016-08-16   cbh  添加运行日志
 */
public class AssertUtil{
	/**
	 * 判断是否通过验证，如果不通过结束并截图,并添加具体问题描述
	 * 
	 * @param message  //添加在图片上的描述
	 * @param bl       //预期结果
	 * @param caseName //运行用例（图片名字）
	 */
	public static void Myassert(String message, boolean bl, String caseName) {

		//bl为false
		if (bl == false) {
			//写入app运行日志
			UiautomatorAssistant.UiAutomationLog(caseName+" 验证失败: "+message);
			//被TestListener捕获，已经写有截图工具
			Assert.assertEquals(message, false, true);
		}
		//bl为true
		Assert.assertEquals(message, true, true);
		UiautomatorAssistant.UiAutomationLog(caseName+" 验证通过");
	}
	

	
	
	
}
