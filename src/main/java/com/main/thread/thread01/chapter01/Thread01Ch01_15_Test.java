package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread15_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午12:06:44
 */
public class Thread01Ch01_15_Test {
	/**1.5 sleep()方法**/
	//sleep()方法的作用是在指定的毫秒内让当前"正在执行的线程"休眠(暂停执行)。这个"正在执行的线程"是指this.currentThread返回的线程。
	//1.5 sleep() 方法
	//-1程序运行结果如下:
	//begin=1513574359901
	//run threadname=main begin
	//run threadname=main end
	//end=1513574361901
	public static void main(String[] args) {
		Thread01Ch01_15_Thread thread = new Thread01Ch01_15_Thread();
		System.out.println("begin="+System.currentTimeMillis());
		thread.run();
		System.out.println("end="+System.currentTimeMillis());
	}
}

class Thread01Ch01_15_Thread extends Thread{
	@Override
	public void run() {
		//super.run();
		try {
			System.out.println("run threadname="+this.currentThread().getName()+" begin");
			Thread.sleep(2000);
			System.out.println("run threadname="+this.currentThread().getName()+" end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}