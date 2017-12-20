package com.main.thread.thread01.chapter02;

/**
 * 
 *<p>Title	: Thread01Ch02_36_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日下午5:37:14
 */
public class Thread01Ch02_36_Test {
	//2.2.16 锁对象的改变
	//-3只要对象不变就是同步效果。
	//-3只要对象不变,即使对象的属性被改变,运行的结果还是同步的。
	public static void main(String[] args) {
		try {
			Thread01Ch02_36_Service service = new Thread01Ch02_36_Service();
			Thread01Ch02_36_UserInfo userInfo = new Thread01Ch02_36_UserInfo();
			Thread01Ch02_36_A a = new Thread01Ch02_36_A(service,userInfo);
			a.setName("A");
			a.start();
			Thread.sleep(100);
			Thread01Ch02_36_B b = new Thread01Ch02_36_B(service,userInfo);
			b.setName("B");
			b.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch02_36_Service {
	public void method(Thread01Ch02_36_UserInfo userInfo){
		synchronized (userInfo) {
			try {
				System.out.println("threadname="+Thread.currentThread().getName()+" begin");
				userInfo.setUsername("aaaaaa");
				Thread.sleep(3000);
				System.out.println("threadname="+Thread.currentThread().getName()+" end ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

class Thread01Ch02_36_A extends Thread{
	private Thread01Ch02_36_Service service;
	private Thread01Ch02_36_UserInfo userInfo;
	
	public Thread01Ch02_36_A(Thread01Ch02_36_Service service,Thread01Ch02_36_UserInfo userInfo) {
		this.service = service;
		this.userInfo = userInfo;
	}
	
	@Override
	public void run() {
		super.run();
		service.method(userInfo);
	}
}

class Thread01Ch02_36_B extends Thread{
	private Thread01Ch02_36_Service service;
	private Thread01Ch02_36_UserInfo userInfo;
	
	public Thread01Ch02_36_B(Thread01Ch02_36_Service service,Thread01Ch02_36_UserInfo userInfo) {
		this.service = service;
		this.userInfo = userInfo;
	}
	
	@Override
	public void run() {
		super.run();
		service.method(userInfo);
	}
}

class Thread01Ch02_36_UserInfo {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
