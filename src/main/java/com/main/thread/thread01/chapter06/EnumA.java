package com.main.thread.thread01.chapter06;

/**
 * 
 *<p>Title	: EnumA</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月25日上午10:04:09
 */
public enum EnumA {
    INSTANCE;
    private EnumAResource instance;
    EnumA() {
    	System.out.println("调用了EnumA 的构造方法!");
        instance = new EnumAResource();
    }
    public EnumAResource getInstance() {
        return instance;
    }
}

class EnumAResource{
}
