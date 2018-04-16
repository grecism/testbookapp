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
	//是否停止1?true 是否停止2?false
	//-2程序运行结果如下:
	//是否停止1?true 是否停止2?false
	//-3
	//是否停止1?true 是否停止2?false
	//-4
	//是否停止1?true 是否停止2?false
	public static void main(String[] args) {
		try {
			Thread01Ch01_17_Thread thread = new Thread01Ch01_17_Thread();
			thread.start();
			thread.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

class Thread01Ch01_17_Thread extends Thread{
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 500; i++) {
			System.out.println("i="+(i+1));
			if(i==100){
				//-1
				//System.out.println("是否停止1?"+Thread.interrupted());//true
				//System.out.println("是否停止2?"+Thread.interrupted());//false
				//-2
				//System.out.println("是否停止1?"+Thread.currentThread().interrupted());//true
				//System.out.println("是否停止2?"+Thread.currentThread().interrupted());//false
				//-3
				//Thread.currentThread().interrupt();
				//this.interrupt();
				//System.out.println("是否停止1?"+Thread.interrupted());//true
				//System.out.println("是否停止2?"+Thread.interrupted());//false
				//-4
				Thread.currentThread().interrupt();
				//this.interrupt();
				System.out.println("是否停止1?"+Thread.currentThread().interrupted());//true
				System.out.println("是否停止2?"+Thread.currentThread().interrupted());//false


			}
		}
		
	}
}
