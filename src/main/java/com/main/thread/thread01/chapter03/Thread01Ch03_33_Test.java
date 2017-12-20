package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_33_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日下午2:15:30
 */
public class Thread01Ch03_33_Test {
	/**3.3 类ThreadLocal的使用**/
	//变量值的共享可以使用public satic变量的形式,所有的线程都使用同一个public static变量。如果想实现每一个线程都有自己的共享变量该如何解决呢?JDk中
	//提供的类ThreadLocal正是为了解决这样的问题。
	//类ThreadLocal主要解决的就是每个线程绑定自己的值,可以将ThreadLocal类比喻成全局存放数据的盒子,盒子中可以存储每个线程的私有数据。
	/**3.3.1 方法get()与null**/
	//3.3.1 方法get()与null
	//-1正常打印结果。
	//-1类解决的是变量在不同线程间的隔离性,也就是不同线程拥有自己的值,不同线程中的值是可以放入ThreadLocal类中进行保存的。
	public static ThreadLocal threadLocal = new ThreadLocal();
	public static void main(String[] args) {
		if(threadLocal.get() == null){
			System.out.println("此值为空");
			threadLocal.set("已放入值");
		}
		System.out.println(threadLocal.get());
		System.out.println(threadLocal.get());
	}
	
	
}

















