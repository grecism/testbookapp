package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread19_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午4:18:31
 */
public class Thread01Ch01_19_Test {
	//1.7.2  判断线程是否是停止状态
	//-3程序运行结果如下:
	//		是否停止1?true
	//		是否停止2?false
	//		end!
	//为什么第二个值是false？
	//官方文档中对interrupted方法的解释:
	//测试当前线程是否已经中断。线程的中断状态由该方法清除。换句话说,如果连续两次调用该方法,则第二次调用将返回false。
	//(在第一次调用已清除了其中断状态之后,且第二次调用检查完中断状态前,当前线程再次中断的情况除外)。
	//interrupted()方法具有清除状态的功能,所以第二次调用interrupted()方法返回的值是false。
	public static void main(String[] args) {
		Thread.currentThread().interrupt();
		System.out.println("是否停止1?"+Thread.interrupted());
		System.out.println("是否停止2?"+Thread.interrupted());
		System.out.println("end!");
	}
}
