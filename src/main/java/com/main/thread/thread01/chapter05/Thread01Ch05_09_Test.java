package com.main.thread.thread01.chapter05;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_09_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日上午9:46:50
 */
public class Thread01Ch05_09_Test {
	/**5.1.3 方法schedule(TimerTask task,long delay)的测试**/
	//该方法的作用是以执行schedule(TimerTask task,long delay)方法当前的时间为参考时间,在此时间基础上延迟指定的毫秒数后执行一次TimeTask任务。
	//5.1.3 方法schedule(TimerTask task,long delay)的测试
	//-1任务task被延迟5秒后执行。
	//-1任务task被延迟5秒后执行。
	public static void main(String[] args) {
		Thread01Ch05_09_Task task = new Thread01Ch05_09_Task();
		Timer timer = new Timer();
		System.out.println("当前时间:"+new Date().toLocaleString());
		timer.schedule(task, 5000);
	}
}

class Thread01Ch05_09_Task extends TimerTask{

	@Override
	public void run() {
		System.out.println("运行了!时间为:"+new Date().toLocaleString());
	}
	
}


















