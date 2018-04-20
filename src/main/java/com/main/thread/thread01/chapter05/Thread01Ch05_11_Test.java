package com.main.thread.thread01.chapter05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_11_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午12:39:06
 */
public class Thread01Ch05_11_Test {
	/**5.1.5 scheduleAtFixedRate(TimerTask task,Date firstTime,long period)**/
	//方法schedule和方法scheduleAtFixedRate都会按顺序执行,所以不要考虑非线程安全的情况。
	//方法schedule和方法scheduleAtFixedRate主要的区别只在于不延时的情况。
	//使用schedule方法:如果执行任务的时间没有被延时,那么下一次任务的执行时间参考的是上一次任务的"开始"时的时间来计算。
	//使用scheduleAtFixedRate方法:如果执行任务的时间没有被延时,那么下一次任务的执行时间参考的是上一次任务的"结束"时的时间来计算。
	//延时的情况则没有区别,也就是使用schedule或scheduleAtFixedRate方法都是如果执行任务的时间被延时,那么下一次任务的执行时间参考的是上一次任务"结束"
	//时的时间来计算。
	//5.1.5 scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
	//1.测试schedule方法任务不延时
	//-1使用schedule方法不延时,那么下一次任务的执行时间参考的是上一次任务的"开始"时的时间来计算。
	//-1控制台打印结果说明,在不延时的情况下,如果执行任务的时间没有被延时,则下一次执行任务的时间是上一次任务的开始时间加上delay(period)的时间。
	
	private static Timer timer = new Timer();
	private static int count = 0 ;
	static public class Thread01Ch05_11_Task extends TimerTask{

		@Override
		public void run() {
			try {
				System.out.println("Task begin time:"+new Date().toLocaleString());
				Thread.sleep(1000);
				System.out.println("Task end time:"+new Date().toLocaleString());
				count++;
				if(count == 5){
					timer.cancel();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		try {
			Thread01Ch05_11_Task task = new Thread01Ch05_11_Task();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String taskTime = "2017-12-22 13:34:00";
			Date taskDate = sdf.parse(taskTime);
			System.out.println("指定的taskADate="+taskDate.toLocaleString()+" 当前时间为:"+new Date().toLocaleString());
			timer.schedule(task,taskDate,3000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}





















