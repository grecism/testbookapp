package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_38_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月13日下午4:42:56
 */
public class Thread01Ch02_38_Test {
	/**2.3.2 解决同步死循环**/
	//2.3.2 解决同步死循环
	//-1程序被停止了
	//-1但当上面的代码的格式运行在-server服务器模式中64bit的JVM上时,会出现死循环。解决办法是使用volatile关键字。关键字volatile的作用是强制从公共
	//堆栈中取得变量的值,而不是从线程私有数据栈中取得变量的值。
	public static void main(String[] args) {
		try {
			Thread01Ch02_38_Object object = new Thread01Ch02_38_Object();
			new Thread(object,"A").start();
			Thread.sleep(5000);
			System.out.println("我要停止它！ stopthreadname="+Thread.currentThread().getName());
			object.setFlag(false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

class Thread01Ch02_38_Object implements Runnable{
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
	@Override
	public void run() {
		printMethod();
	}
	
	
	
}