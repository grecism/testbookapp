package com.main.thread.thread01.chapter01;

/**
 * 
 *<p>Title	: PracticeThread35_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月5日上午11:16:08
 */
public class Thread01Ch01_35_Test {
	/**1.9 yield方法**/
	//yield()方法的作用是放弃当前的CPU资源,将他让给其他的任务去占用CPU执行时间。但放弃的时间不确定,有可能刚刚放弃,马上又获得CPU时间片。
	//1.9 yield方法
	//取得运行时间,作为比较结果,测试yield方法的使用效果。
	//-1CPU独占时间片。
	//-1CPU独占时间片。
	//-2将CPU让给其他资源导致速度变慢。
	//-2将CPU让给其他资源导致速度变慢。
	public static void main(String[] args) {
		Thread thread = new Thread(){
			@Override
			public void run() {
				super.run();
				long begintime = System.currentTimeMillis();
				int count = 0;
				for (int i = 0; i < 50000000; i++) {
					count = count+(i+1);//用时:31毫秒 CPU独占时间片
					//Thread.yield();//用时:4400毫秒 将CPU让给其他资源导致速度变慢
				}
				long endtime = System.currentTimeMillis();
				System.out.println("用时:"+(endtime-begintime)+"毫秒");
			}
		};
		thread.start();
	}
}
