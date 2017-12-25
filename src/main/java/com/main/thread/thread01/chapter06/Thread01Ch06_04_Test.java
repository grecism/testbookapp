package com.main.thread.thread01.chapter06;

/**
 * 
 *<p>Title	: Thread01Ch06_04_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午4:12:58
 */
public class Thread01Ch06_04_Test {
	/**6.3 使用静态内置类实现单例模式**/
	//6.3 使用静态内置类实现单例模式
	//DCL可以解决多线程单例模式的非线程安全问题,使用其他的办法也能够达到同样的效果。
	//-1控制台运行结果相同。
	//-1控制台运行结果相同。
	public static void main(String[] args) {
		Thread01Ch06_04_Thread t1 = new Thread01Ch06_04_Thread();
		Thread01Ch06_04_Thread t2 = new Thread01Ch06_04_Thread();
		Thread01Ch06_04_Thread t3 = new Thread01Ch06_04_Thread();
		t1.start();
		t2.start();
		t3.start();
	}
}

class Thread01Ch06_04_Object{
	private Thread01Ch06_04_Object(){
	}
	//内部类方式
	private static class Thread01Ch06_04_ObjectHandler{
		private static Thread01Ch06_04_Object object = new Thread01Ch06_04_Object();
	}
	public static Thread01Ch06_04_Object getInstance(){
		return Thread01Ch06_04_ObjectHandler.object;
		
	}
	
}

class Thread01Ch06_04_Thread extends Thread{
	@Override
	public void run() {
		super.run();
		System.out.println(Thread01Ch06_04_Object.getInstance().hashCode());
	}
}




















