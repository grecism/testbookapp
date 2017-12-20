package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: TestThread1_01</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月1日下午4:25:26
 */
public class Thread01Ch01_02_Test {
	//1.2.1继承Thread类
	//-2随机被执行的线程
	//-2线程执行的随机性
	//Thread.java类中的start()方法通知"线程规划器"此线程已经准备就绪,等待调用线程对象的run()方法。这个过程其实就是让系统安排一个时间来调用Thread中的
	//run()方法,也就是使线程得到运行,启动线程,具有异步执行的效果。如果调用代码thread.run()就不是异步执行了,而是同步,那么此线程对象并不交给"线程规划器"来
	//进行处理,而是由main主线程来调用run()方法,也就是必须等run()方法中的代码执行完后才可以执行后面的代码。
	public static void main(String[] args) {
		try {
			Thread01Ch01_02_Thread thread = new Thread01Ch01_02_Thread();
			thread.setName("thread");
			thread.start();
			for (int i = 0; i < 10; i++) {
				int time = (int) (Math.random()*1000);
				Thread.sleep(time);
				System.out.println(Thread.currentThread().getName()+" run====="+i+"次");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

class Thread01Ch01_02_Thread extends Thread{
	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				int time = (int) (Math.random()*1000);
				Thread.sleep(time);
				System.out.println(Thread.currentThread().getName()+"run======"+i+"次");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
