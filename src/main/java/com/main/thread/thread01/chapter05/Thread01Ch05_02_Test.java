package com.main.thread.thread01.chapter05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_02_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午4:50:22
 */
public class Thread01Ch05_02_Test {
	//5.1.1 方法schedule(TimerTask task,Date time)的测试
	//3.多个TimerTask任务及延时的测试
	//Timer中允许有多个TimerTask任务。
	//-1一个Timer中可以运行多个TimerTask任务。
	//-1一个Timer中可以运行多个TimerTask任务。
	
	private static Timer timer = new Timer();
	static public class Thread01Ch05_02_Task_A extends TimerTask{

		@Override
		public void run() {
			System.out.println("运行了taskA begin time="+new Date());
		}
		
	}
	static public class Thread01Ch05_02_Task_B extends TimerTask{

		@Override
		public void run() {
			System.out.println("运行了taskB begin time="+new Date());
		}
		
	}
	public static void main(String[] args) {
		try {
			Thread01Ch05_02_Task_A taskA = new Thread01Ch05_02_Task_A();
			Thread01Ch05_02_Task_B taskB = new Thread01Ch05_02_Task_B();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String taskATime = "2017-12-21 17:34:00";
			String taskBTime = "2017-12-21 17:35:00";
			Date taskADate = sdf.parse(taskATime);
			Date taskBDate = sdf.parse(taskBTime);
			System.out.println("指定的taskADate="+taskADate.toLocaleString()+"当前时间:"+new Date());
			System.out.println("指定的taskBDate="+taskBDate.toLocaleString()+"当前时间:"+new Date());
			timer.schedule(taskA, taskADate);
			timer.schedule(taskB, taskBDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}





















