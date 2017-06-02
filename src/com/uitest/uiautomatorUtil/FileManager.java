package com.uitest.uiautomatorUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.uitest.data.UserConfig;


/**
 * 保存文件，追加内容到文件
 * 
 * 日志记录：
 * 版本                  日期                         修改者    更新内容
 * 1.0       2016-06-04   cbh  模块重新整理
 * 1.1       2016-08-19   cbh  修改ROOT_PATH 全局变量
 * 
 */

public class FileManager {
	
	protected static final String ROOT_PATH=UserConfig.saveRootPath;
	
	/**
   * 新增或追加内容到文件中
   * 
   * @param fileName
   * @param content
   */
	public static void saveToFile(String FileName, String conent) {
		mkdir(ROOT_PATH);
		String Path = ROOT_PATH + FileName;
		BufferedWriter bw = null;
		try {
			/**
			 * 追加文件：使用FileOutputStream，在构造FileOutputStream时，把第二个参数设为true
			 */

			FileOutputStream fo = new FileOutputStream(Path, true);
			OutputStreamWriter ow = new OutputStreamWriter(fo);
			bw = new BufferedWriter(ow);
			bw.append(conent);
			bw.newLine();
			bw.flush();
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
  /**
   * 创建目录，非Root手机无法在 /data等目录下使用，需要手动或使用adb shell创建
   * @param path
   */
	public static void mkdir(String path) {
		File destDir = new File(path);
		if (!destDir.exists()) {
			// 创建文件夹
			System.out.println("mkdirs" + path);
			destDir.mkdirs();
		}
	}
	
	/**
	 * 向文件写入内容
	 * @param line
	 * @param file
	 */
	public static void saveFile(String line,File file){
		BufferedWriter bw=null;
		try {
			FileOutputStream fo=new FileOutputStream(file,true);
			OutputStreamWriter ow=new OutputStreamWriter(fo);
			bw=new BufferedWriter(ow);
            bw.append(line);
            bw.newLine();
            bw.flush();
            bw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
