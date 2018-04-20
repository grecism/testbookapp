package com.main.thread.thread01.chapter05;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_10_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午12:28:53
 */
public class Thread01Ch05_10_Test {
	/**5.1.4  方法schedule(TimerTask task,long delay,long period)的测试**/
	//该方法的作用是以执行schedule(TimerTask task,long delay,long period)方法当前的时间为参考时间,在此时间基础上延迟指定的毫秒数,再以某一间隔时间
	//无限次数地执行某一任务。
	//5.1.4  方法schedule(TimerTask task,long delay,long period)的测试
	//-1以当前时间为基础3秒以后运行并且每隔5秒执行一次
	//-1凡是使用方法中带有period参数的,都是无限循环执行TimerTask中的任务。
	public static void main(String[] args) {
		Thread01Ch05_10_Task task = new Thread01Ch05_10_Task();
		Timer timer = new Timer();
		System.out.println("当前时间为:"+new Date().toLocaleString());
		timer.schedule(task,3000,5000);
	}
}

class Thread01Ch05_10_Task extends TimerTask{

	@Override
	public void run() {
		System.out.println("运行了 时间为:"+new Date().toLocaleString());
	}
	
}



















