package com.main.thread.thread01.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	: Thread01Ch03_20_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月19日下午2:42:57
 */
public class Thread01Ch03_20_Test {
	//3.1.11 生产者/消费者模式实现
	//-7多生产与一消费:操作栈
	//-7正常运行。
	public static void main(String[] args) {
		Thread01Ch03_20_stack stack = new Thread01Ch03_20_stack();
		Thread01Ch03_20_P p = new Thread01Ch03_20_P(stack);
		Thread01Ch03_20_C c = new Thread01Ch03_20_C(stack);
		Thread01Ch03_20_Thread_P[] tpArray = new Thread01Ch03_20_Thread_P[5];
		for (int i = 0; i < tpArray.length; i++) {
			tpArray[i] = new Thread01Ch03_20_Thread_P(p);
			tpArray[i].start();
		}
		Thread01Ch03_20_Thread_C tc = new Thread01Ch03_20_Thread_C(c);
		tc.start();
	}
}

class Thread01Ch03_20_stack{
	private List list = new ArrayList();
	synchronized public void push(){
		try {
			
			while(list.size() == 1){
				this.wait();
			}
			list.add("anyString="+Math.random());
			this.notifyAll();
			System.out.println("push="+list.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public String pop(){
		String returnString = "";
		try {
			while(list.size() == 0){
				System.out.println("pop操作中的:"+Thread.currentThread().getName()+"wait了");
				this.wait();
			}
			returnString = (String) list.get(0);
			list.remove(0);
			this.notifyAll();
			System.out.println("pop="+list.size());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return returnString;
	}
}

class Thread01Ch03_20_P{
	private Thread01Ch03_20_stack stack;
	public Thread01Ch03_20_P(Thread01Ch03_20_stack stack) {
		this.stack = stack;
	}
	public void pushService(){
		stack.push();
	}
}

class Thread01Ch03_20_C{
	private Thread01Ch03_20_stack stack;
	public Thread01Ch03_20_C(Thread01Ch03_20_stack stack) {
		this.stack = stack;
	}
	public void popService(){
		System.out.println("pop="+stack.pop());
	}
}

class Thread01Ch03_20_Thread_P extends Thread{
	private Thread01Ch03_20_P p;
	public Thread01Ch03_20_Thread_P(Thread01Ch03_20_P p) {
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

class Thread01Ch03_20_Thread_C extends Thread{
	private Thread01Ch03_20_C c;
	public Thread01Ch03_20_Thread_C(Thread01Ch03_20_C c) {
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