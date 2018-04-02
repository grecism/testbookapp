package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_07_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月6日下午4:34:47
 */
public class Thread01Ch02_07_Test {
	/**2.1.6 Synchronized锁重入**/
	//关键字synchronized拥有锁重入的功能,也就是在使用synchronized时,当一个线程得到一个对象锁后,再次请求此对象锁时是可以再次
	//得到该对象的锁的。这也证明在一个synchronized方法/块的内部调用本类的其他synchronized方法/块时,是永远可以得到锁的。
	//2.1.6 Synchronized锁重入
	//-1 依次输出结果
	//-1"可重入锁"的概念是:自己可以再次获取自己的内部锁。比如有1条线程获得了某个对象的锁,此时这个对象锁还没有释放,当其再次想要获取这个
	//对象的锁的时候还是可以获取的,如果不可锁重入的话,就会造成死锁。
	public static void main(String[] args) {
		Thread01Ch02_07_01 thread01 = new Thread01Ch02_07_01();
		thread01.start();
	}
}

class Thread01Ch02_07_Object {
	synchronized public void method01(){
		System.out.println("method01");
		method02();
	}
	
	synchronized public void method02(){
		System.out.println("method02");
		method03();
	}
	
	synchronized public void method03(){
		System.out.println("method03");
	}
}

class Thread01Ch02_07_01 extends Thread{
	@Override
	public void run() {
		super.run();
		Thread01Ch02_07_Object object = new Thread01Ch02_07_Object();
		object.method01();
	}
}