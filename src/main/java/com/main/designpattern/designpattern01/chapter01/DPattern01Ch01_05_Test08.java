package com.main.designpattern.designpattern01.chapter01;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 
 *<p>Title	: DPattern01Ch01_05_Test08.java</p>
 * @Description :
 * @author 	: admin
 * @date	: 2018年2月7日
 */
public class DPattern01Ch01_05_Test08 {
	//实现深拷贝实例(clone时对this.shallowList clone并赋值给新的集合)：
	//运行结果：
//	1.原型
//	shallow：{"shallowList":["小美"]}
//	============================
//	2.克隆对象后
//	shallow1：{"shallowList":["小美"]}
//	shallow2：{"shallowList":["小美","lily"]}
//	shallowList：["小美"]
//	============================
//	3.克隆list集合后
//	shallowList：["小美"]
//	cloneShallowList：["小美","Tom"]
//	============================
//	4.原型
//	shallow：{"shallowList":["小美"]}
//	============================
	public static void main(String[] args) throws CloneNotSupportedException {
		DPattern01Ch01_05_Shallow08 shallow = new DPattern01Ch01_05_Shallow08();
		ArrayList<String> shallowList = (ArrayList<String>) shallow.getShallowList();
		shallowList.add("小美");
		
		System.out.println("1.原型");
		System.out.println("shallow："+JSON.toJSONString(shallow));
		System.out.println("============================");
		
		DPattern01Ch01_05_Shallow08 shallow2 = shallow.clone();
		List<String> shallowList2 = shallow2.getShallowList();
		shallowList2.add("lily");
		System.out.println("2.克隆对象后");
		System.out.println("shallow1："+JSON.toJSONString(shallow));
		System.out.println("shallow2："+JSON.toJSONString(shallow2));
		System.out.println("shallowList："+JSON.toJSONString(shallowList));
		System.out.println("============================");
		
		ArrayList<String> cloneShallowList = (ArrayList<String>) shallowList.clone();
		cloneShallowList.add("Tom");
		System.out.println("3.克隆list集合后");
		System.out.println("shallowList："+JSON.toJSONString(shallowList));
		System.out.println("cloneShallowList："+JSON.toJSONString(cloneShallowList));
		System.out.println("============================");
		
		System.out.println("4.原型");
		System.out.println("shallow："+JSON.toJSONString(shallow));
		System.out.println("============================");
	}
}

class DPattern01Ch01_05_Shallow08 implements Cloneable{
	ArrayList<String> shallowList = new ArrayList<String>();
	
	public DPattern01Ch01_05_Shallow08 clone() throws CloneNotSupportedException{
		DPattern01Ch01_05_Shallow08 shallow = (DPattern01Ch01_05_Shallow08) super.clone();
		ArrayList<String> cloneShallowList = (ArrayList<String>) this.shallowList.clone();
		shallow.setShallowList(cloneShallowList);
		return shallow;
	}

	public ArrayList<String> getShallowList() {
		return shallowList;
	}

	public void setShallowList(ArrayList<String> shallowList) {
		this.shallowList = shallowList;
	}
	
	
}