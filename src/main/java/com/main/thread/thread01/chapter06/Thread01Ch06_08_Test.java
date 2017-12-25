package com.main.thread.thread01.chapter06;

/**
 * 
 *<p>Title	: Thread01Ch06_08_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月25日上午10:00:36
 */
public class Thread01Ch06_08_Test {
	/**6.7完善使用enum枚举实现单例模式**/
	//前一示例将枚举类进行暴露,违反了"职责单一原则"。
	//6.7完善使用enum枚举实现单例模式
	//-1控制台运行结果相同。
	//-1控制台运行结果相同。
	public enum EnumA {
	    INSTANCE;
	    private Thread01Ch06_08_Resource instance;
	    EnumA() {
	    	System.out.println("调用了EnumA 的构造方法!");
	        instance = new Thread01Ch06_08_Resource();
	    }
	    public Thread01Ch06_08_Resource getInstance() {
	        return instance;
	    }
	}
	
	public static Thread01Ch06_08_Resource getInstance() {
	        return EnumA.INSTANCE.getInstance();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			Thread01Ch06_08_Thread t = new Thread01Ch06_08_Thread();
			t.start();
		}
	}
}

class Thread01Ch06_08_Thread extends Thread{
	@Override
	public void run() {
		super.run();
		System.out.println(Thread01Ch06_08_Test.getInstance().hashCode());
	}
}

class Thread01Ch06_08_Resource{
}
















