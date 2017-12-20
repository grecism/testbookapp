package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread28_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日下午6:46:06
 */
public class Thread01Ch01_28_Test {
	/**1.7.7 释放锁的不良后果**/
	//1.7.7 释放锁的不良后果
	//-1强制stop造成数据不一致。程序运行的结果出现值不同步的情况,运行结果如下:b===aa。
	//-1使用stop()释放锁将会给数据造成不一致性的结果。如果出现这样的情况,程序处理的数据就有可能遭到破坏,最终导致程序执行的流程错误。
	//由于stop()方法在jdk中已经被标明为"作废/过期"的方法,显然它在功能上具有缺陷,所以不建议在程序中使用stop()方法。
	public static void main(String[] args) {
		try {
			Thread01Ch01_28_SynchronizedObject object = new Thread01Ch01_28_SynchronizedObject();
			Thread01Ch01_28_Thread thread = new Thread01Ch01_28_Thread(object);
			thread.start();
			Thread.sleep(500);
			thread.stop();
			System.out.println(object.getUsername()+"==="+object.getPassword());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch01_28_SynchronizedObject{
	private String username = "a";
	private String password = "aa";
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	synchronized public void printString(String username,String password){
		try {
			this.username = username;
			Thread.sleep(10000);
			this.password = password;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch01_28_Thread extends Thread{
	private Thread01Ch01_28_SynchronizedObject object;
	
	public Thread01Ch01_28_Thread(Thread01Ch01_28_SynchronizedObject object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		super.run();
		object.printString("b", "bb");
	}
}
