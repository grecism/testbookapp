package com.main.thread.thread01.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	: Thread01Ch03_01_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月14日下午5:09:21
 */
public class Thread01Ch03_01_Test {
	/**3 线程间通信**/
	//线程是操作系统中独立的个体,但这些个体如果不经过特殊的处理就不能成为一个整体。线程间的通信就是成为整体的必用方案之一,可以说,使线程间进行通信后,系统之间的
	//交互性会更强大,在大大提高CPU利用率的同时还会使我们对各线程任务在处理的过程中进行有效的把控与监督。
	//(1)使用wait/notify实现线程间的通信。
	//(2)生产者/消费者模式的实现。
	//(3)方法join的使用。
	//(4)ThreadLocal类的使用。
	/**3.1 等待/通知机制**/
	/**3.1.1 不使用等待/通知机制实现线程间通信**/
	//3.1.1 不使用等待/通知机制实现线程间通信
	//-1两个线程互相通信成功了
	//-1虽然两个线程间实现了通信,但有一个弊端就是,线程B不停地通过while语句轮询机制来检测某一个条件,这样会浪费CPU资源。如果轮询的时间间隔很小,更浪费CPU资源;
	//如果轮询的时间间隔很大,有可能会取不到想要得到的数据。所以就需要有一种机制来实现减少CPU的资源浪费,而且还可以实现在多个线程间通信,它就是"wait/notify"机制。
	public static void main(String[] args) {
		Thread01Ch03_01_MyList list = new Thread01Ch03_01_MyList();
		Thread01Ch03_01_Thread_A a = new Thread01Ch03_01_Thread_A(list);
		a.setName("A");
		a.start();
		Thread01Ch03_01_Thread_B b = new Thread01Ch03_01_Thread_B(list);
		b.setName("B");
		b.start();
	}
}

class Thread01Ch03_01_MyList{
	private List list = new ArrayList();
	
	public void add(){
		list.add("list");
	}
	
	public int size(){
		return list.size();
	}
}

class Thread01Ch03_01_Thread_A extends Thread{
	private Thread01Ch03_01_MyList myList;
	
	public Thread01Ch03_01_Thread_A(Thread01Ch03_01_MyList myList) {
		this.myList = myList;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			for (int i = 0; i < 10; i++) {
				myList.add();
				System.out.println("添加了"+(i+1)+"个元素");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_01_Thread_B extends Thread{
	private Thread01Ch03_01_MyList myList;
	
	public Thread01Ch03_01_Thread_B(Thread01Ch03_01_MyList myList) {
		this.myList = myList;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			while(true){
				System.out.println("是否执行");
				if(myList.size() == 5){
					System.out.println("==5的情况B线程退出了");
					throw new InterruptedException();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}












