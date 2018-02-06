package com.main.thread.thread01.chapter02;

import com.main.thread.thread01.chapter02.Thread01Ch02_34_OutClass.InnerClass1;
import com.main.thread.thread01.chapter02.Thread01Ch02_34_OutClass.InnerClass2;

/**
 * 
 *<p>Title	: Thread01Ch02_34_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日下午3:59:33
 */
public class Thread01Ch02_34_Test {
	/**2.2.15 内置类与同步2**/
	//测试同步代码块synchronized(class2)对class2上锁后,其他线程只能以同步的方式调用class2中的静态同步方法。
	//2.2.15 内置类与同步2
	//-1t2/B异步运行 t1/A,t3/C同步运行
	//-1原因是static修饰了内部类,这时该内部类中所定义的所有方法和变量都默认是static的了,所以你加不加static都没问题。
	public static void main(String[] args) {
		final InnerClass1 in1 = new InnerClass1();
		final InnerClass2 in2 = new InnerClass2();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				in1.methodA(in2);
			}
		},"A");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				in1.methodB();
			}
		},"B");
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				in2.methodC();
			}
		},"C");
		
		t1.start();
		t2.start();
		t3.start();
	}
}

class Thread01Ch02_34_OutClass {
	static class InnerClass1{
		public void methodA(InnerClass2 class2){
			synchronized (class2) {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"进入了InnerClass1methodA()方法");
				for (int i = 0; i < 10; i++) {
					System.out.println("i="+i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(threadName+"离开了InnerClass1methodA()方法");
			}
		}
		
		synchronized public void methodB(){
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"进入了InnerClass1methodB()方法");
				for (int i = 10; i < 20; i++) {
					System.out.println("i="+i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(threadName+"离开了InnerClass1methodB()方法");
		}
	}
	
	static class InnerClass2{
		synchronized public void methodC(){
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName+"进入了InnerClass2methodC()方法");
			for (int i = 20; i < 30; i++) {
				System.out.println("i="+i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(threadName+"离开了InnerClass2methodC()方法");
		}
	}
}
