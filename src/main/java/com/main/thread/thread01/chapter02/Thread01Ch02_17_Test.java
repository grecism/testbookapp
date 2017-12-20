package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_17_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月8日下午4:30:48
 */
public class Thread01Ch02_17_Test {
	/**2.2.7 将任意对象作为对象监视器**/
	//多个线程调用同一个对象中的不同名称的synchronized同步方法或synchronized(this)同步代码块时,调用的效果就是按顺序执行,也就是同步的,阻塞的。
	//这说明synchronized同步方法或synchronized(this)同步代码块分别有两种作用。
	//1.synchronized同步方法
	//(1)对其他synchronized同步方法或synchronized(this)同步代码块调用呈阻塞状态。
	//(2)同一时间只有一个线程可以执行synchronized同步方法中的代码。
	//2.synchronized同步代码块
	//(1)对其他synchronized同步方法或synchronized(this)同步代码块调用呈阻塞状态。
	//(2)同一时间只有一个线程可以执行synchronized(this)同步方法中的代码。
	//在前面的学习中,使用synchronized(this)格式来同步代码块,其实java还支持对"任意对象"作为"对象监视器"来实现同步的功能。这个"任意对象"大多是
	//实例变量及方法的参数,使用格式为synchronized(非this对象)。synchronized(非this对象)格式的作用只有1种:synchronized(非this对象x)同步代码块。
	//(1)在多个线程持有"对象监视器"为同一个对象的前提下,同一时间只有一个线程可以执行synchronized(非this对象x)同步代码块中的代码。
	//(2)当持有"对象监视器"为同一个对象的前提下,同一时间只有一个线程可以执行synchronized(非this对象x)同步代码块中的代码。
	//2.2.7 将任意对象作为对象监视器
	//-1执行结果是同步效果
	//-2不是同步的而是异步(因为不是同一个锁)
	//-1锁非this对象具有一定的优点:如果在一个类中有很多个synchronized方法,这时虽然能实现同步,但会受到阻塞,所以影响运行效率;但如果使用同步代码块,
	//锁非this对象,则synchronized(非this)代码块中的程序与同步方法时异步的,不与其他锁this同步方法争抢this锁,则可大大提高运行效率。
	//-2可见,使用"synchronized(非this对象x)同步代码块"格式进行同步操作时,对象监视器必须是同一个对象。如果不是同一个对象监视器,运行的
	//结果就是异步调用了,就会交叉运行。
	public static void main(String[] args) {
		Thread01Ch02_17_Service service = new Thread01Ch02_17_Service();
		Thread01Ch02_17_A a = new Thread01Ch02_17_A(service);
		a.setName("A");
		a.start();
		Thread01Ch02_17_B b = new Thread01Ch02_17_B(service);
		b.setName("B");
		b.start();
	
		
	}
}

class Thread01Ch02_17_Service {
	private String username;
	private String password;
	//private String anyString = new String();
	
	public void setUsernamePassword(String username,String password){
		try {
			String anyString = new String();
			synchronized (anyString) {
				System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"进入同步块");
				this.username = username;
				Thread.sleep(3000);
				this.password = password;
				System.out.println("threadname="+Thread.currentThread().getName()+"在"+System.currentTimeMillis()+"离开同步块");

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_17_A extends Thread{
	private Thread01Ch02_17_Service service;
	
	public Thread01Ch02_17_A(Thread01Ch02_17_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.setUsernamePassword("a", "aa");
	}
}

class Thread01Ch02_17_B extends Thread{
	private Thread01Ch02_17_Service service;
	
	public Thread01Ch02_17_B(Thread01Ch02_17_Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		super.run();
		service.setUsernamePassword("b", "bb");
	}
}