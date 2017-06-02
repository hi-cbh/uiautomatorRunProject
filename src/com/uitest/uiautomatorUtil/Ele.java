package com.uitest.uiautomatorUtil;

import android.graphics.Rect;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.uitest.cmp.EditText;


/**
 * 用于页面元素管理，等待页面元素，判断页面元素是否存在、是否被显示等。
 * 
 * 日志记录： 版本 日期 修改者 更新内容 1.0 2017-05-16 cbh 代码重构
 * 
 */

public class Ele extends UiAutomatorTestCase{
	// 时间(ms)
	public static long MAXRUNTIME = 60000;
	public static long MIDRUNTIME = 45000;
	public static long MINRUNTIME = 10000;


	// ///////////////////////获取通过各种途径获取页面元素////////////////////////////

	/**
	 * 通过 固定的样式获取对象
	 * <br>By.ID = 1
	 * <br>By.Name = 2
	 * <br>By.CLASSNAME = 3
	 * <br>By.Description = 4
	 * 
	 * @param style
	 * @param text
	 * @return 
	 */
	public static UiObject getUiObject(int style, String text){
		UiObject uo = null;
		
		try{
			switch(style){
			case 1:
				uo = new UiObject(new UiSelector().resourceId(text));
				break;
			case 2:
				uo = new UiObject(new UiSelector().textContains(text));
				break;
			case 3:
				uo = new UiObject(new UiSelector().className(text));
				break;
			case 4:
				uo = new UiObject(new UiSelector().descriptionContains(text));
				break;
			}
			return uo;
		}catch(Exception e){
			System.out.println("can not find " + text + " !");
		}
		
		return uo;
	}
	
	/**
	 * 通过 固定的样式获取对象
	 * <br>By.ID = 1
	 * <br>By.Name = 2
	 * <br>By.CLASSNAME = 3
	 * <br>By.Description = 4
	 * 
	 * @param style
	 * @param text
	 * @return 
	 */
	public static UiObject getUiObject(int style, String text, int num){
		UiObject uo = null;
		
		try{
			switch(style){
			case 1:
				uo = new UiObject(new UiSelector().resourceId(text).index(num));
				break;
			case 2:
				uo = new UiObject(new UiSelector().textContains(text).index(num));
				break;
			case 3:
				uo = new UiObject(new UiSelector().className(text).index(num));
				break;
			case 4:
				uo = new UiObject(new UiSelector().descriptionContains(text).index(num));
				break;
			}
			return uo;
		}catch(Exception e){
			System.out.println("can not find " + text + " !");
		}
		
		return uo;
	}
	
	
	/**
	 * 通过 固定的样式获取对象，获取子集合的对象
	 * <br>By.ID = 1
	 * <br>By.Name = 2
	 * <br>By.CLASSNAME = 3
	 * <br>By.Description = 4
	 * 
	 * @param style
	 * @param text
	 * @return 
	 */
	public static UiObject getUiObject(int style, String text, int style2, String text2, int num){
		UiObject uo = null;
		
		uo = getUiObject(getCollection(style, text), style2, text2, num);
		
		return uo;
	}
	
	/**
	 * 通过 固定的样式获取对象
	 * <br>By.ID = 1
	 * <br>By.Name = 2
	 * <br>By.CLASSNAME = 3
	 * <br>By.Description = 4
	 * 
	 * @param style
	 * @param text
	 * @return 
	 */
	private static UiCollection getCollection(int style, String text){
		UiCollection collection = null;
		
		try{
			switch(style){
			case 1:
				collection = new UiCollection(new UiSelector().resourceId(text));
				break;
			case 2:
				collection = new UiCollection(new UiSelector().textContains(text));
				break;
			case 3:
				collection = new UiCollection(new UiSelector().className(text));
				break;
			case 4:
				collection = new UiCollection(new UiSelector().descriptionContains(text));
				break;
			}
			return collection;
		}catch(Exception e){
			System.out.println("can not find " + text + " !");
		}
		
		return collection;
	}
	
	/**
	 * 通过 固定的样式获取对象
	 * <br>By.ID = 1
	 * <br>By.Name = 2
	 * <br>By.CLASSNAME = 3
	 * <br>By.Description = 4
	 * @param uc 集合
	 * @param style
	 * @param text
	 * @return 
	 */
	private static UiObject getUiObject(UiCollection uc, int style, String text, int num){
		UiObject uo = null;
		try{
			switch(style){
			case 1:
				uo = uc.getChildByInstance(new UiSelector().resourceId(text), num);
				break;
			case 2:
				uo = uc.getChildByInstance(new UiSelector().textContains(text), num);
				break;
			case 3:
				uo = uc.getChildByInstance(new UiSelector().className(text), num);
				break;
			case 4:
				uo = uc.getChildByInstance(new UiSelector().descriptionContains(text), num);
				break;
			}
			return uo;
		}catch(Exception e){
			System.out.println("can not find " + text + " !");
		}
		
		return uo;
	}

	

	/**
	 * 需求：根据ClassName,在搜索该布局下的匹配name的第一个控件。
	 * 
	 * @param name
	 */
	public static UiObject getUiObjectSearchByClassNameAndName(
			String className, String name) {
		System.out.println("getUiObjectSearchByClassNameAndName: " + className);
		UiCollection collection = new UiCollection(new UiSelector().index(0));
		UiObject CheckObject = null;
		try {

			for (int i = 0; i < 10; i++) {
				CheckObject = collection.getChildByInstance(
						new UiSelector().className(className), i);

				if (CheckObject.getText().equals(name)) {
					return CheckObject;
				}
			}
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}
		return CheckObject;
	}

	/**
	 * 获取同一ClassName下，最后一个匹配id类型的对象
	 * 
	 * @param id
	 * @param destClass
	 * @return
	 */
	public static UiObject getEndClassObjectById(String id, String destClass) {
		UiCollection collection = new UiCollection(
				new UiSelector().resourceId(id));
		UiObject CheckObject = null;

		int num1 = collection.getChildCount(new UiSelector()
				.className(destClass));

		System.out.println("cnt: " + num1);
		try {
			if (num1 == 0) {
				return null;
			} else {
				CheckObject = collection.getChildByInstance(
						new UiSelector().className(destClass), num1 - 1);
			}
		} catch (UiObjectNotFoundException e) {

			e.printStackTrace();
		}
		return CheckObject;
	}

	/**
	 * 获取同一ClassName下，首个匹配id类型的对象
	 * 
	 * @param id
	 * @param destClass
	 * @return
	 */
	public static UiObject getStartClassObjectById(String id, String destClass) {
		UiCollection collection = new UiCollection(
				new UiSelector().resourceId(id));
		UiObject CheckObject = null;

		int num1 = collection.getChildCount(new UiSelector()
				.className(destClass));

		System.out.println("cnt: " + num1);
		try {
			if (num1 == 0) {
				return null;
			} else {
				CheckObject = collection.getChildByInstance(
						new UiSelector().className(destClass), 1);
			}
		} catch (UiObjectNotFoundException e) {

			e.printStackTrace();
		}
		return CheckObject;
	}

	/**
	 * 获取同行对象，可以设置上下的偏移量
	 * 
	 * @param srcObject
	 * @param destClass
	 * @param upOffset
	 * @param dowmOffset
	 * @return
	 */
	public static UiObject getSameLineObject(UiObject srcObject,
			String destClass, int upOffset, int dowmOffset) {
		Rect r1;
		UiCollection collection = new UiCollection(new UiSelector().index(0));
		UiObject CheckObject = null;
		try {
			r1 = srcObject.getBounds();
			int y0 = r1.top + upOffset;
			int y1 = r1.bottom + dowmOffset;
			for (int i = 0; i < 10; i++) {
				CheckObject = collection.getChildByInstance(
						new UiSelector().className(destClass), i);
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

	/**
	 * 获取同行对象，可以设置上下的偏移量
	 * 
	 * @param srcObject
	 * @param destClass
	 * @param upOffset
	 * @param dowmOffset
	 * @return
	 */
	public static UiObject getSameLineObjectById(UiObject srcObject, String id,
			int upOffset, int dowmOffset) {
		Rect r1;
		UiCollection collection = new UiCollection(new UiSelector().index(0));
		UiObject CheckObject = null;
		try {
			r1 = srcObject.getBounds();
			System.out.println("srcObject: "+r1);
			int y0 = r1.top + upOffset;
			int y1 = r1.bottom + dowmOffset;
			for (int i = 0; i < 5; i++) {
				CheckObject = collection.getChildByInstance(
						new UiSelector().resourceId(id), i);
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


	/**
	 * 获取根据父rootclas下，匹配id类型的控件数量
	 * 
	 * @param className
	 * @return
	 */
	public static int getChildCountByClassId(String rootClass, String id) {
		UiCollection collection = new UiCollection(
				new UiSelector().classNameMatches(".*" + rootClass));
		int cnt = collection.getChildCount(new UiSelector()
				.resourceIdMatches(".*" + id));
		return cnt;
	}

	/**
	 * 获取根据父Id下，匹配className类型的控件数量
	 * 
	 * @param className
	 * @return
	 */
	public static int getChildCountByIdClass(String id, String rootClass) {
		UiCollection collection = new UiCollection(new UiSelector().resourceIdMatches(".*" + id));
		int cnt = collection.getChildCount(new UiSelector().classNameMatches(".*" + rootClass));
		return cnt;
	}
	
	
	/**
	 * 获取根据父rootclas下，匹配subclass类型的控件数量
	 * 
	 * @param className
	 * @return
	 */
	public static int getChildCountByClassClass(String rootClass,
			String subClass) {
		UiCollection collection = new UiCollection(
				new UiSelector().classNameMatches(".*" + rootClass));
		int cnt = collection.getChildCount(new UiSelector()
				.classNameMatches(".*" + subClass));
		return cnt;
	}

	/**
	 * 获取屏幕中，匹配classname类型，第num个的控件
	 * 
	 * @param className
	 * @param num
	 * @return
	 */
	public static UiObject getAllViewByClassName(String className, int num) {
		UiCollection collection = new UiCollection(new UiSelector().index(0));
		try {
			return collection.getChildByInstance(
					new UiSelector().className(className), num);
		} catch (UiObjectNotFoundException e) {

			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 滑动列表，直到匹配到text为止，获取对象
	 * 
	 * @param text
	 * @return
	 */
	public static UiObject getUiObjectScrollListViewByText(String classname,
			String text) {
		UiScrollable scroll = new UiScrollable(
				new UiSelector().classNameMatches(".*" + classname));
		UiObject object = null;
		try {
			scroll.scrollTextIntoView(text);
			object = new UiObject(new UiSelector().text(text));
		} catch (UiObjectNotFoundException e) {

			e.printStackTrace();
		}
		return object;
	}

	// 等待操作----------------------------------

	/**
	 * 等待元素出现
	 * @param style By类型
	 * @param text  元素类型
	 * @param time  秒
	 * @return
	 */
	public static UiObject waitForExistst(int style, String text, int time) {
		
		UiObject uo = getUiObject(style, text);
		int outtime = time * 1000;
		if (uo.waitForExists(outtime)) {
			return uo;
		} else {
			System.out.println("can not find " + text + " !");
			return null;
		}
	}
	
	/**
	 * 等待元素出现
	 * @param style By类型
	 * @param text  元素类型
	 * @param  默认 等待10秒
	 * @return
	 */
	public static UiObject waitForExistst(int style, String text) {
		try {
			System.out.println("waitForExistst: " + text);
			UiObject uo = getUiObject(style, text);
			int outtime = 10 * 1000;
			if (uo.waitForExists(outtime)) {
				return uo;
			} else {
				System.out.println("can not find " + text + " !");
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 等待资源ID消失
	 */
	public static boolean waitUntilGoneById(int style, String text, int time) {
		System.out.println("waitUntilGone: " + text);
		UiObject uo = getUiObject(style, text);
		int outtime = time * 1000;
		if (uo.waitUntilGone(outtime)) {
			return true;
		} else {
			return false;
		}
	}

	// /点击操作-------------------------------------

	
	
	/**
	 * 通过坐标点击
	 * 
	 * @param x
	 * @param y
	 */
	public static void click(int x, int y) {
		UiDevice.getInstance().click(x, y);
	}




	/**
	 * 
	 * @param uo
	 */
	public static void clickLongByUiObject(UiObject uo) {
		System.out.println("start long click");
		if (!uo.exists()) {
			System.out.println("clickLongByUiObject null");
			return;
		}
		myLongClick(uo, 100);
		System.out.println("end long click");
	}

	/**
	 * 通过元素坐标，点击控件
	 * 
	 * @param uo
	 * @param step
	 */
	private static void myLongClick(UiObject uo, int step) {
		Rect buttonRect;
		try {
			buttonRect = uo.getBounds();
			// 滑动同一点
			UiDevice.getInstance().swipe(buttonRect.centerX(),
					buttonRect.centerY(), buttonRect.centerX(),
					buttonRect.centerY(), step);
		} catch (UiObjectNotFoundException e) {

			e.printStackTrace();
		}

	}



	/**
	 * 需求：根据ClassName和资源name点击Uiobject
	 */
	public static void clickSameTextByClassAndName(String className, String name) {
		try {
			getUiObjectSearchByClassNameAndName(className, name).click();
		} catch (UiObjectNotFoundException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 滑动列表，并点击匹配txt的控件
	 * 
	 * @param classname
	 * @param text
	 */
	public static void clickWithScrollList(String classname, String text) {
		try {
			getUiObjectScrollListViewByText(classname, text).click();
		} catch (UiObjectNotFoundException e) {

			e.printStackTrace();
		}
	}


	/**
	 * 点击屏幕中间
	 */
	public static void clickPageCenter() {
		int width = UiDevice.getInstance().getDisplayWidth();
		int height = UiDevice.getInstance().getDisplayHeight();
		UiDevice.getInstance().click(width / 2, height / 2);
	}

	// 输入操作--------------------------------------

	/**
	 * 获取对象，输入
	 * 
	 * @param uo
	 * @param content
	 */
	public static void inputText(int style, String text, String content) {
		UiObject uo = getUiObject(style, text);
		if (uo == null) {
			return;
		}
		EditText.clearEditText(uo);
		EditText.setText(uo, content);
	}


	/**
	 * 需求：根据坐标点击后，输入内容
	 * 
	 * @param name
	 * @param content
	 */
	public static void inputTextByPoint(int style, String text, String content) {
		System.out.println("[start] inputTextByName: " + text + " , input: "
				+ content);
		UiObject uo = getUiObject(style, text);
		if (!uo.exists()) {
			System.out.println("[end] can not find: " + text);
			return;
		}

		Rect ct;
		try {
			ct = uo.getBounds();
			click(ct.top, ct.top);
			EditText.setText(uo, content);
			System.out.println("[end] inputTextByName: " + text + " , input: "
					+ content);
		} catch (UiObjectNotFoundException e) {

			e.printStackTrace();
		}
	}

}
