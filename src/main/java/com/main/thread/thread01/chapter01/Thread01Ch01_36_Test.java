package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread36_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月5日上午11:31:53
 */
public class Thread01Ch01_36_Test {
	/**1.10 线程的优先级**/
	//在操作系统中,线程可以划分优先级,优先级较高的线程得到的CPU资源较多,也就是CPU优先执行优先级较高的线程对象中的任务。
	//设置线程优先级有助于帮"线程规划器"确定在下一次选择哪一个线程来优先执行。
	//设置 线程的优先级使用setPriority()方法。
	//在java中,线程的优先级分为1-10这10个等级,如果小于1或大于10,则jdk抛出异常 throw new IllegalArgumentException()。
	/**1.10.1 线程优先级的继承特性**/
	//1.10.1 线程优先级的继承特性
	//在java中,线程的优先级具有继承性,比如A线程启动B线程,则B线程的优先级与A是一样的。
	//-1优先级被继承。
	//-1优先级被继承。
	//-2优先级被更改在继续继承。
	//-2优先级被更改在继续继承。
	public static void main(String[] args) {
		System.out.println("main begin="+Thread.currentThread().getPriority());//优先级被继承
		//Thread.currentThread().setPriority(6);//优先级被更改在继续继承
		System.out.println("main end="+Thread.currentThread().getPriority());
		Thread thread1 = new Thread(){
			@Override
			public void run() {
				super.run();
				System.out.println("thread1 ="+this.getPriority());
				System.out.println("thread1 ="+Thread.currentThread().getPriority());
				Thread thread2 = new Thread(){
					@Override
					public void run() {
						super.run();
						System.out.println("thread2 ="+this.getPriority());
						System.out.println("thread2 ="+Thread.currentThread().getPriority());
					}
				};
				thread2.start();
			}
		};
		
		thread1.start();
	}
}
