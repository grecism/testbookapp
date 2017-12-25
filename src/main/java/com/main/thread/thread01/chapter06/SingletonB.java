package com.main.thread.thread01.chapter06;

import java.io.Serializable;

/**
 * 
 *<p>Title	: Thread01Ch06_05_Object</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午5:23:52
 */
public class SingletonB implements Serializable{
	private static SingletonB SINGLETON_B;
	private SingletonB() {
		
	}
	//设置同步方法效率太低了,整个方法被上锁。
	synchronized public static SingletonB getInstance(){
		try {
			//延迟加载
			if(SINGLETON_B != null){
			}else{
				Thread.sleep(3000);
				SINGLETON_B = new SingletonB();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return SINGLETON_B;
	}
	
	public Object readResolve(){
		System.out.println("调用了readResolve方法");
		return SINGLETON_B;
		
	}
}
