package com.main.thread.thread01.chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	: Thread01Ch02_19_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月8日下午5:48:56
 */
public class Thread01Ch02_19_Test {
	//2.2.7 将任意对象作为对象监视器
	//同步代码块放在非同步synchronized方法中进行声明,并不能保证调用方法的线程的执行同步/顺序性,也就是线程调用方法的顺序是无序的,虽然在
	//同步块中执行的顺序是同步的,这样极易出现"脏读"问题。使用"synchronized(非this对象x)同步代码块"格式也可以解决"脏读"问题。
	//验证多个线程调用同一个方法时随机的。
	//-4两个线程随机执行同一个方法
	//-4同步块中的代码是同时打印的,当前线程的"执行"与"退出"是成对出现的。但线程A和线程B的执行却是异步的,这就有可能出现"脏读"的环境。由于线程
	//执行方法的顺序不确定,所以当A和B两个线程执行带有分支判断的方法时,就会出现逻辑上的错误,有可能出现脏读。
	public static void main(String[] args) {
		Thread01Ch02_19_MyList list = new Thread01Ch02_19_MyList();
		Thread01Ch02_19_A a = new Thread01Ch02_19_A(list);
		a.setName("A");
		a.start();
		Thread01Ch02_19_B b = new Thread01Ch02_19_B(list);
		b.setName("B");
		b.start();
	}
}

class Thread01Ch02_19_MyList {
	private List list = new ArrayList();
	synchronized public void add(String username){
		System.out.println("threadname="+Thread.currentThread().getName()+"执行了add方法!");
		list.add(username);
		System.out.println("threadname="+Thread.currentThread().getName()+"退出了add方法!");
	}
	
	synchronized public int getSize(){
		System.out.println("threadname="+Thread.currentThread().getName()+"执行了getSize方法!");
		int sizeValue= list.size();
		System.out.println("threadname="+Thread.currentThread().getName()+"退出了getSize方法!");
		return sizeValue;
	}
}

class Thread01Ch02_19_A extends Thread{
	private Thread01Ch02_19_MyList list;
	
	public Thread01Ch02_19_A(Thread01Ch02_19_MyList list) {
		this.list = list;
	}
	
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 10000; i++) {
			list.add("threadA"+(i+1));
		}
	}
}

class Thread01Ch02_19_B extends Thread{
	private Thread01Ch02_19_MyList list;
	
	public Thread01Ch02_19_B(Thread01Ch02_19_MyList list) {
		this.list = list;
	}
	
	@Override
	public void run() {
		super.run();
		for (int i = 0; i < 10000; i++) {
			list.add("threadB"+(i+1));
		}
	}
}