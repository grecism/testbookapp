package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_11_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月6日下午6:19:06
 */
public class Thread01Ch02_11_Test {
	/**2.2 同步语句块**/
	//用关键字synchronized声明方法在某些情况下是有弊端的,比如A线程调用同步方法执行一个长时间的任务,那么B线程则必须等待比较长时间。
	//在这样的情况下可以使用synchronized同步语句块来解决。
	/**2.2.1synchronized方法的弊端**/
	//2.2.1synchronized方法的弊端
	//证明用synchronized关键字声明方法是有弊端的。
	//-1运行结果时间较长
	//-1运行时间较长,可以使用synchronized同步块解决。
	public static void main(String[] args) {
		Thread01Ch02_11_Task task = new Thread01Ch02_11_Task();
		Thread01Ch02_11_A a = new Thread01Ch02_11_A(task);
		a.setName("a");
		a.start();
		Thread01Ch02_11_B b = new Thread01Ch02_11_B(task);
		b.setName("b");
		b.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long beginTime = Thread01Ch02_11_Util.beginTime1;
		if(Thread01Ch02_11_Util.beginTime2 < Thread01Ch02_11_Util.beginTime1){
			beginTime = Thread01Ch02_11_Util.beginTime2;
		}
		long endTime = Thread01Ch02_11_Util.endTime1;
		if(Thread01Ch02_11_Util.endTime2 > Thread01Ch02_11_Util.endTime1){
			endTime = Thread01Ch02_11_Util.endTime2;
		}
		System.out.println("耗时:"+((endTime-beginTime)/1000));
	}
}	

class Thread01Ch02_11_Task {
	private String data1;
	private String data2;
	
	synchronized public void taskMethod(){
		try {
			System.out.println("begin task");
			Thread.sleep(3000);
			data1 = "长时间处理任务返回的值data1 threadname="+Thread.currentThread().getName();
			data2 = "长时间处理任务返回的值data2 threadname="+Thread.currentThread().getName();
			System.out.println("data1="+data1);
			System.out.println("data2="+data2);
			System.out.println("end task");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_11_A extends Thread{
	private Thread01Ch02_11_Task task;
	
	public Thread01Ch02_11_A(Thread01Ch02_11_Task task) {
		this.task = task;
	}
	
	@Override
	public void run() {
		super.run();
		Thread01Ch02_11_Util.beginTime1 = System.currentTimeMillis();
		task.taskMethod();
		Thread01Ch02_11_Util.endTime1 = System.currentTimeMillis();
	}
}

class Thread01Ch02_11_B extends Thread{
	private Thread01Ch02_11_Task task;
	
	public Thread01Ch02_11_B(Thread01Ch02_11_Task task) {
		this.task = task;
	}
	
	@Override
	public void run() {
		super.run();
		Thread01Ch02_11_Util.beginTime2 = System.currentTimeMillis();
		task.taskMethod();
		Thread01Ch02_11_Util.endTime2 = System.currentTimeMillis();
	}
}

class Thread01Ch02_11_Util {
	public static long beginTime1;
	public static long endTime1;
	public static long beginTime2;
	public static long endTime2;
}
