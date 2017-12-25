package com.main.thread.thread01.chapter06;

import java.io.Serializable;

/**
 * 
 *<p>Title	: Thread01Ch06_05_Object</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午5:23:52
 */
public class SingletonA implements Serializable{
	private SingletonA() {
	}
	//内部类方式
	private static class SingletonAHandler{
		private static final SingletonA SINGLETON_A = new SingletonA();
	}
	public static SingletonA getInstance(){
		return SingletonAHandler.SINGLETON_A;
		
	}
	public Object readResolve(){
		System.out.println("调用了readResolve方法");
		return SingletonAHandler.SINGLETON_A;
		
	}
}
