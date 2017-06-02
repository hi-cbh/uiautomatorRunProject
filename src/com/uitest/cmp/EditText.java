package com.uitest.cmp;

import jp.jun_nama.test.utf7ime.helper.Utf7ImeHelper;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;



/**
 * 用于EditText元素管理，清空输入框、输入内容
 * 
 * 日志记录：
 * 版本                  日期                         修改者    更新内容
 * 1.0       2016-06-21   cbh  模块重新整理
 * 
 */
public class EditText  {
	
	/**
	 * 需求：根据UiObject对象，清空文本内容
	 * @param uo
	 */
	public static void clearEditText(UiObject uo){
		int len;
		try {
			if(!uo.exists()){
				System.out.println("clearEditText: 对象不存在");
				return;
			}
			//点击控件，获取焦点
			uo.click();
			
			len = uo.getText().length();
			System.out.println("text len: " + len);
			if (len == 0) {
				return;
			} 
			else {
				UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_MOVE_END);
				for(int i = 0; i<len; i++){
					//System.out.println("del");
					UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_DEL);
				}
			}
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void setText(UiObject uo, String content){
		try {
			if(!uo.exists()){
				System.out.println("setText: 对象不存在");
				return;
			}
			
			//uo.setText(content);
			//开启和设置为默认输入法
			uo.setText(Utf7ImeHelper.e(content));
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}
	}

	

}
