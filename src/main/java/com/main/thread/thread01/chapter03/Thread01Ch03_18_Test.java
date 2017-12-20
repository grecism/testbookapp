package com.main.thread.thread01.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	: Thread01Ch03_18_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月18日下午6:28:55
 */
public class Thread01Ch03_18_Test {
	//3.1.11 生产者/消费者模式实现
	//使生产者向堆栈list对象中放入数据,使消费者从list堆栈中取出数据。list最大容量是1。
	//-4一生产与一消费:操作栈
	//-4程序运行结果是size()不会大于1,通过使用生产者/消费者模式,容器size()的值不会大于1,这是本示例想要实现的效果,值在0和1之间进行交替,也就是生产和消费
	//这两个过程在交替执行。
	public static void main(String[] args) {
		Thread01Ch03_18_stack stack = new Thread01Ch03_18_stack();
		Thread01Ch03_18_P p = new Thread01Ch03_18_P(stack);
		Thread01Ch03_18_C c = new Thread01Ch03_18_C(stack);
		Thread01Ch03_18_Thread_P tp = new Thread01Ch03_18_Thread_P(p);
		Thread01Ch03_18_Thread_C tc = new Thread01Ch03_18_Thread_C(c);
		tp.start();
		tc.start();
	}
}

class Thread01Ch03_18_stack{
	private List list = new ArrayList();
	synchronized public void push(){
		try {
			if(list.size() == 1){
				this.wait();
			}
			list.add("anyString="+Math.random());
			this.notify();
			System.out.println("push="+list.size());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public String pop(){
		String returnString = "";
		try {
			if(list.size() == 0){
				System.out.println("pop操作中的:"+Thread.currentThread().getName()+"wait了");
				this.wait();
			}
			returnString = (String) list.get(0);
			list.remove(0);
			this.notify();
			System.out.println("pop="+list.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return returnString;
	}
}

class Thread01Ch03_18_P{
	private Thread01Ch03_18_stack stack;
	public Thread01Ch03_18_P(Thread01Ch03_18_stack stack) {
		this.stack = stack;
	}
	public void pushService(){
		stack.push();
	}
}

class Thread01Ch03_18_C{
	private Thread01Ch03_18_stack stack;
	public Thread01Ch03_18_C(Thread01Ch03_18_stack stack) {
		this.stack = stack;
	}
	public void popService(){
		System.out.println("pop="+stack.pop());
	}
}

class Thread01Ch03_18_Thread_P extends Thread{
	private Thread01Ch03_18_P p;
	public Thread01Ch03_18_Thread_P(Thread01Ch03_18_P p) {
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

class Thread01Ch03_18_Thread_C extends Thread{
	private Thread01Ch03_18_C c;
	public Thread01Ch03_18_Thread_C(Thread01Ch03_18_C c) {
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