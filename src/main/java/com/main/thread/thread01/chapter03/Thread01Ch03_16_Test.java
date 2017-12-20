package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_16_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月18日下午3:00:13
 */
public class Thread01Ch03_16_Test {
	//3.1.11 生产者/消费者模式实现
	//-2多生产与多消费:操作值假死
	//-2"假死"的现象其实就是线程进入waiting等待状态。如果全部线程都进入waiting状态,则程序就不在执行任何业务功能了,整个项目呈停止状态。
	//在代码中确实已经通过wait/notify进行通信了,但不保证notify唤醒的是异类,也许是同类,比如"生产者"唤醒"生产者",或"消费者"唤醒"消费者"这样的情况。如果
	//按这样的情况运行的比率积少成多,就会导致所有的线程都不能继续运行下去,大家都在等待,都呈waiting状态,程序最后也就呈"假死"状态,不能继续运行下去了。
	public static void main(String[] args) {
		try {
			String lock = new String();
			Thread01Ch03_16_P p = new Thread01Ch03_16_P(lock);
			Thread01Ch03_16_C c = new Thread01Ch03_16_C(lock);
			Thread01Ch03_16_Thread_P[] tpArray = new Thread01Ch03_16_Thread_P[2];
			Thread01Ch03_16_Thread_C[] tcArray = new Thread01Ch03_16_Thread_C[2];
			for (int i = 0; i < 2; i++) {
				tpArray[i] = new Thread01Ch03_16_Thread_P(p);
				tpArray[i].setName("生产者"+ (i+1));
				tcArray[i] = new Thread01Ch03_16_Thread_C(c);
				tcArray[i].setName("消费者"+ (i+1));
				tcArray[i].start();
				tpArray[i].start();
				
			}
			Thread.sleep(10000);
			Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
			Thread.currentThread().getThreadGroup().enumerate(threadArray);
			for (int i = 0; i < threadArray.length; i++) {
				System.out.println("threadname="+threadArray[i].getName()+" threadstate="+threadArray[i].getState());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_16_Object{
	public static String value = "";
}

class Thread01Ch03_16_P{
	private String lock;
	public Thread01Ch03_16_P(String lock) {
		this.lock = lock;
	}
	public void setValue(){
		try {
			synchronized (lock) {
				while(!"".equals(Thread01Ch03_16_Object.value)){
					System.out.println(" 生产者"+Thread.currentThread().getName()+" waiting了###");
					lock.wait();
				}
				System.out.println(" 生产者"+Thread.currentThread().getName()+" runtime了");
				String value=""+System.currentTimeMillis()+"_"+System.nanoTime();
				Thread01Ch03_16_Object.value = value;
				lock.notify();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_16_C{
	private String lock;
	public Thread01Ch03_16_C(String lock) {
		this.lock = lock;
	}
	public void getValue(){
		try {
			synchronized (lock) {
				while("".equals(Thread01Ch03_16_Object.value)){
					System.out.println(" 消费者"+Thread.currentThread().getName()+" waiting了$$$");
					lock.wait();
				}
				System.out.println(" 消费者"+Thread.currentThread().getName()+" runtime了");
				Thread01Ch03_16_Object.value ="";
				lock.notify();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_16_Thread_P extends Thread{
	private Thread01Ch03_16_P p;
	public Thread01Ch03_16_Thread_P(Thread01Ch03_16_P p) {
		this.p = p;
	}
	@Override
	public void run() {
		super.run();
		while(true){
			p.setValue();
		}
	}
}

class Thread01Ch03_16_Thread_C extends Thread{
	private Thread01Ch03_16_C c;
	public Thread01Ch03_16_Thread_C(Thread01Ch03_16_C c) {
		this.c = c;
	}
	@Override
	public void run() {
		super.run();
		while(true){
			c.getValue();
		}
	}
}



















