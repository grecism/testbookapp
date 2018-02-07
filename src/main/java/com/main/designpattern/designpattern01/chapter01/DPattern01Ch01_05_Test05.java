package com.main.designpattern.designpattern01.chapter01;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 
 *<p>Title	:DPattern01Ch01_05_Test05.java
 * @Description :
 * @author 	: admin
 * @date	: 2018年2月6日
 */
public class DPattern01Ch01_05_Test05 {
	//实现浅拷贝实例：
	//是因为 Java 做了一个偷懒的拷贝动作，Object 类提供的方法 clone 只是拷贝本对象，其对象内部的数组、引用对象等都不拷贝，还是指向原生对象的内部元素地址，这种拷贝就叫做浅拷贝，
	//确实是非常浅，两个对象共享了一个私有变量，你改我改大家都能改，是一个种非常不安全的方式，在实际项目中使用还是比较少的。
	//运行结果：
//	1.原型
//	shallow：{"shallowList":["小美"]}
//	============================
//	2.克隆对象后
//	shallow：{"shallowList":["小美","lily"]}
//	shallow2：{"shallowList":["小美","lily"]}
//	============================
//	3.克隆list集合后（深克隆）
//	shallowList：["小美","lily"]
//	cloneShallowList：["小美","lily","Tom"]
//	============================
//	4.原型
//	shallow：{"shallowList":["小美","lily"]}
//	============================
	public static void main(String[] args) throws CloneNotSupportedException {
		DPattern01Ch01_05_Shallow05 shallow = new DPattern01Ch01_05_Shallow05();
		ArrayList<String> shallowList = (ArrayList<String>) shallow.getShallowList();
		shallowList.add("小美");
		
		System.out.println("1.原型");
		System.out.println("shallow："+JSON.toJSONString(shallow));
		System.out.println("============================");
		
		DPattern01Ch01_05_Shallow05 shallow2 = shallow.clone();
		List<String> shallowList2 = shallow2.getShallowList();
		shallowList2.add("lily");
		System.out.println("2.克隆对象后");
		System.out.println("shallow："+JSON.toJSONString(shallow));
		System.out.println("shallow2："+JSON.toJSONString(shallow2));
		System.out.println("============================");
		
		ArrayList<String> cloneShallowList = (ArrayList<String>) shallowList.clone();
		cloneShallowList.add("Tom");
		System.out.println("3.克隆list集合后（深克隆）");
		System.out.println("shallowList："+JSON.toJSONString(shallowList));
		System.out.println("cloneShallowList："+JSON.toJSONString(cloneShallowList));
		System.out.println("============================");
		
		System.out.println("4.原型");
		System.out.println("shallow："+JSON.toJSONString(shallow));
		System.out.println("============================");
		
		
	}
}

class DPattern01Ch01_05_Shallow05 implements Cloneable{
	List<String> shallowList = new ArrayList<String>();
	
	public DPattern01Ch01_05_Shallow05 clone() throws CloneNotSupportedException{
		DPattern01Ch01_05_Shallow05 shallow = (DPattern01Ch01_05_Shallow05) super.clone();
		return shallow;
	}

	public List<String> getShallowList() {
		return shallowList;
	}

	public void setShallowList(List<String> shallowList) {
		this.shallowList = shallowList;
	}

	
} 