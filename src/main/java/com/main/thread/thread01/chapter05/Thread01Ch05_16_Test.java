package com.main.thread.thread01.chapter05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_16_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午2:20:01
 */
public class Thread01Ch05_16_Test {
	//5.1.5 scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
	//6.验证scheduleAtFixedRate方法具有追赶执行性
	//-1两个时间段内所对应的Task任务被"补充性"执行了,这就是Task任务追赶执行的特性。
	//-1两个时间段内所对应的Task任务被"补充性"执行了,这就是Task任务追赶执行的特性。
	public static void main(String[] args) {
		try {
			Thread01Ch05_16_Task task = new Thread01Ch05_16_Task();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String taskTime = "2017-12-22 14:29:00";
			Date taskDate = sdf.parse(taskTime);
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(task,taskDate,5000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch05_16_Task extends TimerTask{

	@Override
	public void run() {
		System.out.println("Task begin time="+new Date());
		System.out.println("Task end time="+new Date());
	}
	
}























