package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_04_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月15日下午1:04:55
 */
public class Thread01Ch03_04_Test {
	//3.1.3 等待/通知机制的实现
	//-2方法wait下面的代码不执行了
	//-2方法wait下面的代码不执行了
	public static void main(String[] args) {
		try {
			String lock = new String();
			System.out.println("syn之前");
			synchronized (lock) {
				System.out.println("lock wait begin");
				lock.wait();
				System.out.println("lock wait end");
			}
			System.out.println("syn之后");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
