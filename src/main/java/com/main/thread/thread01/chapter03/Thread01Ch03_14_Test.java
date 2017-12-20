package com.main.thread.thread01.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	: Thread01Ch03_14_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月15日下午6:12:00
 */
public class Thread01Ch03_14_Test {
	/**3.1.10 等待wait的条件发生变化**/
	//3.1.10 等待wait的条件发生变化
	//-1运行结果异常
	//-1运行结果异常
	//-2运行结果正常
	//-2运行结果正常
	public static void main(String[] args) {
		try {
			String lock = new String("");
			Thread01Ch03_14_Add add = new Thread01Ch03_14_Add(lock);
			Thread01Ch03_14_Subtract subtract = new Thread01Ch03_14_Subtract(lock);
			Thread01Ch03_14_ThreadSubtract t1 = new Thread01Ch03_14_ThreadSubtract(subtract);
			t1.start();
			Thread01Ch03_14_ThreadSubtract t2 = new Thread01Ch03_14_ThreadSubtract(subtract);
			t2.start();
			Thread.sleep(3000);
			Thread01Ch03_14_ThreadAdd t3 = new Thread01Ch03_14_ThreadAdd(add);
			t3.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

class Thread01Ch03_14_Object{
	public static List list = new ArrayList();
}
class Thread01Ch03_14_Add{
	private String lock;
	public Thread01Ch03_14_Add(String lock) {
		this.lock = lock;
	}
	
	public void add(){
		synchronized (lock) {
			Thread01Ch03_14_Object.list.add("string");
			//lock.notify();
			lock.notifyAll();
		}
	}
}

class Thread01Ch03_14_Subtract{
	private String lock;
	public Thread01Ch03_14_Subtract(String lock) {
		this.lock = lock;
	}
	
	public void subtract(){
		try {
			synchronized (lock) {
				if(Thread01Ch03_14_Object.list.size() == 0){
					System.out.println("threadname="+Thread.currentThread().getName()+"subtract begin wait");
					lock.wait();
					System.out.println("threadname="+Thread.currentThread().getName()+"subtract end wait");
				}

			}
			Thread01Ch03_14_Object.list.remove(0);
			System.out.println("list size="+Thread01Ch03_14_Object.list.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_14_ThreadAdd extends Thread{
	private  Thread01Ch03_14_Add add;
	public Thread01Ch03_14_ThreadAdd(Thread01Ch03_14_Add add) {
		this.add = add;
	}
	@Override
	public void run() {
		super.run();
		add.add();
	}
}

class Thread01Ch03_14_ThreadSubtract extends Thread{
	private  Thread01Ch03_14_Subtract subtract;
	public Thread01Ch03_14_ThreadSubtract(Thread01Ch03_14_Subtract subtract) {
		this.subtract = subtract;
	}
	@Override
	public void run() {
		super.run();
		subtract.subtract();
	}
}