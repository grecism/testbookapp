package com.main.thread.thread01.chapter03;

/**
 * 
 *<p>Title	: Thread01Ch03_36_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月20日下午3:05:01
 */
public class Thread01Ch03_36_Test {
	/**3.3.3 解决get()返回null问题**/
	//3.3.3 解决get()返回null问题
	//第一次调用get()方法不返回null,具有默认值的效果。
	//-1有初始值。
	//-1此示例仅仅证明main线程有自己的值,那其他线程是否会有自己的初始值呢？
	public static Thread01Ch03_36_ThreadLocal_A tla = new Thread01Ch03_36_ThreadLocal_A();
	public static void main(String[] args) {
		if(tla.get() == null){
			System.out.println("值为空");
			tla.set("setValue");
		}
		System.out.println(tla.get());
		System.out.println(tla.get());
	}
}

class Thread01Ch03_36_ThreadLocal_A extends ThreadLocal{
	@Override
	protected Object initialValue() {
		return "默认值 第一次get的不为null";
	}
}


















