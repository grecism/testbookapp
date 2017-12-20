package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_27_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日下午12:02:05
 */
public class Thread01Ch02_27_Test {
	/**2.2.10 数据类型String的常量池特性**/
	//在JVM中具有String常量池缓存的功能。
	//2.2.10 数据类型String的常量池特性
	//-1结果为true。
	//-1String常量池缓存。
	public static void main(String[] args) {
		String a = "a";
		String b = "a";
		System.out.println(a == b);
		
	}
}
