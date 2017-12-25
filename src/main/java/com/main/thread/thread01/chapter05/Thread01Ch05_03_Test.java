package com.main.thread.thread01.chapter05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_03_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午5:37:03
 */
public class Thread01Ch05_03_Test {
	//5.1.1 方法schedule(TimerTask task,Date time)的测试
	//3.多个TimerTask任务及延时的测试
	//-2taskB的运行时间被延迟。以taskA完成的时间为基准,后立即执行,向后延迟。
	//-2TimerTask是以队列的方式一个一个被顺序执行的,所以执行的时间有可能和预期的时间不一致,因为前面的任务有可能消耗的时间较长,则后面的任务运行的时间也会被延迟。
		
	private static Timer timer = new Timer();
	static public class Thread01Ch05_03_Task_A extends TimerTask{

		@Override
		public void run() {
			try {
				System.out.println("taskA begin time="+new Date());
				Thread.sleep(10000);
				System.out.println("taskA end time="+new Date());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	static public class Thread01Ch05_03_Task_B extends TimerTask{

		@Override
		public void run() {
			System.out.println("taskB begin time="+new Date());
			System.out.println("运行了taskB time="+new Date());
			System.out.println("taskB end time="+new Date());
		}
		
	}
	public static void main(String[] args) {
		try {
			Thread01Ch05_03_Task_A taskA = new Thread01Ch05_03_Task_A();
			Thread01Ch05_03_Task_B taskB = new Thread01Ch05_03_Task_B();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String taskATime = "2017-12-21 17:41:00";
			String taskBTime = "2017-12-21 17:41:05";
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
