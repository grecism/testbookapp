package com.main.thread.thread01.chapter05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 *<p>Title	: Thread01Ch05_01_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月21日下午4:02:16
 */
public class Thread01Ch05_01_Test {
	/**5 定时器 Timer**/
	//定时/计划功能在移动开发领域使用较多,比如Android技术。定时计划任务功能在java中主要使用的就是Timer对象,它在内部使用多线程的方式进行处理,所以它和线程
	//技术还是有非常大的关联的。掌握如下技术点:1.如何实现指定时间执行任务。2.如何实现按指定周期执行任务。
	/**5.1 定时器Timer的使用**/
	//在JDK库中Timer类主要负责计划任务的功能,也就是在指定的时间开始执行某一个任务。Timer类的主要作用就是设置计划任务,但封装任务的类却是TimeTask类。执行
	//计划任务的代码要放入TimeTask的子类中,因为TimeTask是一个抽象类。
	/**5.1.1 方法schedule(TimerTask task,Date time)的测试**/
	//5.1.1 方法schedule(TimerTask task,Date time)的测试
	//该方法的作用是在指定的日期执行一次某一任务。
	//1.执行任务的时间晚于当前时间:在未来执行的效果。
	//2.计划时间早于当前时间:提前运行的结果。
	//-1
	//任务虽然执行完了,但进程还未销毁,呈红色状态。
	//守护线程创建成功进程退出。程序运行后迅速结束当前的进程,并且TimerTask中的任务不在被运行,因为进程已经结束了。
	//-1
	//查询构造方法可以得知,创建一个Timer就是启动一个新的线程,这个新启动的线程并不是守护线程,它一直在运行。
	//-2立即执行task任务。
	//-2如果执行任务的时间早于当前时间,则立即执行task任务。

	private static Timer timer = new Timer();
	//private static Timer timer = new Timer(true);//设置为守护线程。
	static public class Thread01Ch05_01_Task extends TimerTask{

		@Override
		public void run() {
			System.out.println("运行了 begin time="+new Date());
		}
		
	}
	public static void main(String[] args) {
		try {
			Thread01Ch05_01_Task task = new Thread01Ch05_01_Task();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//String dateString = "2017-12-21 17:07:00";
			String dateString = "2017-11-21 17:07:00";
			Date date = sdf.parse(dateString);
			System.out.println("指定的任务时间字符串为:"+date.toLocaleString()+"当前时间:"+new Date().toLocaleString());
			timer.schedule(task, date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	
}




















