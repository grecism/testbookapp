package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread06_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月1日下午6:07:14
 */
public class Thread01Ch01_06_Test {
	//1.2.3实例变量与线程安全
	//-2共享数据运行结果 产生了"非线程安全问题" A,B执行结果重复
	//-2我们想要的打印结果却不是重复的,而是依次递减的。某些jvm中,i--的操作要分为如下3步:(1)取得原有i值(2)计算i-1(3)对i进行赋值。在这3个步骤中,如果有多个线程
	//同时访问,那么一定会出现非线程安全问题。
	public static void main(String[] args) {
		Thread01Ch01_06_Thread thread = new Thread01Ch01_06_Thread();
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

class Thread01Ch01_06_Thread extends Thread{
	private int count =5;
	@Override
	public void run() {
		super.run();
		//此示例不要用for语句,因为使用同步后其它线程就得不到运行的机会了,一直由一个线程进行减法运算
		count--;
		System.out.println("由"+this.currentThread().getName()+"计算,count="+count);
	}
}