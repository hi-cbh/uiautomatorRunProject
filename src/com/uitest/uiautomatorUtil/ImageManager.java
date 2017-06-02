package com.uitest.uiautomatorUtil;

import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import com.android.uiautomator.core.UiDevice;
import com.uitest.data.UserConfig;

/**
 * 保存图片，截图等功能
 * 
 * 日志记录： 版本 日期 修改者 更新内容 1.0 2016-06-04 cbh 模块重新整理 1.1 2016-08-12 cbh 添加截图和加水印的方法
 * 
 */

public class ImageManager {

	// ///////////////图片////////

	public static int getColorPicel(int x, int y) {
		String path = "/mnt/sdcard/testcolor.png";
		takeScreenshot(path);
		Bitmap m = BitmapFactory.decodeFile(path);
		int color = m.getPixel(x, y);
		return color;
	}

	/**
	 * 需求：截取一张图片后，另外为
	 * 
	 * @param rect
	 * @param path
	 */
	public static void cutBitmap(Rect rect, String path, String cutImg) {
		Bitmap m = BitmapFactory.decodeFile(path);
		m = Bitmap.createBitmap(m, rect.left, rect.top, rect.width(),
				rect.height());
		saveBitMapToSdcard(m, cutImg);
	}

	// 重命名图片，保存
	public static void saveBitMapToSdcard(Bitmap bitmap, String newName) {
		FileOutputStream out = null;
		try {

			out = new FileOutputStream(UserConfig.savePicPath + newName);
			if (out != null) {
				bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
				out.close();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	/**
	 * 图片压缩，重命名，保存
	 * @param srcPath
	 * @param newPath
	 */
	public static void CompressToSdcard(String  srcPath, String newPath) {
		FileOutputStream out = null;
		
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath);
		try {

			out = new FileOutputStream(newPath);
			if (out != null) {
				bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
				out.close();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 调用Uidevice的方法 截屏获取图片
	 * 
	 * @param path
	 *            文件保存路径
	 * @return 是否截图成功
	 */
	public static boolean takeScreenshot(String path) {
		// 创建文件
		File file = new File(path);
		// 截图图片，并返回是否成功
		return UiDevice.getInstance().takeScreenshot(file);
	}

	/**
	 * 截图并添加水印
	 * 
	 * @param testCasename
	 *            用例名称
	 * @param text
	 *            写入的内容
	 */
	public static void snapshot(String testCasename, String text) {

		System.out.println("snapshot");
		// 获取当前工作路径
		String currentPath = UserConfig.savePicPath;

		// 临时文件
		String tmpPic = "tmpPic.jpg";
		// 文件名
		String filename = testCasename + "_"
				+ TimeUtil.getCurrentSysTimeUnsigned() + ".jpg";
		// System.out.println("file name: " + filename);
		screenshotAndDrawText(currentPath + tmpPic, filename, text);

	}

	/**
	 * 截图
	 * 
	 * @param saveFile
	 *            存储路径
	 * @param saveName
	 *            存储的文件名
	 */
	public static void takesnapshot(String saveFile, String saveName) {

		System.out.println("snapshot");
		
		// 获取当前工作路径,临时文件
		String currentPath = saveFile + "tmpPic.jpg";

		// 存储路径，文件名
		String filename = saveFile + saveName + ".jpg";
		
		//创建图片
		new File(currentPath);
		new File(filename);
		
		//截图
		takeScreenshot(currentPath);
		
		//压缩，另存为
		CompressToSdcard(currentPath, filename);
		

	}
	
	/**
	 * 截屏，添加水印
	 * 
	 * @param path      截屏保存路径
	 * @param imageName 图片保存名称
	 * @param text
	 */
	public static void screenshotAndDrawText(String path, String imageName,
			String text) {
		File file = new File(path);
		// System.out.println("path: " + path);
		// 截图
		UiDevice.getInstance().takeScreenshot(file);
		// UiDevice.getInstance().takeScreenshot(file, 0.1f, 1);
		// 转为bitmap类型
		Bitmap bitmap = BitmapFactory.decodeFile(path);
		// 添加水印
		Bitmap drawBitmap = drawTextBitmap(bitmap, text);
		// 重命名图片，保存
		saveBitMapToSdcard(drawBitmap, imageName);
	}

	// ///////////////画矩形
	/**
	 * 截图，并在图片上标记
	 * 
	 * @param testCasename
	 *            用例
	 * @param r
	 *            标记的位置
	 */
	public static void snapshotAndDrawRect(String testCasename, Rect r) {

		if(!UserConfig.isWritePic){
			return;
		}
		
		System.out.println("snapshot");
		// 获取当前工作路径
		String currentPath = UserConfig.savePicPath;

		// 临时文件
		String tmpPic = "tmpPic.jpg";
		// 文件名
		String filename = testCasename + "_"
				+ TimeUtil.getCurrentSysTimeUnsigned() + ".jpg";
		// System.out.println("file name: " + filename);
		screenshotAndDrawRect(currentPath + tmpPic, filename, r);

	}

	/**
	 * 实现截图方法
	 * 
	 * @param path
	 * @param imageName
	 * @param r
	 */
	private static void screenshotAndDrawRect(String path, String imageName,
			Rect r) {
		File file = new File(path);
		// System.out.println("path: " + path);
		// 截图
		UiDevice.getInstance().takeScreenshot(file);

		// 转为bitmap类型
		Bitmap bitmap = BitmapFactory.decodeFile(path);
		// 添加水印
		Bitmap drawBitmap = MydrawRect(bitmap, r);
		// 重命名图片，保存
		saveBitMapToSdcard(drawBitmap, imageName);
	}

	/**
	 * 画矩形
	 * 
	 * @param bitmap
	 *            原图
	 * @param r
	 *            范围
	 * @return 返回修改后图片
	 */
	private static Bitmap MydrawRect(Bitmap bitmap, Rect r) {
		// 获取位图宽高
		int x = bitmap.getWidth();
		int y = bitmap.getHeight();

		// 创建一个与原来图片大小一致的位图
		Bitmap newBitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);

		// 创建画板
		Canvas canvans = new Canvas(newBitmap);

		// 画笔
		Paint paint = new Paint();
		// 透明度
		paint.setAlpha(100);
		// 防止边缘锯齿
		paint.setAntiAlias(true);

		// 画笔颜色
		paint.setColor(Color.parseColor("#FF0000"));
		// 画笔粗细
		paint.setStrokeWidth(2);
		// 文字大小
		paint.setTextSize(40);
		// 设置画笔为空心
		paint.setStyle(Style.STROKE);

		// 在原图位置（0，0）叠加一张图片
		canvans.drawBitmap(bitmap, 0, 0, null);

		// 画矩形
		canvans.drawRect(r, paint);

		// 保存画板修改
		canvans.save(Canvas.ALL_SAVE_FLAG);
		// 存储
		canvans.restore();
		return newBitmap;
	}

	/**
	 * 在图片的底部，添加水印
	 * 
	 * @param bitmap
	 * @param text
	 * @return
	 */
	private static Bitmap drawTextBitmap_bottom(Bitmap bitmap, String text) {
		int x = bitmap.getWidth();
		int y = bitmap.getHeight();

		// 创建一个比原来图片更大的位图
		Bitmap newBitmap = Bitmap.createBitmap(x, y + 80,
				Bitmap.Config.ARGB_8888);
		Canvas canvans = new Canvas(newBitmap);
		Paint paint = new Paint();
		// 在原图位置（0，0）叠加一张图片
		canvans.drawBitmap(bitmap, 0, 0, paint);
		// 画笔颜色
		paint.setColor(Color.parseColor("#FF0000"));
		paint.setTextSize(40);
		canvans.drawText(text, 20, y + 55, paint);
		canvans.save(Canvas.ALL_SAVE_FLAG);
		canvans.restore();
		return newBitmap;
	}

	/**
	 * 添加水印
	 * 
	 * @param bitmap
	 * @param text
	 * @return
	 */
	private static Bitmap drawTextBitmap(Bitmap bitmap, String text) {
		int x = bitmap.getWidth();
		int y = bitmap.getHeight();

		// 创建一个比原来图片更大的位图
		Bitmap newBitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
		Canvas canvans = new Canvas(newBitmap);
		Paint paint = new Paint();
		// 透明度
		paint.setAlpha(100);
		// 防止边缘锯齿
		paint.setAntiAlias(true);

		// 在原图位置（0，0）叠加一张图片
		canvans.drawBitmap(bitmap, 0, 0, null);
		// 画笔颜色
		paint.setColor(Color.parseColor("#FF0000"));
		paint.setTextSize(80);
		canvans.drawText(text, 20, 600, paint);
		canvans.save(Canvas.ALL_SAVE_FLAG);
		canvans.restore();
		return newBitmap;
	}

	/**
	 * 图片对比
	 * 
	 * @param targetImagePath
	 *            目标路径
	 * @param comPath
	 *            对比文件路径
	 * @param percent
	 *            相似度
	 * @return
	 */
	public boolean imageSameAs(String targetImagePath, String comPath,
			double percent) {
		try {
			Bitmap m1 = BitmapFactory.decodeFile(targetImagePath);
			Bitmap m2 = BitmapFactory.decodeFile(comPath);

			int width = m2.getWidth();
			int height = m2.getHeight();
			int numDiffPixels = 0;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					if (m2.getPixel(x, y) != m1.getPixel(x, y)) {
						numDiffPixels++;
					}
				}
			}

			double totalPicels = height * width;
			double diffPercent = numDiffPixels / totalPicels;

			return percent <= 1.0 - diffPercent;
		} catch (Exception e) {
		}

		return false;
	}

}
