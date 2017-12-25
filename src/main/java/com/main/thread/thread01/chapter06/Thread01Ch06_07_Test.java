package com.main.thread.thread01.chapter06;

/**
 * 
 *<p>Title	: Thread01Ch06_07_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午6:33:21
 */
public class Thread01Ch06_07_Test {
	/**6.6 使用enum枚举数据类型实现单例模式**/
	//枚举enum和静态代码块的特性相似,在使用枚举类时,构造方法会被自动调用,也可以应用其这个特性实现单例设计模式。
	//6.6 使用enum枚举数据类型实现单例模式
	//-1控制台运行结果相同。
	//-1控制台运行结果相同。
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			Thread01Ch06_07_Thread t = new Thread01Ch06_07_Thread();
			t.start();
		}
	}
}


class Thread01Ch06_07_Thread extends Thread{
	@Override
	public void run() {
		super.run();
		//System.out.println(EnumA.INSTANCE.getInstance().hashCode());
		System.out.println(EnumA.INSTANCE);
	}
}
























