package com.main.thread.thread01.chapter05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_04_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午5:41:35
 */
public class Thread01Ch05_04_Test {
	/**5.1.2 方法schedule(TimerTask task,Date firstTime,long period)的测试**/
	//5.1.2 方法schedule(TimerTask task,Date firstTime,long period)的测试
	//该方法的作用是在指定的日期之后,按指定的间隔周期性地无限循环地执行某一任务。
	//1.计划时间晚于当前时间:在未来执行的效果。
	//2.计划时间早于当前时间:提前运行的效果。
	//-1运行结果可知,每隔4秒运行一次TimerTask任务,并且是无限期地重复执行。
	//-1运行结果可知,每隔4秒运行一次TimerTask任务,并且是无限期地重复执行。
	//-2立即执行task任务。
	//-2控制台打印的结果是,程序运行后立即执行task任务。
	static public class Thread01Ch05_04_Task extends TimerTask{

		@Override
		public void run() {
			System.out.println("运行了 taskA begin time="+new Date().toLocaleString());
		}
		
	}
	public static void main(String[] args) {
		try {
			Thread01Ch05_04_Task task = new Thread01Ch05_04_Task();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//String taskTime = "2017-12-21 17:56:00";
			String taskTime = "2017-12-21 17:50:00";
			Date taskDate = sdf.parse(taskTime);
			System.out.println("指定的taskDate="+taskDate.toLocaleString()+"当前时间:"+new Date().toLocaleString());
			Timer timer = new Timer();
			timer.schedule(task, taskDate,4000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}





















