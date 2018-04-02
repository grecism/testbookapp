package com.main.thread.thread01.chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	: Thread01Ch02_20_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月8日下午6:53:24
 */
public class Thread01Ch02_20_Test {
	//2.2.7 将任意对象作为对象监视器
	//脏读的情况
	//-5无序性带来的错误结果 list-2
	//-5"脏读"出现了。出现的原因是两个线程以异步的方式返回list参数的size()大小。解决办法就是"同步化"。
	//-6正确的运行结果 list=1
	//-6由于list参数对象在项目中是一份实例,是单例的,而且也正需要对list参数的getSize()方法做同步的调用,所以就对list参数做同步处理。
	public static void main(String[] args) {
		try {
			Thread01Ch02_20_MyOneList list = new Thread01Ch02_20_MyOneList();
			String anyString = new String();
			Thread01Ch02_20_A a = new Thread01Ch02_20_A(list,anyString);
			a.setName("A");
			a.start();
			Thread01Ch02_20_B b = new Thread01Ch02_20_B(list,anyString);
			b.setName("B");
			b.start();
			Thread.sleep(6000);
			System.out.println("list="+list.getSize());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_20_Service {
	private String string = new String();
    public Thread01Ch02_20_MyOneList method(Thread01Ch02_20_MyOneList list,String anyString,String data){
		try {
			/*System.out.println("begin="+list.getSize());
			if(list.getSize() < 1){
				Thread.sleep(2000);//模拟从远程花费2秒取回数据
				list.add(data);
				System.out.println("end="+list.getSize());
			}*///异步方式执行
			
			/*synchronized (string) {
				System.out.println("begin="+list.getSize());
				if(list.getSize() < 1){
					Thread.sleep(2000);//模拟从远程花费2秒取回数据
					list.add(data);
					System.out.println("end="+list.getSize());
				}
			}*///异步方式执行 不是同一把锁
			
			/*synchronized (anyString) {
				System.out.println("begin="+list.getSize());
				if(list.getSize() < 1){
					Thread.sleep(2000);//模拟从远程花费2秒取回数据
					list.add(data);
					System.out.println("end="+list.getSize());
				}
			}*/ //同步执行 是同一把锁
			
			synchronized (list) {
				System.out.println("begin="+list.getSize());
				if(list.getSize() < 1){
					Thread.sleep(2000);//模拟从远程花费2秒取回数据
					list.add(data);
					System.out.println("end="+list.getSize());
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return list;
		
	}
}

class Thread01Ch02_20_MyOneList {
	private List list = new ArrayList();
	
	synchronized public void add(String data){
		list.add(data);
	}
	
	synchronized public int getSize(){
		return list.size();
	}
}

class Thread01Ch02_20_A extends Thread{
	private Thread01Ch02_20_MyOneList list;
	private String anyString;
	
	public Thread01Ch02_20_A(Thread01Ch02_20_MyOneList list,String anyString) {
		this.list = list;
		this.anyString = anyString;
	}
	
	@Override
	public void run() {
		super.run();
		Thread01Ch02_20_Service service = new Thread01Ch02_20_Service();
		service.method(list,anyString, "A");
	}
}

class Thread01Ch02_20_B extends Thread{
	private Thread01Ch02_20_MyOneList list;
	private String anyString;
	
	public Thread01Ch02_20_B(Thread01Ch02_20_MyOneList list,String anyString) {
		this.list = list;
		this.anyString = anyString;
	}
	
	@Override
	public void run() {
		super.run();
		Thread01Ch02_20_Service service = new Thread01Ch02_20_Service();
		service.method(list,anyString, "B");
	}
}

