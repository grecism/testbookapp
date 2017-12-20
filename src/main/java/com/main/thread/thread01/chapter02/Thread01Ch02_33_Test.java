package com.main.thread.thread01.chapter02;

import com.main.thread.thread01.chapter02.Thread01Ch02_33_OutClass.InnerClass;

/**
 * 
 *<p>Title	: Thread01Ch02_33_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日下午3:46:06
 */
public class Thread01Ch02_33_Test {
	/**2.2.14 内置类与同步1**/
	//测试在内置类中有两个同步方法,但使用的却是不同的锁,打印的结果也是异步的。
	//2.2.14 内置类与同步1
	//-1乱序打印
	//-1由于持有不同的"对象监视器",打印结果是乱序的。
	public static void main(String[] args) {
		InnerClass inner = new InnerClass();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				inner.methodA();
			}
		},"A");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				inner.methodB();
			}
		},"B");
		
		t1.start();
		t2.start();
	}
}

class Thread01Ch02_33_OutClass {
	static class InnerClass{
		public void methodA(){
			synchronized ("其他的锁") {
				for (int i = 1; i <= 10; i++) {
					System.out.println(Thread.currentThread().getName()+"i="+i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		synchronized public void methodB(){
			for (int i = 11; i <= 20; i++) {
				System.out.println(Thread.currentThread().getName()+"i="+i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

