package com.main.thread.thread01.chapter03;

import java.util.Date;

/**
 * 
 *<p>Title	: Thread01Ch03_39_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日下午3:36:05
 */
public class Thread01Ch03_39_Test {
	/**3.34.2 值继承在修改**/
	//如果在继承的同时还可以对值进行进一步的处理那就更好了。
	//3.34.2 值继承在修改
	//-1成功继承并修改。
	//-1在使用InheritableThreadLocal类需要注意一点的是,如果子线程在取得值的同时,主线程将InheritableThreadLocal中的值进行更改,那么子线程取到的值
	//还是旧值。
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("main getValue="+Thread01Ch03_39_Tools.tla.get());
				Thread.sleep(200);
			}
			Thread.sleep(3000);
			Thread01Ch03_39_Thread_A ta = new Thread01Ch03_39_Thread_A();
			ta.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_39_InheritableThreadLocal_A extends InheritableThreadLocal{
	@Override
	protected Object initialValue() {
		return new Date().getTime();
	}
	
	@Override
	protected Object childValue(Object parentValue) {
		return parentValue+"在子线程中加的值~";
	}
}

class Thread01Ch03_39_Tools extends Thread{
	public static Thread01Ch03_39_InheritableThreadLocal_A tla = new Thread01Ch03_39_InheritableThreadLocal_A();
}

class Thread01Ch03_39_Thread_A extends Thread{
	@Override
	public void run() {
		try {
			super.run();
			for (int i = 0; i < 10; i++) {
				System.out.println("ThreadA getValue="+Thread01Ch03_39_Tools.tla.get());
				Thread.sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}























