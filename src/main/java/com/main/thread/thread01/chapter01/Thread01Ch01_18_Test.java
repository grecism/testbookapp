package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread18_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午3:51:55
 */
public class Thread01Ch01_18_Test {
	/**1.7.2 判断线程是否是停止状态**/
	//1.7.2  判断线程是否是停止状态
	//如何判断线程的状态是不是停止的。在java的sdk中,Thread.java类里提供了两种方法。
	//(1)this.interrupted();测试当前线程是否已经中断 
	//(2)this.IsInterrupted();测试线程是否已经中断
	//-1程序运行结果如下:
	//是否停止1?false 是否停止2?false end!
	//-2程序运行结果如下:
	//是否停止1?true 是否停止2?false end!
	//这个"当前线程是main",它从未中断过,所以打印两个false。
	public static void main(String[] args) {
		try {
			Thread01Ch01_18_Thread thread = new Thread01Ch01_18_Thread();
			thread.start();
			thread.sleep(1000);
			//thread.interrupt();
			//System.out.println("是否停止1?"+thread.interrupted());
			//System.out.println("是否停止2?"+thread.interrupted());
			
			Thread.currentThread().interrupt(); 
			System.out.println("是否停止1?"+Thread.interrupted());
			System.out.println("是否停止2?"+Thread.interrupted());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end!");
	}
}

class Thread01Ch01_18_Thread extends Thread{
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 500000; i++) {
			System.out.println("i="+(i+1));
		}
		
	}
}
