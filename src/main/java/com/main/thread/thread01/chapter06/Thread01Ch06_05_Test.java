package com.main.thread.thread01.chapter06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 *<p>Title	: Thread01Ch06_05_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月22日下午4:25:57
 */
public class Thread01Ch06_05_Test {
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
			//oos.writeObject(SingletonA.getInstance());
			oos.writeObject(SingletonB.getInstance());
			oos.close();
//			System.out.println(SingletonA.getInstance().hashCode());
			System.out.println(SingletonB.getInstance().hashCode());
			// 反序列化
			File file = new File("D:/wfile/test/object.txt");
			ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(file));
//			SingletonA newInstance = (SingletonA) ois.readObject();
			SingletonB newInstance = (SingletonB) ois.readObject();
			System.out.println(newInstance.hashCode());
			ois.close();
			//判断是否是同一个对象
//			System.out.println(newInstance == SingletonA.getInstance());
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






















