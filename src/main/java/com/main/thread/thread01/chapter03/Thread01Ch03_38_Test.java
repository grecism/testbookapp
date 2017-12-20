package com.main.thread.thread01.chapter03;

import java.util.Date;

/**
 * 
 *<p>Title	: Thread01Ch03_38_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日下午3:25:23
 */
public class Thread01Ch03_38_Test {
	/**3.4 类InheritableThreadLocal的使用**/
	//使用InheritableThreadLocal可以在子线程中取得父线程继承下来的值。
	/**3.4.1 值继承**/
	//3.4.1 值继承
	//-1值成功地从父线程中继承下来。
	//-1InheritableThreadLocal可以让子线程从父线程中取得值。
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("main getValue="+Thread01Ch03_38_Tools.tla.get());
				Thread.sleep(200);
			}
			Thread.sleep(3000);
			Thread01Ch03_38_Thread_A ta = new Thread01Ch03_38_Thread_A();
			ta.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}	

class Thread01Ch03_38_InheritableThreadLocal_A extends InheritableThreadLocal{
	@Override
	protected Object initialValue() {
		return new Date().getTime();
	}
}

class Thread01Ch03_38_Tools extends Thread{
	public static Thread01Ch03_38_InheritableThreadLocal_A tla = new Thread01Ch03_38_InheritableThreadLocal_A();
}

class Thread01Ch03_38_Thread_A extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			for (int i = 0; i < 10; i++) {
				System.out.println("ThreadA getValue="+Thread01Ch03_38_Tools.tla.get());
				Thread.sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}




















