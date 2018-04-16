package com.main.thread.thread01.chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	: Thread01Ch03_22_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月19日下午2:49:20
 */
public class Thread01Ch03_22_Test {
	//多生产多消费
	//性能较低
	public static void main(String[] args) {
		Thread01Ch03_22_Salesperson salesperson = new Thread01Ch03_22_Salesperson();
		Thread01Ch03_22_Thread_P p = new Thread01Ch03_22_Thread_P(salesperson);
		Thread01Ch03_22_Thread_C c = new Thread01Ch03_22_Thread_C(salesperson);
		for (int i = 0; i < 10; i++) {
			new Thread(p,"生产者"+i).start();
			new Thread(c,"消费者"+i).start();
		}
	}
}

//产品类
class Thread01Ch03_22_Product{
	private String name;
	public Thread01Ch03_22_Product(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

//售货员
class Thread01Ch03_22_Salesperson{
	private List<Thread01Ch03_22_Product> store = new ArrayList<Thread01Ch03_22_Product>();
	
	synchronized public void receiveProduct(Thread01Ch03_22_Product p){
		try {
			while(store.size() >=5 ){
				this.wait();
			}
			
			store.add(p);
			this.notifyAll();
			System.out.printf("-->库存状态(%d) 新产品(%s)%n", store.size(), p.getName());//%n换行
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void buyProduct(){
		try {
			while(store.size() == 0){
				this.wait();
			}
			Thread01Ch03_22_Product p = store.remove(0);
			this.notifyAll();
			 System.out.printf("<--库存状态(%d) 取走产品(%s)%n", store.size(), p.getName());  
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//生产者
class Thread01Ch03_22_Thread_P implements Runnable{
	private Thread01Ch03_22_Salesperson salesperson;
	public Thread01Ch03_22_Thread_P(Thread01Ch03_22_Salesperson salesperson) {
		this.salesperson = salesperson;
	}
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			Thread01Ch03_22_Product p = new Thread01Ch03_22_Product(Thread.currentThread().getName()+"产品"+i);
			salesperson.receiveProduct(p);
		}
	}
	
}

//消费者
class Thread01Ch03_22_Thread_C implements Runnable{
	private Thread01Ch03_22_Salesperson salesperson;
	public Thread01Ch03_22_Thread_C(Thread01Ch03_22_Salesperson salesperson) {
		this.salesperson = salesperson;
	}
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			salesperson.buyProduct();
		}
	}
	
}







