package com.uitest.data;

public class UserConfig {

	//运行前需要提前手动创建一下目录，否则运行错误(部分android手机没权限创建目录)
	public static String saveRootPath = "/mnt/sdcard/AppTestReport/";    //运行记录及app日志
	public static String savePicPath = "/mnt/sdcard/AppTestReportPic/"; //运行错误时截图
	public static String saveLogPath = "/mnt/sdcard/AppTestReportLog/"; //系统运行日志
	public static String saveAppLogPath = "/mnt/sdcard/AppTestReportAppRunLog/";
	public static String readXmlPath = "/mnt/sdcard/testdata.xml";      //读取数据文件
	
	/**
	 * 参数：VERBOSE、DEBUG、INFO、WARN、ERROR 对应的等级 1 2 3 4 5；
	 * 
	 *  Verbose，啰嗦模式，最低级别的信息，不过滤地输出所有调试信息，包括VERBOSE、DEBUG、INFO、WARN、ERROR级别
	 * 
	 *  Debug，调试模式，一些调试信息通过该模式输出，输出信息包括输出DEBUG、INFO、WARN、ERROR级别
	 * 
	 *  Info，信息模式，输出信息包括输出INFO、WARN、ERROR级别。
	 *
	 *  Warn，警告模式，输出信息包括输出WARN、ERROR级别。
	 * 
	 *  Error，错误模式，
	 *  
	 */
	private static int VERBOSE = 1; 
	private static int DEBUG = 2; 
	private static int INFO = 3; 
	private static int WARN = 4; 
	private static int ERROR = 5; 
	
	public static int LOGCAT= WARN;
	
	//全局参数
	public static boolean isWriteLog = true; //是否写入app运行日志，默认为关闭
	public static boolean isWritePic = false; //是否记录运行步骤中的图片
	
	//登录账号
	public static String LoginName = "13427665104";
	public static String LoginPwd = "yscs12345";

	//根据每台PC的不同，进行调整
	public static String androidId = "2";
	public static String packageName = "com.chinamobile.contacts.im";
	
	
}
