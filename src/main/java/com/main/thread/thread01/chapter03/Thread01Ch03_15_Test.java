package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_15_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月18日上午10:12:12
 */
public class Thread01Ch03_15_Test {
	/**3.1.11 生产者/消费者模式实现**/
	//3.1.11 生产者/消费者模式实现
	//-1一生产与一消费:操作值
	//-1一个生产者和一个消费者进行数据的交互,在控制台打印的日志get和set是交替运行的。
	//如果在此实验的基础上,设计出多个生产者和多个消费者,那么在运行的过程中极有可能出现"假死"的情况,也就是所有的线程都呈waiting等待状态。
	public static void main(String[] args) {
		String lock = new String("");
		Thread01Ch03_15_P p = new Thread01Ch03_15_P(lock);
		Thread01Ch03_15_C c = new Thread01Ch03_15_C(lock);
		Thread01Ch03_15_Thread_P tp = new Thread01Ch03_15_Thread_P(p);
		Thread01Ch03_15_Thread_C tc = new Thread01Ch03_15_Thread_C(c);
		tp.start();
		tc.start();
	}
}

class Thread01Ch03_15_Object{
	public static String value = "";
}

class Thread01Ch03_15_P{
	private String lock;
	public Thread01Ch03_15_P(String lock) {
		this.lock = lock;
	}
	public void setValue(){
		try {
			synchronized (lock) {
				if(!"".equals(Thread01Ch03_15_Object.value)){
					lock.wait();
				}
				String value=""+System.currentTimeMillis()+"_"+System.nanoTime();
				Thread01Ch03_15_Object.value = value;
				System.out.println("setValue的值为:"+value);
				lock.notify();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_15_C{
	private String lock;
	public Thread01Ch03_15_C(String lock) {
		this.lock = lock;
	}
	public void getValue(){
		try {
			synchronized (lock) {
				if("".equals(Thread01Ch03_15_Object.value)){
					lock.wait();
				}
				System.out.println("getValue的值为:"+Thread01Ch03_15_Object.value);
				Thread01Ch03_15_Object.value ="";
				lock.notify();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_15_Thread_P extends Thread{
	private Thread01Ch03_15_P p;
	public Thread01Ch03_15_Thread_P(Thread01Ch03_15_P p) {
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

class Thread01Ch03_15_Thread_C extends Thread{
	private Thread01Ch03_15_C c;
	public Thread01Ch03_15_Thread_C(Thread01Ch03_15_C c) {
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











