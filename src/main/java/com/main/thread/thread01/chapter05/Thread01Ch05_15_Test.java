package com.main.thread.thread01.chapter05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_15_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午2:09:53
 */
public class Thread01Ch05_15_Test {
	//5.1.5 scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
	//5.验证schedule方法不具有追赶执行性
	//-1schedule方法不具有追赶执行性。
	//-1指定时间"2017-12-22 14:15:00"到当前时间"2017-12-22 14:19:00"之间的时间所对应的Task任务被取消了,不执行了。这就是Task任务不追赶的情况。
	public static void main(String[] args) {
		try {
			Thread01Ch05_15_Task task = new Thread01Ch05_15_Task();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String taskTime = "2017-12-22 14:15:00";
			Date taskDate = sdf.parse(taskTime);
			System.out.println("指定的taskDate="+taskDate.toLocaleString()+" 当前时间为:"+new Date().toLocaleString());
			Timer timer = new Timer();
			timer.schedule(task,taskDate,5000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch05_15_Task extends TimerTask{

	@Override
	public void run() {
		System.out.println("Task begin time="+new Date().toLocaleString());
		System.out.println("Task end time="+new Date().toLocaleString());
	}
	
}




















