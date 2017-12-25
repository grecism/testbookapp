package com.main.thread.thread01.chapter06;

/**
 * 
 *<p>Title	: Thread01Ch06_06_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午6:16:58
 */
public class Thread01Ch06_06_Test {
	/**6.5 使用static代码块实现单例模式**/
	//6.5 使用static代码块实现单例模式
	//静态代码块中的代码在使用类的时候就已经执行了,所以可以应用静态代码块的这个特性来实现单例设计模式。
	//-1控制台运行结果相同。
	//-1控制台运行结果相同。
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			Thread01Ch06_06_Thread t = new Thread01Ch06_06_Thread();
			t.start();
		}
	}
}

class Thread01Ch06_06_Object{
	private static Thread01Ch06_06_Object object = null;
	private Thread01Ch06_06_Object() {
	}
	static{
		object = new Thread01Ch06_06_Object();
	}
	public static Thread01Ch06_06_Object getInstance(){
		return object;
	}
}

class Thread01Ch06_06_Thread extends Thread{
	@Override
	public void run() {
		super.run();
		System.out.println(Thread01Ch06_06_Object.getInstance().hashCode());
	}
}

















