package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_43_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月14日下午4:42:31
 */
public class Thread01Ch02_43_Test {
	/**2.3.7 synchronized代码块有volatile同步的功能**/
	//2.3.7 synchronized代码块有volatile同步的功能
	//关键字synchronized可以使多个线程访问同一个资源具有同步性,而且它还具有将线程工作内存中的私有变量与公共内存中的变量同步的功能。
	//-1应该出现死循环 以-server服务器模式运行(没有出现)
	//-1死循环的情况是由于各线程间的数据值没有可视性造成的,关键字可以具有可视性。
	//-2解决死循环
	//-2关键字synchronized可以保证在同一时刻,只有一个线程可以执行某一个方法或某一个代码块。它包含两个特征:互斥性和可见性。同步synchronized不仅可以解决
	//一个线程看到对象处于不一致的状态,还可以保证进入同步方法或者同步代码块的每个线程,都看到由同一个锁保护之前所有的修改效果。
	//多线程并发,要着重"外练互斥,内修可见"。
	public static void main(String[] args) {
		try {
			Thread01Ch02_43_Service service = new Thread01Ch02_43_Service();
			Thread01Ch02_43_Thread_A a = new Thread01Ch02_43_Thread_A(service);
			a.start();
			Thread01Ch02_43_Thread_B b = new Thread01Ch02_43_Thread_B(service);
			b.start();
			Thread.sleep(1000);
			System.out.println("已经发起停止命令");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_43_Service{
	private boolean flag = true;
	
	/*public void runMethod(){
		while(flag == true){
			
		}
		System.out.println("已经停止运行了");
	}*/
	
	public void runMethod(){
		String anyString = new String();
		while(flag == true){
			synchronized (anyString) {
				
			}
		}
		System.out.println("已经停止运行了");
	}
	
	public void stopMethod(){
		flag = false;
	}
}

class Thread01Ch02_43_Thread_A extends Thread{
	private Thread01Ch02_43_Service service;
	
	public Thread01Ch02_43_Thread_A(Thread01Ch02_43_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.runMethod();
	}
}

class Thread01Ch02_43_Thread_B extends Thread{
	private Thread01Ch02_43_Service service;
	
	public Thread01Ch02_43_Thread_B(Thread01Ch02_43_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.stopMethod();
	}
}














