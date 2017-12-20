package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_26_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月19日下午5:25:39
 */
public class Thread01Ch03_26_Test {
	/**3.14 实战:等待/通知之交叉备份**/
	//继续学习等待/通知相关知识点,创建20个线程,其中10个线程是将数据备份到A数据库中,另外10个线程将数据备份到B数据库中,并且备份A数据库和B数据库是交叉进行的。
	//3.14 实战:等待/通知之交叉备份
	//-1打印的效果是交替运行的。
	//-1flag变量的主要作用就是确保备份数据A首先执行,然后与数据库B交替进行备份。
	//交替打印的原理就是使用如下代码作为标记:volatile private boolean flag = false;实现了A和B线程交替备份的效果。
	public static void main(String[] args) {
		Thread01Ch03_26_DBTools dbTools = new Thread01Ch03_26_DBTools();
		for (int i = 0; i < 10; i++) {
			Thread01Ch03_26_Thread_A ta = new Thread01Ch03_26_Thread_A(dbTools);
			ta.start();
			Thread01Ch03_26_Thread_B tb = new Thread01Ch03_26_Thread_B(dbTools);
			tb.start();
		}
		
	}
}

class Thread01Ch03_26_DBTools{
	volatile private boolean flag = false;
	synchronized public void backUpA(){
		try {
			while(flag == true){
				this.wait();
			}
			System.out.println(Thread.currentThread().getName()+"backUpA begin");
			for (int i = 0; i < 5; i++) {
				System.out.println("##########");
			}
			flag = true;
			this.notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void backUpB(){
		try {
			while(flag == false){
				this.wait();
			}
			System.out.println(Thread.currentThread().getName()+"backUpB begin");
			for (int i = 0; i < 5; i++) {
				System.out.println("$$$$$$$$$$");
			}
			flag = false;
			this.notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_26_Thread_A extends Thread{
	private Thread01Ch03_26_DBTools dbtools;
	public Thread01Ch03_26_Thread_A(Thread01Ch03_26_DBTools dbtools) {
		this.dbtools = dbtools;
	}
	@Override
	public void run() {
		super.run();
		dbtools.backUpA();
	}
}

class Thread01Ch03_26_Thread_B extends Thread{
	private Thread01Ch03_26_DBTools dbtools;
	public Thread01Ch03_26_Thread_B(Thread01Ch03_26_DBTools dbtools) {
		this.dbtools = dbtools;
	}
	@Override
	public void run() {
		super.run();
		dbtools.backUpB();
	}
}













