package com.main.thread.thread01.chapter03;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 *<p>Title	: Thread01Ch03_23_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月19日下午3:36:13
 */
public class Thread01Ch03_23_Test {
	//多生产多消费
	//使用BlockingQueue阻塞队列来实现，当产品放满货架时BlockingQueue就会阻塞，当产品被取完也会进入阻塞。
	public static void main(String[] args) {
		// 声明一个容量为5的缓存队列
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(5);//最多存放5个产品
        Thread01Ch03_23_Thread_P p = new Thread01Ch03_23_Thread_P(queue);
		Thread01Ch03_23_Thread_C c = new Thread01Ch03_23_Thread_C(queue);
		for (int i = 0; i < 10; i++) {
			new Thread(p,"生产者"+i).start();
			new Thread(c,"消费者"+i).start();
		}
	}
}

//产品类
class Thread01Ch03_23_Product{
	private String name;
	public Thread01Ch03_23_Product(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

//生产者
class Thread01Ch03_23_Thread_P implements Runnable{
	private  BlockingQueue<String> queue;
	public Thread01Ch03_23_Thread_P(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				queue.put(Thread.currentThread().getName()+"_产出的产品"+i);
				System.out.println(Thread.currentThread().getName()+":产出了["+Thread.currentThread().getName()+"_产出的产品"+i+"]");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

//消费者
class Thread01Ch03_23_Thread_C implements Runnable{
	private  BlockingQueue<String> queue;
	public Thread01Ch03_23_Thread_C(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName()+":消费了"+"["+queue.take()+"]");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}