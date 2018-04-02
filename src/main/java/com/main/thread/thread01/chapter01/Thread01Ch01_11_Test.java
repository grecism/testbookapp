package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread11_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日上午11:02:20
 */
public class Thread01Ch01_11_Test {
	/**1.3 currentThread()方法**/
	//1.3 currentThread()方法
	//-1程序运行结果如下:
	//main 构造方法的打印:main run方法的打印:Thread-0
	//-2程序运行结果如下:
	//main 构造方法的打印:main run方法的打印:main
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		Thread01Ch01_11_Thread thread= new Thread01Ch01_11_Thread();
		thread.start(); //-1
		//thread.run(); //-2
	}
}

class Thread01Ch01_11_Thread extends Thread{
	public Thread01Ch01_11_Thread() {
		System.out.println("构造方法的打印:"+Thread.currentThread().getName());
	}
	@Override
	public void run() {
		//super.run();
		System.out.println("run方法的打印:"+Thread.currentThread().getName());
	}
}
