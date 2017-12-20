package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_37_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日下午5:45:45
 */
public class Thread01Ch02_37_Test {
	/**2.3  volatile关键字**/
	//关键字volatile的主要作用是使变量在多个线程间可见。
	/**2.3.1 volatile关键字与死循环**/
	//2.3.1 volatile关键字与死循环
	//-1停不下来的程序
	//-1停不下来的原因主要就是main线程一直在处理while()循环,导致程序不能继续执行后面的代码。解决办法是用多线程技术。
	public static void main(String[] args) {
		Thread01Ch02_37_Object object = new Thread01Ch02_37_Object();
		object.printMethod();
		System.out.println("我要停止它！ stopthreadname="+Thread.currentThread().getName());
		object.setFlag(false);
	}
}

class Thread01Ch02_37_Object{
	private boolean flag = true;
	public boolean isFlag(){
		return flag;
	}
	public void setFlag(boolean flag){
		this.flag = flag;
	}
	public void printMethod(){
		try {
			while(flag == true){
				System.out.println("threadname="+Thread.currentThread().getName());
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}