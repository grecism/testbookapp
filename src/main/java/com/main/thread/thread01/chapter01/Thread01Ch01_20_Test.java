package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread20_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午4:29:46
 */
public class Thread01Ch01_20_Test {
	//1.7.2  判断线程是否是停止状态 
	//-4程序运行结果如下:
	//是否停止1?true 是否停止2?true end!
	//-5程序运行结果如下:
	//是否停止1?false 是否停止2?false end!
	//方法IsInterrupted()并未清除状态标志,所以打印了两个true。
	//-4-5
	//this.interrupted:测试当前线程是否已经是中断状态,执行后具有将状态标志织清除为false的功能。
	//this.isInterrupted:测试线程Thread对象是否已经是中断状态,但不清除状态标志。
	public static void main(String[] args) {
		try {
			Thread01Ch01_20_Thread thread = new Thread01Ch01_20_Thread();
			thread.start();
			thread.sleep(10);
			//-1
			//thread.interrupt();
			//System.out.println("是否停止1?"+thread.isInterrupted());//true
			//System.out.println("是否停止2?"+thread.isInterrupted());//true
			//System.out.println("是否停止3?"+thread.interrupted());//false
			//System.out.println("是否停止4?"+thread.interrupted());//false
			
			//-2
			Thread.currentThread().interrupt();
			System.out.println("是否停止1?"+thread.isInterrupted());//false
			System.out.println("是否停止2?"+thread.isInterrupted());//false
			System.out.println("是否停止3?"+thread.interrupted());//true
			System.out.println("是否停止4?"+thread.interrupted());//false
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end!");
	}
}

class Thread01Ch01_20_Thread extends Thread{
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 5000; i++) {
			System.out.println("i="+(i+1));
		}
		
	}
}
