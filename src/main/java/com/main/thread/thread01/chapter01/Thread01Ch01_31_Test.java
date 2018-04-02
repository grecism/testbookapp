package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread31_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月5日上午10:33:11
 */
public class Thread01Ch01_31_Test {
	/**1.8.2 suspend与resume方法的缺点-独占**/
	//在使用suspend与resume方法时,如果使用不当,极易造成公共的同步对象的独占,使得其他线程无法访问公共同步对象。
	//1.8.2 suspend与resume方法的缺点-独占
	//-1独占并锁死了printString方法。
	//-1独占并锁死了printString方法。
	public static void main(String[] args) {
		try {
			final Thread01Ch01_31_SynchronizedObject object = new Thread01Ch01_31_SynchronizedObject();
			Thread thread = new Thread(){
				@Override
				public void run() {
					super.run();
					object.printString();
				}
			};
			thread.setName("a");
			thread.start();
			Thread.sleep(1000);
			Thread thread2 =new Thread(){
				@Override
				public void run() {
					super.run();
					System.out.println("thread2启动了,但进入不了printString()方法！结果只打印了一个begin!");
					System.out.println("因为printString()方法被a线程锁定并且永远suspend暂停了！");
					object.printString();
				}
			};
			thread2.start();
			System.out.println("main end!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

class Thread01Ch01_31_SynchronizedObject {
	synchronized public void printString(){
		System.out.println("begin");
		if(Thread.currentThread().getName().equals("a")){
			System.out.println("a线程永远处于suspend暂停状态了！");
			Thread.currentThread().suspend();
		}
		System.out.println("end");
	}
}

