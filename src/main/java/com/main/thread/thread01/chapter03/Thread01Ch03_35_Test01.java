package com.main.thread.thread01.chapter03;

/**
 * @author lulu.zou
 * @version 2018/4/16
 * @since 2018/4/16
 */
public class Thread01Ch03_35_Test01 {
    //3.3.2 验证线程变量的隔离性
    //-3线程的初始值都为0，线程的累加值都为45。
    //-3可以看到，各个线程的value值是相互独立的，本线程的累加操作不会影响到其他线程的值，真正达到了线程内部隔离的效果。
    private static final ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i <5 ; i++) {
            new Thread(new Thread01Ch03_35_01_Thread(i)).start();
        }
    }

    static class Thread01Ch03_35_01_Thread implements Runnable{
        private int index;

        public Thread01Ch03_35_01_Thread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            System.out.println("线程"+index+"的初始value:"+value.get());
            for(int i=0;i<10;i++){
                value.set(value.get()+i);
            }
            System.out.println("线程"+index+"的累加value:"+value.get());
        }
    }
}
