package com.main.thread.thread01.chapter05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_08_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午6:28:01
 */
public class Thread01Ch05_08_Test {
	//5.1.2 方法schedule(TimerTask task,Date firstTime,long period)的测试
	//6.Timer的cancel()方法注意事项
	//Timer类中的cancel()方法有时并不一定会停止执行计划任务,而是正常执行。
	//-1并没有停止执行。
	//-1这是因为Timer类中的cancel()方法有时并没有争抢到queue锁,所以TimerTask类中的任务继续正常执行。
	
	static int i=0;
	static public class Thread01Ch05_08_Task extends TimerTask{

		@Override
		public void run() {
			System.out.println("正常运行了"+i);
		}
		
	}
	public static void main(String[] args) {
		try {
			while(true){
				i++;
				Thread01Ch05_08_Task task = new Thread01Ch05_08_Task();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String taskTime = "2017-12-21 18:27:00";
				Date taskDate = sdf.parse(taskTime);
				Timer timer = new Timer();
				timer.schedule(task, taskDate);
				timer.cancel();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}





















