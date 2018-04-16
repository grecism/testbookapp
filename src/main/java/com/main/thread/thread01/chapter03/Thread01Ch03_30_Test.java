package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_30_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日上午11:07:38
 */
public class Thread01Ch03_30_Test {
	/**3.2.5 方法join(long)与sleep(long)的区别**/
	//方法join(long)的功能在内部是使用wait(long)方法来实现的,所以join(long)方法具有释放锁的特点。
	//3.2.5 方法join(long)与sleep(long)的区别
	//Thread.sleep(long)方法具有不释放锁的特点。
	//-1线程B不释放线程A的锁。
	//-1源码中可以了解到,当执行wait(long)方法后,当前线程的锁被释放,那么其它线程就可以调用此线程中的同步方法了。
	public static void main(String[] args) {
		try {
			Thread01Ch03_30_Thread_A ta = new Thread01Ch03_30_Thread_A();
			Thread01Ch03_30_Thread_B tb = new Thread01Ch03_30_Thread_B(ta);
			tb.start();
			Thread.sleep(1000);
			Thread01Ch03_30_Thread_C tc = new Thread01Ch03_30_Thread_C(ta);
			tc.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_30_Thread_A extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			System.out.println("A run begin time="+System.currentTimeMillis());
			//Thread.sleep(10000);
			Thread.sleep(5000);
			System.out.println("A run end time="+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void threadBMethod(){
		System.out.println("ThreadBMethod begin time="+System.currentTimeMillis());
	}
}

class Thread01Ch03_30_Thread_B extends Thread{
	private Thread01Ch03_30_Thread_A a;
	public Thread01Ch03_30_Thread_B(Thread01Ch03_30_Thread_A a) {
		this.a = a;
	}
	@Override
	public void run() {
		try {
			synchronized (a) {
				a.start();
				//Thread.sleep(3000); 
				Thread.sleep(6000);//此时并不释放锁
				System.out.println("ThreadB end");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_30_Thread_C extends Thread{
	private Thread01Ch03_30_Thread_A a;
	public Thread01Ch03_30_Thread_C(Thread01Ch03_30_Thread_A a) {
		this.a = a;
	}
	@Override
	public void run() {
		super.run();
		a.threadBMethod();
	}
}















