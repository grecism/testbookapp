package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_28_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月19日下午6:38:26
 */
public class Thread01Ch03_28_Test {
	/**3.2.3 方法join与异常**/
	//在join过程中,如果当前线程对象被中断,则当前线程出现异常。
	//3.2.3 方法join与异常
	//-1出现异常
	//-1说明方法join()与interrupt()方法如果彼此遇到,则会出现异常。但进程按钮还呈"红色",原因是"线程"A还在继续运行,线程A并未出现异常,是正常执行的状态。
	public static void main(String[] args) {
		try {
			Thread01Ch03_28_Thread_B tb = new Thread01Ch03_28_Thread_B();
			tb.start();
			Thread.sleep(500);
			Thread01Ch03_28_Thread_C tc = new Thread01Ch03_28_Thread_C(tb);
			tc.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_28_Thread_A extends Thread{
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			double random = Math.random();
		}
	}
}

class Thread01Ch03_28_Thread_B extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			Thread01Ch03_28_Thread_A a = new Thread01Ch03_28_Thread_A();
			a.start();
			a.join();
			System.out.println("ThreadB 在run end处运行了");
		} catch (InterruptedException e) {
			System.out.println("ThreadB 在catch处运行了");
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_28_Thread_C extends Thread{
	private Thread01Ch03_28_Thread_B b;
	public Thread01Ch03_28_Thread_C(Thread01Ch03_28_Thread_B b) {
		this.b = b;
	}
	@Override
	public void run() {
		super.run();
		b.interrupt();
	}
}























