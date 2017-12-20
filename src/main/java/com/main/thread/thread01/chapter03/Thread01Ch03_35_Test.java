package com.main.thread.thread01.chapter03;

import java.util.Date;

/**
 * 
 *<p>Title	: Thread01Ch03_35_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日下午2:57:11
 */
public class Thread01Ch03_35_Test {
	//3.3.2 验证线程变量的隔离性
	//-2运行结果只有两种时间。
	//-2在第一次调用ThreadLocal类的get()方法返回值是null。
	public static void main(String[] args) {
		try {
			Thread01Ch03_35_Thread_A ta = new Thread01Ch03_35_Thread_A();
			ta.start();
			Thread.sleep(1000);
			Thread01Ch03_35_Thread_B tb = new Thread01Ch03_35_Thread_B();
			tb.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_35_Tools{
	public static ThreadLocal<Date> threadLocal = new ThreadLocal<Date>();
}

class Thread01Ch03_35_Thread_A extends Thread{
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 20; i++) {
			if(Thread01Ch03_35_Tools.threadLocal.get() == null){
				Thread01Ch03_35_Tools.threadLocal.set(new Date());
			}
			System.out.println("ThreadA getValue="+Thread01Ch03_35_Tools.threadLocal.get().getTime());
			//System.out.println("ThreadA getValue="+Thread01Ch03_35_Tools.threadLocal.get());

		}
	}
}

class Thread01Ch03_35_Thread_B extends Thread{
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 20; i++) {
			if(Thread01Ch03_35_Tools.threadLocal.get() == null){
				Thread01Ch03_35_Tools.threadLocal.set(new Date());
			}
			System.out.println("ThreadB getValue="+Thread01Ch03_35_Tools.threadLocal.get().getTime());
			//System.out.println("ThreadB getValue="+Thread01Ch03_35_Tools.threadLocal.get());

		}
	}
}