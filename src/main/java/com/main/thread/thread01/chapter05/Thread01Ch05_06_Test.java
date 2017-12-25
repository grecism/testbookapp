package com.main.thread.thread01.chapter05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_06_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午6:08:19
 */
public class Thread01Ch05_06_Test {
	//5.1.2 方法schedule(TimerTask task,Date firstTime,long period)的测试
	//4.TimerTask()类的cancel()方法
	//TimerTask()类中的cancel()方法的作用是将自身从任务队列中清除。
	//-1taskA仅运行一次后被清除了。
	//-1TimerTask()类的cancel()方法是将自身从任务队列中被移除,其他任务不受影响。
	public static void main(String[] args) {
		try {
			Thread01Ch05_06_Task_A taskA = new Thread01Ch05_06_Task_A();
			Thread01Ch05_06_Task_B taskB = new Thread01Ch05_06_Task_B();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String taskATime = "2017-12-21 18:17:00";
			Date taskADate = sdf.parse(taskATime);
			System.out.println("指定的taskADate="+taskADate+"当前时间:"+new Date());
			Timer timer = new Timer();
			timer.schedule(taskA, taskADate,4000);
			timer.schedule(taskB, taskADate,4000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch05_06_Task_A extends TimerTask{

	@Override
	public void run() {
		System.out.println("运行了 taskA begin time="+new Date());
		this.cancel();
	}
	
}

class Thread01Ch05_06_Task_B extends TimerTask{

	@Override
	public void run() {
		System.out.println("运行了 taskB begin time="+new Date());
	}
	
}



















