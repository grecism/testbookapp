package com.main.thread.thread01;


/**
 * 
 *<p>Title	: Thread01Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月18日下午5:56:31
 */
public class Thread01Test {
	public static void main(String[] args)  {
		//1.random() 方法可返回介于 0 ~ 1 之间的一个随机数。
		double a = Math.random();
		System.out.println("a="+a);
		double b= a*10000;
		System.out.println("a*10000="+b);
		int c =(int)b;
		System.out.println("int(a*10000)="+c);
		//System.out.println(Math.random());
		//System.out.println((int)(Math.random()*10000));
		//System.out.println((int)(Math.random()*1000));
		//2.SimpleDateFormat
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Date date = new Date();
		//System.out.println(sdf.format(date));
		
	}
}


