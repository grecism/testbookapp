package com.main.thread.thread01.chapter05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_12_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午1:34:35
 */
public class Thread01Ch05_12_Test {
	//5.1.5 scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
	//2.测试schedule方法任务延时
	//-1使用schedule方法延时,那么下一次任务的执行时间参考的是上一次任务的"结束"时的时间来计算。
	//-1控制台打印结果说明,如果执行任务的时间被延时,那么下一次任务的执行时间以上一次任务"结束"时的时间为参考来计算。
	
	private static Timer timer = new Timer();
	private static int count = 0 ;
	static public class Thread01Ch05_12_Task extends TimerTask{

		@Override
		public void run() {
			try {
				System.out.println("Task begin time:"+new Date().toLocaleString());
				Thread.sleep(5000);
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
			Thread01Ch05_12_Task task = new Thread01Ch05_12_Task();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String taskTime = "2017-12-22 13:52:00";
			Date taskDate = sdf.parse(taskTime);
			System.out.println("指定的taskDate="+taskDate.toLocaleString()+" 当前时间为:"+new Date().toLocaleString());
			timer.schedule(task,taskDate,2000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}




















