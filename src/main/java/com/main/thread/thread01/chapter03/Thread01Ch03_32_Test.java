package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_32_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日下午12:01:26
 */
public class Thread01Ch03_32_Test {
	/**3.2.6 方法join(long)后面的代码提前运行:出现意外**/
	/**3.2.7 方法join(long)后面的代码提前运行:解释意外**/
	//3.2.6 方法join(long)后面的代码提前运行:出现意外
	//3.2.7 方法join(long)后面的代码提前运行:解释意外
	//-1程序运行结果出现不一致的情况,程序运行结果如下:
	//1
	//threadname=Thread-1B begin time=1513749909238
	//threadname=Thread-1B end time=1513749914240
	//main end time=1513749914240
	//threadname=Thread-0A begin time=1513749914240
	//threadname=Thread-0A end time=1513749919242
	//2
	//threadname=Thread-1B begin time=1513749909238
	//threadname=Thread-1B end time=1513749914240
	//threadname=Thread-0A begin time=1513749914240
	//threadname=Thread-0A end time=1513749919242
	//main end time=1513749914240
	//3
	//threadname=Thread-1B begin time=1513749909238
	//threadname=Thread-1B end time=1513749914240
	//threadname=Thread-0A begin time=1513749914240
	//main end time=1513749914240
	//threadname=Thread-0A end time=1513749919242
	//-1程序运行结果出现不一致的情况。
	//方法join(long)大部分是先运行的,也就是先抢到A的锁,然后快速进行释放。
	public static void main(String[] args) {
		try {
			Thread01Ch03_32_Thread_A ta = new Thread01Ch03_32_Thread_A();
			Thread01Ch03_32_Thread_B tb = new Thread01Ch03_32_Thread_B(ta);
			
			tb.start();
			ta.start();
			ta.join(2000);
			System.out.println("main end time="+System.currentTimeMillis());
			System.out.println("main end !");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_32_Thread_A extends Thread{
	@Override
	synchronized public void run() {
		try {
			super.run();
			System.out.println("threadname="+Thread.currentThread().getName()+"A begin time="+System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("threadname="+Thread.currentThread().getName()+"A end time="+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

class Thread01Ch03_32_Thread_B extends Thread{
	private Thread01Ch03_32_Thread_A a;
	public Thread01Ch03_32_Thread_B(Thread01Ch03_32_Thread_A a) {
		this.a = a;
	}
	@Override
	public void run() {
		super.run();
		synchronized (a) {
			try {
				System.out.println("threadname="+Thread.currentThread().getName()+"B begin time="+System.currentTimeMillis());
				Thread.sleep(5000);
				System.out.println("threadname="+Thread.currentThread().getName()+"B end time="+System.currentTimeMillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}















