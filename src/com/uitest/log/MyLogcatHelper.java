package com.uitest.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.uitest.data.UserConfig;


/**
 * 系统运行日志截取（logcat）
 * 
 * 
 * 日志记录：
 * 版本                  日期                                   修改者            更新内容
 * 1.0       2016-08-19   cbh     创建并修改
 * 
 */
public class MyLogcatHelper {

	
	private static String PATH_LOGCAT;
	private LogDumper mLogDumper = null;
	private int mPId;

	public MyLogcatHelper(String path) {
		PATH_LOGCAT = path;
		mPId = android.os.Process.myPid();
		System.out.println("run PId: "+mPId);
	}

	public void start() {
		if (mLogDumper == null)
			mLogDumper = new LogDumper(String.valueOf(mPId));
		mLogDumper.start();
	}

	public void stop() {
		if (mLogDumper != null) {
			mLogDumper.stopLogs();
			mLogDumper = null;
		}
	}

	private class LogDumper extends Thread {

		private Process logcatProc;
		private BufferedReader mReader = null;
		private boolean mRunning = true;
		String cmds = null;
		private String mPID;
		private FileOutputStream out = null;

		public LogDumper(String pid) {
			mPID = pid;
			try {
				//out = new FileOutputStream(new File("/mnt/sdcard/logc","log.txt"));
				out = new FileOutputStream(new File(PATH_LOGCAT));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			
			/**
			 * 
			 * 日志等级：*:v , *:d , *:w , *:e , *:f , *:s
			 * 
			 * 显示当前mPID程序的 E和W等级的日志.
			 * 说明：
			 * 
			 * Verbose，啰嗦模式，最低级别的信息，不过滤地输出所有调试信息，包括VERBOSE、DEBUG、INFO、WARN、ERROR级别
			 * 
			 * Debug，调试模式，一些调试信息通过该模式输出，输出信息包括输出DEBUG、INFO、WARN、ERROR级别
			 * 
			 * Info，信息模式，输出信息包括输出INFO、WARN、ERROR级别。
			 *
			 * Warn，警告模式，输出信息包括输出WARN、ERROR级别。
			 * 
			 * Error，错误模式，输出信息包括输出ERROR级别
			 * 
			 * */

			// cmds = "logcat *:e *:w | grep \"(" + mPID + ")\"";
			// cmds = "logcat  | grep \"(" + mPID + ")\"";//打印所有日志信息
			// cmds = "logcat -s way";//打印标签过滤信息
			
			//cmds = "logcat *:e *:i | grep \"(" + mPID + ")\"";
			
			//cmds = "logcat *:w | grep \"(" + mPID + ")\"";
			
			cmds = "logcat *:"+getLevel()+" | grep \"(" + mPID + ")\"";
			
		}
		
		private String getLevel(){
			String level = "i";
			switch(UserConfig.LOGCAT){
			case 1:
				level = "v";
				break;
			case 2:
				level = "d";
				break;
			case 3:
				level = "i";
				break;
			case 4:
				level = "w";
				break;
			case 5:
				level = "e";
				break;
			}
			return level;
		}

		public void stopLogs() {
			mRunning = false;
		}

		@Override
		public void run() {

			String line = null;
			try {
				logcatProc = Runtime.getRuntime().exec(cmds);
				mReader = new BufferedReader(new InputStreamReader(
						logcatProc.getInputStream()), 1024);

				while (mRunning && (line = mReader.readLine()) != null) {
					if (!mRunning) {
						break;
					}
					if (line.length() == 0) {
						continue;
					}
					if (out != null) {
						out.write((line + "\n").getBytes());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (logcatProc != null) {
					logcatProc.destroy();
					logcatProc = null;
				}
				if (mReader != null) {
					try {
						mReader.close();
						mReader = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					out = null;
				}

			}
		}
	}

}
