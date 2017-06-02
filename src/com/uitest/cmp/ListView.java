package com.uitest.cmp;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

public class ListView {
	
	/**
	 * 需求：输入类名，搜索的文件，返回对象
	 * @param ClassName
	 * @param text
	 * @return
	 */
	public UiObject getUiObjectScrollList(String ClassName, String text){
		UiScrollable scroll=new UiScrollable(new UiSelector().className(ClassName));
		UiObject object = null;
		try {
			scroll.scrollTextIntoView(text);
			object=new UiObject(new UiSelector().text(text));
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
}
