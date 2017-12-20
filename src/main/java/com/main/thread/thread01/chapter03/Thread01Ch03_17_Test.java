package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_17_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月18日下午6:17:39
 */
public class Thread01Ch03_17_Test {
	//3.1.11 生产者/消费者模式实现
	//-3多生产与多消费:操作值正常运行
	//-3将生产者和消费者中的notify改成notifyAll,不光通知同类线程,也包括异类。
	public static void main(String[] args) {
		try {
			String lock = new String();
			Thread01Ch03_17_P p = new Thread01Ch03_17_P(lock);
			Thread01Ch03_17_C c = new Thread01Ch03_17_C(lock);
			Thread01Ch03_17_Thread_P[] tpArray = new Thread01Ch03_17_Thread_P[2];
			Thread01Ch03_17_Thread_C[] tcArray = new Thread01Ch03_17_Thread_C[2];
			for (int i = 0; i < 2; i++) {
				tpArray[i] = new Thread01Ch03_17_Thread_P(p);
				tpArray[i].setName("生产者"+ (i+1));
				tcArray[i] = new Thread01Ch03_17_Thread_C(c);
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

class Thread01Ch03_17_Object{
	public static String value = "";
}

class Thread01Ch03_17_P{
	private String lock;
	public Thread01Ch03_17_P(String lock) {
		this.lock = lock;
	}
	public void setValue(){
		try {
			synchronized (lock) {
				while(!"".equals(Thread01Ch03_17_Object.value)){
					System.out.println(" 生产者"+Thread.currentThread().getName()+" waiting了###");
					lock.wait();
				}
				Thread.sleep(3000);
				System.out.println(" 生产者"+Thread.currentThread().getName()+" runtime了");
				String value=""+System.currentTimeMillis()+"_"+System.nanoTime();
				Thread01Ch03_16_Object.value = value;
				lock.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_17_C{
	private String lock;
	public Thread01Ch03_17_C(String lock) {
		this.lock = lock;
	}
	public void getValue(){
		try {
			synchronized (lock) {
				while("".equals(Thread01Ch03_17_Object.value)){
					System.out.println(" 消费者"+Thread.currentThread().getName()+" waiting了$$$");
					lock.wait();
				}
				System.out.println(" 消费者"+Thread.currentThread().getName()+" runtime了");
				Thread01Ch03_16_Object.value ="";
				lock.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_17_Thread_P extends Thread{
	private Thread01Ch03_17_P p;
	public Thread01Ch03_17_Thread_P(Thread01Ch03_17_P p) {
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

class Thread01Ch03_17_Thread_C extends Thread{
	private Thread01Ch03_17_C c;
	public Thread01Ch03_17_Thread_C(Thread01Ch03_17_C c) {
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
