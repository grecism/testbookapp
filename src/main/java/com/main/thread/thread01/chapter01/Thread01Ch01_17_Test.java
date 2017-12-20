package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread17_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午3:41:52
 */
public class Thread01Ch01_17_Test {
	/**1.7 停止线程**/
	//在java中有以下3种方法可以终止正在运行的线程:
	//(1)使用退出标志,使线程正常退出,也就是当run()方法完成后线程终止。
	//(2)使用stop方法强行终止线程,但是不推荐使用这个方法,因为stop和suspend及resume一样,都是作废过期的方法,使用它们可能产生不可预料的结果。
	//(3)使用interrupt方法中断线程。
	/**1.7.1 停止不了的线程**/
	//1.7.1 停止不了的线程
	//调用interrupt()仅仅是在当前线程中打了一个停止的标记,并不是真的停止线程。
	//-1程序运行结果如下:
	//是否停止1?true 是否停止2?false end!
	//-2程序运行结果如下:
	//是否停止1?true 是否停止2?false end!
	public static void main(String[] args) {
		try {
			Thread01Ch01_17_Thread thread = new Thread01Ch01_17_Thread();
			thread.start();
			thread.sleep(2000);
			thread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

class Thread01Ch01_17_Thread extends Thread{
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 500000; i++) {
			System.out.println("i="+(i+1));
			if(i==10000){
				//1
				//是否停止1?true 是否停止2?false end!
				//Thread.currentThread().interrupt();
				//System.out.println("是否停止1?"+Thread.interrupted());
				//System.out.println("是否停止2?"+Thread.interrupted());
				//2
				//是否停止1?true 是否停止2?false end!
				this.interrupt();
				System.out.println("是否停止1?"+Thread.interrupted());
				System.out.println("是否停止2?"+Thread.interrupted());
			}
		}
		
	}
}
