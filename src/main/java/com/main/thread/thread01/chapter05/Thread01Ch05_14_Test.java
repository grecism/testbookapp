package com.main.thread.thread01.chapter05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_14_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午2:06:19
 */
public class Thread01Ch05_14_Test {
	//5.1.5 scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
	//4.测试scheduleAtFixedRate方法任务延时
	//-1使用scheduleAtFixedRate方法延时,那么下一次任务的执行时间参考的是上一次任务的"结束"时的时间来计算。
	//-1控制台打印结果说明,如果执行任务的时间被延时,那么下一次任务的执行时间以上一次任务"结束"时的时间为参考来计算。
	
	private static Timer timer = new Timer();
	private static int count = 0 ;
	static public class Thread01Ch05_14_Task extends TimerTask{

		@Override
		public void run() {
			try {
				System.out.println("Task begin time:"+new Date());
				Thread.sleep(5000);
				System.out.println("Task end time:"+new Date());
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
			Thread01Ch05_14_Task task = new Thread01Ch05_14_Task();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String taskTime = "2017-12-22 14:08:00";
			Date taskDate = sdf.parse(taskTime);
			System.out.println("指定的taskDate="+taskDate+" 当前时间为:"+new Date());
			timer.scheduleAtFixedRate(task,taskDate,2000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}























