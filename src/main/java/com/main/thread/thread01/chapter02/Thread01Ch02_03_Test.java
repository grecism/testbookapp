package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_03_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月6日下午1:49:13
 */
public class Thread01Ch02_03_Test {
	/**2.1.3 多个对象多个锁**/
	//2.1.3 多个对象多个锁
	//-1无同步时各自有锁
	//-1两个线程分别访问同一个类的两个不同实例的相同名称的同步方法,效果却是以异步的方式运行的。本示例由于创建了两个业务对象,在系统中
	//产生出两个锁,所以结果是异步的。先打印b,然后打印a。 
	//Thread01Ch02_02_PrivateData 虽然使用了synchronized关键字,但打印顺序却不是同步的,是交叉的,为什么？
	//关键字synchronized取得的锁都是对象锁,而不是把一段代码或方法(函数)当做锁,所以上面的示例中,哪个线程先执行带synchronized关键字
	//方法,哪个线程就持有该方法所属对象的锁Lock,那么其它线程只能呈等待状态,前提是多个线程访问的是同一个对象。但如果多个线程访问多个对象,
	//则JVM会创建多个锁。
	public static void main(String[] args) {
		Thread01Ch02_02_PrivateData privateData01 = new Thread01Ch02_02_PrivateData();
		Thread01Ch02_02_PrivateData privateData02 = new Thread01Ch02_02_PrivateData();
		Thread01Ch02_02_A a = new Thread01Ch02_02_A(privateData01);
		a.start();
		Thread01Ch02_02_B b = new Thread01Ch02_02_B(privateData02);
		b.start();
	}
}
