package com.main.thread.thread01.chapter05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_07_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午6:17:25
 */
public class Thread01Ch05_07_Test {
	//5.1.2 方法schedule(TimerTask task,Date firstTime,long period)的测试
	//5.Timer类的cancel()方法。
	//和TimerTask类中的cancel()方法清除自身不同,Timer类中的cancel()方法的作用是将任务队列中的全部任务清空。
	//-1进程被清空。
	//-1全部任务都被清除,并且进程被销毁,按钮由红色变成灰色。
	
	private static Timer timer = new Timer();
	static public class Thread01Ch05_07_Task_A extends TimerTask{

		@Override
		public void run() {
			System.out.println("运行了 taskA begin time="+new Date().toLocaleString());
			timer.cancel();
		}
		
	}
	static public class Thread01Ch05_07_Task_B extends TimerTask{

		@Override
		public void run() {
			System.out.println("运行了 taskB begin time="+new Date().toLocaleString());
		}
		
	}
	public static void main(String[] args) {
		try {
			Thread01Ch05_07_Task_A taskA = new Thread01Ch05_07_Task_A();
			Thread01Ch05_07_Task_B taskB = new Thread01Ch05_07_Task_B();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String taskATime = "2017-12-21 18:27:00";
			Date taskADate = sdf.parse(taskATime);
			System.out.println("指定的taskADate="+taskADate.toLocaleString()+"当前时间:"+new Date().toLocaleString());
			timer.schedule(taskA, taskADate, 4000);
			timer.schedule(taskB, taskADate, 4000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}





















