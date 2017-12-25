package com.main.thread.thread01.chapter05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_05_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午5:58:10
 */
public class Thread01Ch05_05_Test {
	//5.1.2 方法schedule(TimerTask task,Date firstTime,long period)的测试
	//3.任务执行时间被延时
	//-1任务被延时但还是一个一个顺序运行。
	//-1任务被延时但还是一个一个顺序运行。
	public static void main(String[] args) {
		try {
			Thread01Ch05_05_Task task = new Thread01Ch05_05_Task();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String taskTime="2017-12-21 18:07:00";
			Date taskDate = sdf.parse(taskTime);
			System.out.println("指定的taskDate="+taskDate.toLocaleString()+"当前时间:"+new Date());
			Timer timer = new Timer();
			timer.schedule(task, taskDate,4000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch05_05_Task extends TimerTask{

	@Override
	public void run() {
		try {
			System.out.println("taskA begin time="+new Date());
			Thread.sleep(5000);
			System.out.println("taskA end time="+new Date());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}




















