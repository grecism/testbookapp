package com.main.thread.thread01.chapter06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 *<p>Title	: Thread01Ch06_05_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午4:25:57
 */
public class Thread01Ch06_05_Test01 {
	/**6.4 序列化与反序列化的单例模式实现**/
	//静态内置类可以达到线程安全问题,但如果遇到序列化对象时,使用默认的方式运行得到的结果还是多例的。
	//6.4 序列化与反序列化的单例模式实现
	//-1控制台运行结果不相同,并且返回false。
	//-1控制台运行结果不相同,并且返回false。
	//-2控制台运行结果相同,并且返回ture。
	//-2控制台运行结果相同,并且返回ture。
	public static void main(String[] args) {
		 try {
			// 序列化
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/wfile/test/object.txt"));
			oos.writeObject(SingletonB.getInstance());
			oos.close();
			System.out.println(SingletonB.getInstance().hashCode());
			// 反序列化
			File file = new File("D:/wfile/test/object.txt");
			ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(file));
			SingletonB newInstance = (SingletonB) ois.readObject();
			System.out.println(newInstance.hashCode());
			ois.close();
			//判断是否是同一个对象
			System.out.println(newInstance == SingletonB.getInstance());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class SingletonB implements Serializable {
	private static SingletonB SINGLETON_B;
	private SingletonB() {

	}
	//设置同步方法效率太低了,整个方法被上锁。
	synchronized public static SingletonB getInstance(){
		try {
			//延迟加载
			if(SINGLETON_B != null){
			}else{
				Thread.sleep(3000);
				SINGLETON_B = new SingletonB();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return SINGLETON_B;
	}

	//-1
	/*public Object readResolve(){
		System.out.println("调用了readResolve方法");
		return SINGLETON_B;

	}*/

	//-2
	public Object readResolve(){
		System.out.println("调用了readResolve方法");
		return SINGLETON_B;

	}
}




















