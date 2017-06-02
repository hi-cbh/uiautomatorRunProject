package com.gt.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 查找本地文件，和读取csv文件
 * @author Administrator
 *
 */
public class GetCSVData {

	/**
	 * 查找本地文件
	 * 
	 * @param path
	 * @param fname
	 * @return
	 */
	public static String getFileName(String path, String fname) {
		File file = new File(path);
		String[] fileName = file.list();
		for (String name : fileName) {
			if (name.contains(fname)) {
				System.out.println(name);
				return name;
			}
		}
		//System.out.println("return null");
		return null;
	}

	
	/**
	 * 查找本地文件，使用通配符
	 * 
	 * @param path
	 * @param pkg 包名
	 * @param type 获取类型，mem为内存，cpu为CPU
	 * @return
	 */
	public static String getFileName(String path, String pkg, String type) {
		
		if(pkg.equals("")){
			System.out.println("包名不能为空");
			return null;
		}
		
		String p = "";
		if(type.equals("cpu")){
			p = "Pcp[0-5]_"+pkg+"_([0-9])+.csv";
		}
		else if(type.equals("mem")){
			p = "Pr[0-5]_"+pkg+"_([0-9])+.csv";
		}
		
		File file = new File(path);
		System.out.println("path:" + path);
		String[] fileName = file.list();
		System.out.println("fileList: " + fileName.length);
		
		
		Pattern pattern = Pattern.compile(p);  
	    Matcher matcher = null;
	    
		//System.out.println("start");
		for (String name : fileName) {
			//System.out.println("current: " + name);
			matcher = pattern.matcher(name); 
			if(matcher.matches()){
				System.out.println("find:"+name);
				return name;
			}
		}
		//System.out.println("return null");
		return null;
	}
	

	public String getData(int row, int col, String path, String filename) {

		try {
				
			BufferedReader reader = new BufferedReader(
					new FileReader(
							path+filename));// 换成你的文件名
			reader.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
			String line = null;
			int index = 0;
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(" ");// CSV格式文件为逗号分隔符文件，这里根据逗号切分
				if (index == row - 1) {
					if (item.length >= col - 1) {
						String last = item[col - 1];// 这就是你要的数据了
						//System.out.println(last);
						return last;
					}
				}
				// int value = Integer.parseInt(last);//如果是数值，可以转化为数值
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * 返回主线程内存最大、最小、平均值
	 * @param path
	 * @return
	 */
	public String getMEMValue(String path,String pkg){
		String filename = getFileName(path,pkg,"mem");


		String data1 = getData(8,1,path,filename);
		String data2 = getData(9,1,path,filename);
		String data3 = getData(10,1,path,filename);
		
		System.out.println("" + data1);
		System.out.println("" + data2);
		System.out.println("" + data3);
		
		//从第四个字符开始搜索
		
		String d1 = data1.substring(0, data1.indexOf(",", 4));
		String d2 = data2.substring(0, data2.indexOf(",", 4));
		String d3 = data3.substring(0, data3.indexOf(",", 4));

		return d1+ "#" +d2+ "#" +d3;
	}
	
	/**
	 * 获取CPU最大、最小、平均
	 * @param path
	 * @return
	 */
	public String getCPUValue(String path, String pkg){
		String filename = getFileName(path,pkg,"cpu");

		String datas1 = getData(7,1,path,filename);
		String datas2 = getData(8,1,path,filename);
		String datas3 = getData(9,1,path,filename);
		
		System.out.println("" + datas1);
		System.out.println("" + datas2);
		System.out.println("" + datas3);
		
		return datas1 + "#" + datas2 + "#" + datas3;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GetCSVData test = new GetCSVData();
//		//test.test(8, 1);
//		test.getData();
		
		String path = "D:\\workspace\\workspace_work\\EmailTestV1.2\\logs\\新建文件夹\\2017-04-22_11-56-30\\";
		
		test.getMEMValue(path,"cn.cj.pe");
		test.getCPUValue(path,"cn.cj.pe");
//		test.getMEMMaxValue(path);
	}
}