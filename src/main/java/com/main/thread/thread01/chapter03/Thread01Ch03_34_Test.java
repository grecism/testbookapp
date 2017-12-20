package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_34_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日下午2:43:48
 */
public class Thread01Ch03_34_Test {
	/**3.3.2 验证线程变量的隔离性**/
	//3.3.2 验证线程变量的隔离性
	//-1类ThreadLocal存储每一个线程的私有数据。
	//-1虽然3个线程都向threadLocal对象中set()数据值,但每个线程还是能取出自己的数据。
	public static void main(String[] args) {
		try {
			Thread01Ch03_34_Thread_A ta = new Thread01Ch03_34_Thread_A();
			ta.start();
			Thread01Ch03_34_Thread_B tb = new Thread01Ch03_34_Thread_B();
			tb.start();
			for (int i = 200; i < 300; i++) {
				Thread01Ch03_34_Tools.threadLocal.set("main"+(i+1));
				System.out.println("main getValue="+Thread01Ch03_34_Tools.threadLocal.get());
				Thread.sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_34_Tools{
	public static ThreadLocal threadLocal = new ThreadLocal();
}

class Thread01Ch03_34_Thread_A extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			for (int i = 0; i < 100; i++) {
				Thread01Ch03_34_Tools.threadLocal.set("ThreadA"+(i+1));
				System.out.println("ThreadA getValue="+Thread01Ch03_34_Tools.threadLocal.get());
				Thread.sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_34_Thread_B extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			for (int i = 100; i < 200; i++) {
				Thread01Ch03_34_Tools.threadLocal.set("ThreadB"+(i+1));
				System.out.println("ThreadB getValue="+Thread01Ch03_34_Tools.threadLocal.get());
				Thread.sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}















