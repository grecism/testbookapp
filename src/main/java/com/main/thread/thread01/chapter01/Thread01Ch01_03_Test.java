package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread03_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月1日下午5:14:47
 */
public class Thread01Ch01_03_Test{
	//1.2.1继承Thread类
	//-3线程启动顺序与start()执行顺序无关
	//-3执行start()方法的顺序不代表线程启动的顺序
	public static void main(String[] args) {
		Thread01Ch01_03_Thread t1 = new Thread01Ch01_03_Thread(1);
		Thread01Ch01_03_Thread t2 = new Thread01Ch01_03_Thread(2);
		Thread01Ch01_03_Thread t3 = new Thread01Ch01_03_Thread(3);
		Thread01Ch01_03_Thread t4 = new Thread01Ch01_03_Thread(4);
		Thread01Ch01_03_Thread t5 = new Thread01Ch01_03_Thread(5);
		Thread01Ch01_03_Thread t6 = new Thread01Ch01_03_Thread(6);
		Thread01Ch01_03_Thread t7 = new Thread01Ch01_03_Thread(7);
		Thread01Ch01_03_Thread t8 = new Thread01Ch01_03_Thread(8);
		Thread01Ch01_03_Thread t9 = new Thread01Ch01_03_Thread(9);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		
	}
}

class Thread01Ch01_03_Thread extends Thread{
	private int i ;
	public Thread01Ch01_03_Thread(int i) {
		this.i = i;
	}
	@Override
	public void run() {
		super.run();
		System.out.println(i);
	}
}