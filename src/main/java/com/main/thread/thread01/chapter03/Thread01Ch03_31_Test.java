package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_31_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日上午11:44:36
 */
public class Thread01Ch03_31_Test {
	//3.2.5 方法join(long)与sleep(long)的区别
	//验证join(long)方法释放锁的特点。
	//-2方法join(long)释放锁。
	//-2方法join(long)具有释放锁的特点。
	public static void main(String[] args) {
		try {
			Thread01Ch03_31_Thread_A ta = new Thread01Ch03_31_Thread_A();
			Thread01Ch03_31_Thread_B tb = new Thread01Ch03_31_Thread_B(ta);
			tb.start();
			Thread.sleep(1000);
			Thread01Ch03_31_Thread_C tc = new Thread01Ch03_31_Thread_C(ta);
			tc.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

class Thread01Ch03_31_Thread_A extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			System.out.println("A run begin time="+System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("A run end time="+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void ThreadBMethod(){
		System.out.println("ThreadBMethod begin time="+System.currentTimeMillis());
	}
}

class Thread01Ch03_31_Thread_B extends Thread{
	private Thread01Ch03_31_Thread_A a;
	public Thread01Ch03_31_Thread_B(Thread01Ch03_31_Thread_A a) {
		this.a = a;
	}
	@Override
	public void run() {
		try {
			synchronized (a) {
				a.start();
				a.join();//此时释放锁
				for (int i = 0; i < Integer.MAX_VALUE; i++) {
					double random = Math.random();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_31_Thread_C extends Thread{
	private Thread01Ch03_31_Thread_A a;
	public Thread01Ch03_31_Thread_C(Thread01Ch03_31_Thread_A a) {
		this.a = a;
	}
	@Override
	public void run() {
		super.run();
		a.ThreadBMethod();
	}
}


















