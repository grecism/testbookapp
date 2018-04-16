package com.main.thread.thread01.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	: Thread01Ch03_19_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月19日上午10:24:31
 */
public class Thread01Ch03_19_Test {
	//3.1.11 生产者/消费者模式实现
	//使生产者向堆栈list对象中放入数据,使多个消费者从list堆栈中取出数据。list最大容量是1。
	//-5一生产与多消费:操作栈 出现异常索引溢出 。
	//-5因为条件发生改变时并没有得到及时的响应,所以多个呈wait状态的线程被唤醒,继而执行list.remove(0)代码而出现异常。将if改while语句即可。
	//-6一生产与多消费:操作栈 出现假死的情况。
	//-6出现假死的情况。使用notifyAll()。
	//-5-6
	//解决wait条件改变与假死。
	public static void main(String[] args) {
		Thread01Ch03_19_stack stack = new Thread01Ch03_19_stack();
		Thread01Ch03_19_P p = new Thread01Ch03_19_P(stack);
		Thread01Ch03_19_C c = new Thread01Ch03_19_C(stack);
		Thread01Ch03_19_C c2 = new Thread01Ch03_19_C(stack);
		Thread01Ch03_19_C c3 = new Thread01Ch03_19_C(stack);
		Thread01Ch03_19_C c4 = new Thread01Ch03_19_C(stack);
		Thread01Ch03_19_C c5 = new Thread01Ch03_19_C(stack);
		Thread01Ch03_19_Thread_P tp = new Thread01Ch03_19_Thread_P(p);
		tp.start();
		Thread01Ch03_19_Thread_C tc = new Thread01Ch03_19_Thread_C(c);
		Thread01Ch03_19_Thread_C tc2 = new Thread01Ch03_19_Thread_C(c2);
		Thread01Ch03_19_Thread_C tc3 = new Thread01Ch03_19_Thread_C(c3);
		Thread01Ch03_19_Thread_C tc4 = new Thread01Ch03_19_Thread_C(c4);
		Thread01Ch03_19_Thread_C tc5 = new Thread01Ch03_19_Thread_C(c5);
		tc.start();
		tc2.start();
		tc3.start();
		tc4.start();
		tc5.start();
		
		
	}
}

class Thread01Ch03_19_stack{
	private List list = new ArrayList();
	synchronized public void push(){
		try {
			/*if(list.size() == 1){
				this.wait();
			}*/
			while(list.size() == 1){
				this.wait();
			}
			list.add("anyString="+Math.random());
			//this.notify();
			this.notifyAll();
			System.out.println("push="+list.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public String pop(){
		String returnString = "";
		try {
			/*if(list.size() == 0){
				System.out.println("pop操作中的:"+Thread.currentThread().getName()+"wait了");
				this.wait();
			}*/
			while(list.size() == 0){
				System.out.println("pop操作中的:"+Thread.currentThread().getName()+"wait了");
				this.wait();
			}
			returnString = (String) list.get(0);
			list.remove(0);
			//this.notify();
			this.notifyAll();
			System.out.println("pop="+list.size());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return returnString;
	}
}

class Thread01Ch03_19_P{
	private Thread01Ch03_19_stack stack;
	public Thread01Ch03_19_P(Thread01Ch03_19_stack stack) {
		this.stack = stack;
	}
	public void pushService(){
		stack.push();
	}
}

class Thread01Ch03_19_C{
	private Thread01Ch03_19_stack stack;
	public Thread01Ch03_19_C(Thread01Ch03_19_stack stack) {
		this.stack = stack;
	}
	public void popService(){
		System.out.println("pop="+stack.pop());
	}
}

class Thread01Ch03_19_Thread_P extends Thread{
	private Thread01Ch03_19_P p;
	public Thread01Ch03_19_Thread_P(Thread01Ch03_19_P p) {
		this.p = p;
	}
	@Override
	public void run() {
		super.run();
		while(true){
			p.pushService();
		}
	}
}

class Thread01Ch03_19_Thread_C extends Thread{
	private Thread01Ch03_19_C c;
	public Thread01Ch03_19_Thread_C(Thread01Ch03_19_C c) {
		this.c = c;
	}
	@Override
	public void run() {
		super.run();
		while(true){
			c.popService();
		}
	}
}