package com.uitest.cmp;


import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;


import android.graphics.Rect;

public class Layout{
	/**
	 * 获取同行对象，可以设置上下的偏移量
	 * @param srcObject
	 * @param destClass
	 * @param upOffset
	 * @param dowmOffset
	 * @return
	 */
	public static UiObject getSameLineObject(UiObject srcObject, String destClass,
			int upOffset, int dowmOffset) {
		Rect r1;
		UiCollection collection = new UiCollection(new UiSelector().index(0));
		UiObject CheckObject = null;
		try {
			r1 = srcObject.getBounds();
			int y0 = r1.top+upOffset;
			int y1 = r1.bottom + dowmOffset;
			for (int i = 0; i < 10; i++) {
				CheckObject = collection.getChildByInstance(new UiSelector().
						className(destClass), i);
				Rect rect = CheckObject.getBounds();
				int centy = rect.centerY();
				if (centy > y0 && centy < y1) {
					return CheckObject;
				}
			}
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}
		return CheckObject;
	}
	
	
	//1.根据输入的对象或id,获取同行相同类型的对象
	//2.获取同行，同一类型的数量，根据输入的num,返回相应的对象
	
	/**
	 * 同行，根据num，获取按顺序的对象
	 * @param srcObject
	 * @param destClass
	 * @param upOffset
	 * @param dowmOffset
	 * @return
	 */
	public static UiObject getSameLineAndSameClassObject(String rootClass, String destClass, int num) {
		UiCollection collection = new UiCollection(new UiSelector().className(rootClass));
		UiObject CheckObject = null;

		int num1 = collection.getChildCount(new UiSelector().className(destClass));

		System.out.println("cnt: " + num1);
		try {
			if (num > num1) {
				return CheckObject;
			} else {
				CheckObject = collection.getChildByInstance(
						new UiSelector().className(destClass), num);
			}
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CheckObject;
	}
	
	
	
}
