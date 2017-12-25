package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: Thread01Ch01_10_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日上午9:52:06
 */
public class Thread01Ch01_10_Test {
	/**1.2.4 留意i--与system.out.println()的异常**/
	//1.2.4 留意i--与system.out.println()的异常
	//-1程序运行后根据概率还是会出现非线程安全问题
	//-1细化一下println()方法与i++联合使用时"有可能"出现的另外一种异常情况。
	//虽然println()方法在内部是同步的,但是i--的操作却是在进入println()方法前发生的,所以有发生非线程安全的概率。所以为了防止发生非线程安全问题,还是应继续使用同步方法。
	public static void main(String[] args) {
		Thread01Ch01_10_Thread thread = new Thread01Ch01_10_Thread();
		Thread t1 = new Thread(thread);
		Thread t2 = new Thread(thread);
		Thread t3 = new Thread(thread);
		Thread t4 = new Thread(thread);
		Thread t5 = new Thread(thread);
		Thread t6 = new Thread(thread);
		Thread t7 = new Thread(thread);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
	}
}

class Thread01Ch01_10_Thread extends Thread{
	private int count = 7;
	@Override
	public void run() {
		//super.run();
		//System.out.println("由"+this.currentThread().getName()+"计算,count="+(count--));
		System.out.println("count="+(count--)+"由"+this.currentThread().getName()+"计算");

	}
}
