package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread13_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日上午11:26:31
 */
public class Thread01Ch01_13_Test {
	/**1.4 isAlive()方法 **/
	//isAlive()方法的作用是测试线程是否处于活动状态。
	//活动状态就是线程已经启动且尚未终止。线程处于正在运行或准备开始运行的状态,就认为线程是"存活"的。
	//1.4 isAlive()方法 
	//-1程序运行结果如下: 
	//begin == false end == true run=true
	//-2程序运行结果如下: 
	//begin == false run=true end == false
	public static void main(String[] args) throws InterruptedException {
		Thread01Ch01_13_Thread thread = new Thread01Ch01_13_Thread();
		System.out.println("begin == "+thread.isAlive());
		thread.start();   //-1
		//Thread.sleep(1000); //-2
		System.out.println("end == "+thread.isAlive());  
	}
}

class Thread01Ch01_13_Thread extends Thread{
	@Override
	public void run() {
		//super.run();
		System.out.println("run="+this.isAlive());
	}
}
