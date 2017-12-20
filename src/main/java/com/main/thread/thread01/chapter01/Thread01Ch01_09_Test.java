package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread08_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月4日上午10:21:45
 */
public class Thread01Ch01_09_Test {
	//1.2.3实例变量与线程安全
	//-5排队进入方法
	//-5解决实现非线程安全的环境为线程安全 
	public static void main(String[] args) {
		Thread01Ch01_08_LoginA  loginA = new Thread01Ch01_08_LoginA();
		loginA.start();
		Thread01Ch01_08_LoginB  loginB = new Thread01Ch01_08_LoginB();
		loginB.start();
	}
}

class PracticeThread09_LoginServlet{
	private static String usernameRef;
	private static String passwordRef;
	
	synchronized public static void doPost(String username,String password){
		try {
			usernameRef = username;
			if("a".equals(username)){
				Thread.sleep(5000);
			}
			passwordRef = password;
			//System.out.println("username="+usernameRef+" password="+passwordRef);
			System.out.println("username="+usernameRef+" password="+password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
