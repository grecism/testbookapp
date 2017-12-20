package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread01_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月1日下午5:04:36
 */
public class Thread01Ch01_01_Test {
	/**1 java多线程技能**/
	//本章中主要介绍Thread类中的核心方法。
	//线程的启动
	//如何使线程暂停
	//如何使线程停止
	//线程的优先级
	//线程安全相关的问题
	/**1.2使用多线程**/
	/**1.2.1继承Thread类**/
	//实现多线程编程的方式主要有两种,一种是继承Thread类,另一种是实现Runnable接口。
	//1.2.1继承Thread类
	//-1先打印"运行结束",后输出"Thread01Ch01_01_Thread"。
	//-1使用多线程技术时,代码的运行结果与代码执行顺序或调用顺序是无关的。
	public static void main(String[] args) {
		/**获得当前线程**/
		//getCurrentThreadName();
		Thread01Ch01_01_Thread thread = new Thread01Ch01_01_Thread();
		thread.start();
		System.out.println("运行结束");
	}
	
	//获得当前线程
	private static void getCurrentThreadName(){
		System.out.println(Thread.currentThread().getName());//main
	}
}

class Thread01Ch01_01_Thread extends Thread{
	@Override
	public void run() {
		super.run();
		System.out.println("Thread01Ch01_01_Thread");
	}
}

