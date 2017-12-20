package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread16_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午12:14:01
 */
public class Thread01Ch01_16_Test {
	/**1.6 getId()方法**/
	//getId()方法的作用是取得线程的唯一标识
	//1.5 sleep() 方法
	//1.6 getId()方法
	public static void main(String[] args) {
		Thread01Ch01_16_Thread thread = new Thread01Ch01_16_Thread();
		System.out.println("begin="+System.currentTimeMillis());
		thread.start();
		System.out.println("end="+System.currentTimeMillis());
		System.out.println("当前线程名字="+Thread.currentThread().getName()+" 当前线程id="+Thread.currentThread().getId());		System.out.println("当前线程名字="+Thread.currentThread().getName()+" 当前线程id="+Thread.currentThread().getId());

	}
}

class Thread01Ch01_16_Thread extends Thread{
	@Override
	public void run() {
		//super.run();
		try {
			System.out.println("run threadname="+this.currentThread().getName()+" begin="+System.currentTimeMillis());
			Thread.sleep(2000);
			System.out.println("run threadname="+this.currentThread().getName()+" end="+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}