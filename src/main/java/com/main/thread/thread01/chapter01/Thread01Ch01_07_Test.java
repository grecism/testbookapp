package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread07_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月1日下午6:21:54
 */
public class Thread01Ch01_07_Test {
	//1.2.3实例变量与线程安全
	//-3共享数据结果 线程安全
	//-3这时就需要使多个线程之间进行同步,也就是用按顺序排队的方式进行减1操作。synchronized可以在任意对象及方法上加锁,而加锁的这段代码称为"互斥区"或"临界区"。
	//术语"非线程安全":非线程安全主要是指多个线程对同一个对象中的同一个实例变量进行操作时会出现值被更改,值不同步的情况,进而影响程序的执行流程。
	public static void main(String[] args) {
		Thread01Ch01_07_Thread thread = new Thread01Ch01_07_Thread();
		Thread t1 = new Thread(thread,"A");
		Thread t2 = new Thread(thread,"B");
		Thread t3 = new Thread(thread,"C");
		Thread t4 = new Thread(thread,"D");
		Thread t5 = new Thread(thread,"E");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}

class Thread01Ch01_07_Thread extends Thread{
	private int count = 5;
	@Override
	synchronized public void run() {
		super.run();
		count--;
		System.out.println("由"+this.currentThread().getName()+"计算,count="+count);
	}
}
