package com.main.designpattern.designpattern01.chapter01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.alibaba.fastjson.JSON;

/**
 * 
 *<p>Title	:DPattern01Ch01_05_Test02.java
 * @Description :
 * @author 	: admin
 * @date	: 2018年2月6日
 */
public class DPattern01Ch01_05_Test02 {
	//实现深拷贝实例(计划任务完成)：
	//-2   对于引用类型List，修改plan的List，plan2不会随着变化了。
	//-2 Object类的clone方法只会拷贝java中的8中基本类型以及他们的封装类型，另外还有String类型。对于数组、容器对象、引用对象等都不会拷贝，这就是浅拷贝。如果要实现深拷贝，必须将原型模式中的数组、容器对象、引用对象等另行拷贝。
	//运行结果:
//	1.原型与克隆后
//	plan与plan2地址是否一样？false
//	plan.getName() == plan2.getName()false
//	plan.getLevel() == plan2.getLevel()false
//	plan.getStartDate() == plan2.getStartDate()false
//	plan.getEndDate() == plan2.getEndDate()false
//	plan.getExecutors() == plan2.getExecutors()false
//	plan:{"endDate":1522512000000,"executors":["xiaowanzi","xiaomei"],"level":1,"name":"前端登陆界面重构","startDate":1514736000000}
//	plan2:{"endDate":1525104000000,"executors":["xiaowanzi","xiaomei"],"level":2,"name":"后端接口改造","startDate":1517414400000}
//	==============================================
//	2.plan任务比较重，再给plan添加一个人
//	plan与plan2地址是否一样？false
//	plan.getName() == plan2.getName()false
//	plan.getLevel() == plan2.getLevel()false
//	plan.getStartDate() == plan2.getStartDate()false
//	plan.getEndDate() == plan2.getEndDate()false
//	plan.getExecutors() == plan2.getExecutors()false
//	plan:{"endDate":1522512000000,"executors":["xiaowanzi","xiaomei","xiaoying"],"level":1,"name":"前端登陆界面重构","startDate":1514736000000}
//	plan2:{"endDate":1525104000000,"executors":["xiaowanzi","xiaomei"],"level":2,"name":"后端接口改造","startDate":1517414400000}
//	==============================================
//	3.plan2任务比较重，再给plan2添加一个人
//	plan任务比较重，再给plan添加一个人
//	plan与plan2地址是否一样？false
//	plan.getName() == plan2.getName()false
//	plan.getLevel() == plan2.getLevel()false
//	plan.getStartDate() == plan2.getStartDate()false
//	plan.getEndDate() == plan2.getEndDate()false
//	plan.getExecutors() == plan2.getExecutors()true
//	plan:{"endDate":1522512000000,"executors":["xiaowanzi","xiaomei","xiaoying","xiaoming"],"level":1,"name":"前端登陆界面重构","startDate":1514736000000}
//	plan2:{"endDate":1525104000000,"executors":["xiaowanzi","xiaomei","xiaoying","xiaoming"],"level":2,"name":"后端接口改造","startDate":1517414400000}
//	==============================================
//	4.plan2任务比较重，再给plan2添加一个人，不会使plan新添加一个人
//	plan任务比较重，再给plan添加一个人
//	plan与plan2地址是否一样？false
//	plan.getName() == plan2.getName()false
//	plan.getLevel() == plan2.getLevel()false
//	plan.getStartDate() == plan2.getStartDate()false
//	plan.getEndDate() == plan2.getEndDate()false
//	plan.getExecutors() == plan2.getExecutors()true
//	plan:{"endDate":1522512000000,"executors":["xiaowanzi","xiaomei","xiaoying","xiaoming","lily"],"level":1,"name":"前端登陆界面重构","startDate":1514736000000}
//	plan2:{"endDate":1525104000000,"executors":["xiaowanzi","xiaomei","xiaoying","xiaoming","lily"],"level":2,"name":"后端接口改造","startDate":1517414400000}
//	==============================================
//	5.plan2需要重新组织团队
//	plan与plan2地址是否一样？false
//	plan.getName() == plan2.getName()false
//	plan.getLevel() == plan2.getLevel()false
//	plan.getStartDate() == plan2.getStartDate()false
//	plan.getEndDate() == plan2.getEndDate()false
//	plan.getExecutors() == plan2.getExecutors()false
//	plan:{"endDate":1522512000000,"executors":["xiaowanzi","xiaomei","xiaoying","xiaoming","lily"],"level":1,"name":"前端登陆界面重构","startDate":1514736000000}
//	plan2:{"endDate":1525104000000,"executors":["xiaowanzi2","xiaomei2"],"level":2,"name":"后端接口改造","startDate":1517414400000}

	public static void main(String[] args) throws ParseException {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//添加执行人员
		List<String> executors = new ArrayList<String>();
		executors.add("xiaowanzi");
		executors.add("xiaomei");
		List<String> executors2 = new ArrayList<String>();
		executors2.add("xiaowanzi2");
		executors2.add("xiaomei2");
		
		//设置开始结束时间
		String startTime = "2018-01-01";
		String endTime = "2018-04-01";
		String startTime2 = "2018-02-01";
		String endTime2 = "2018-05-01";
		
		//原型
		DPattern01Ch01_05_Plan02 plan = new DPattern01Ch01_05_Plan02();
		plan.setName("前端登陆界面重构");
		plan.setLevel(1);
		plan.setStartDate(sdf.parse(startTime));
		plan.setEndDate(sdf.parse(endTime));
		plan.setExecutors(executors);
		
		//克隆后
		DPattern01Ch01_05_Plan02 plan2 = plan.clone();
		plan2.setName("后端接口改造");
		plan2.setLevel(2);
		plan2.setStartDate(sdf.parse(startTime2));
		plan2.setEndDate(sdf.parse(endTime2));
		
		System.out.println("1.原型与克隆后");
		System.out.println("plan与plan2地址是否一样？"+(plan == plan2));
		System.out.println("plan.getName() == plan2.getName()"+(plan.getName() == plan2.getName()));
		System.out.println("plan.getLevel() == plan2.getLevel()"+(plan.getLevel() == plan2.getLevel()));
		System.out.println("plan.getStartDate() == plan2.getStartDate()"+(plan.getStartDate() == plan2.getStartDate()));
		System.out.println("plan.getEndDate() == plan2.getEndDate()"+(plan.getEndDate() == plan2.getEndDate()));
		System.out.println("plan.getExecutors() == plan2.getExecutors()"+(plan.getExecutors() == plan2.getExecutors()));
		System.out.println("plan:"+JSON.toJSONString(plan));
		System.out.println("plan2:"+JSON.toJSONString(plan2));
		System.out.println("==============================================");
		
		executors.add("xiaoying");
		plan.setExecutors(executors);
		System.out.println("2.plan任务比较重，再给plan添加一个人");
		System.out.println("plan与plan2地址是否一样？"+(plan == plan2));
		System.out.println("plan.getName() == plan2.getName()"+(plan.getName() == plan2.getName()));
		System.out.println("plan.getLevel() == plan2.getLevel()"+(plan.getLevel() == plan2.getLevel()));
		System.out.println("plan.getStartDate() == plan2.getStartDate()"+(plan.getStartDate() == plan2.getStartDate()));
		System.out.println("plan.getEndDate() == plan2.getEndDate()"+(plan.getEndDate() == plan2.getEndDate()));
		System.out.println("plan.getExecutors() == plan2.getExecutors()"+(plan.getExecutors() == plan2.getExecutors()));
		System.out.println("plan:"+JSON.toJSONString(plan));
		System.out.println("plan2:"+JSON.toJSONString(plan2));
		System.out.println("==============================================");
		
		executors.add("xiaoming");
		plan2.setExecutors(executors);
		System.out.println("3.plan2任务比较重，再给plan2添加一个人,会使plan新添加一个人");
		System.out.println("plan任务比较重，再给plan添加一个人");
		System.out.println("plan与plan2地址是否一样？"+(plan == plan2));
		System.out.println("plan.getName() == plan2.getName()"+(plan.getName() == plan2.getName()));
		System.out.println("plan.getLevel() == plan2.getLevel()"+(plan.getLevel() == plan2.getLevel()));
		System.out.println("plan.getStartDate() == plan2.getStartDate()"+(plan.getStartDate() == plan2.getStartDate()));
		System.out.println("plan.getEndDate() == plan2.getEndDate()"+(plan.getEndDate() == plan2.getEndDate()));
		System.out.println("plan.getExecutors() == plan2.getExecutors()"+(plan.getExecutors() == plan2.getExecutors()));
		System.out.println("plan:"+JSON.toJSONString(plan));
		System.out.println("plan2:"+JSON.toJSONString(plan2));
		System.out.println("==============================================");
		
		List<String> executorsOri = plan2.getExecutors();
		executorsOri.add("lily");
		plan2.setExecutors(executorsOri);
		System.out.println("4.plan2任务比较重，再给plan2添加一个人，通过获取plan2的执行人员后添加，还是会使plan新添加一个人");
		System.out.println("plan任务比较重，再给plan添加一个人");
		System.out.println("plan与plan2地址是否一样？"+(plan == plan2));
		System.out.println("plan.getName() == plan2.getName()"+(plan.getName() == plan2.getName()));
		System.out.println("plan.getLevel() == plan2.getLevel()"+(plan.getLevel() == plan2.getLevel()));
		System.out.println("plan.getStartDate() == plan2.getStartDate()"+(plan.getStartDate() == plan2.getStartDate()));
		System.out.println("plan.getEndDate() == plan2.getEndDate()"+(plan.getEndDate() == plan2.getEndDate()));
		System.out.println("plan.getExecutors() == plan2.getExecutors()"+(plan.getExecutors() == plan2.getExecutors()));
		System.out.println("plan:"+JSON.toJSONString(plan));
		System.out.println("plan2:"+JSON.toJSONString(plan2));
		System.out.println("==============================================");
		
		plan2.setExecutors(executors2);
		System.out.println("5.plan2需要重新组织团队");
		System.out.println("plan与plan2地址是否一样？"+(plan == plan2));
		System.out.println("plan.getName() == plan2.getName()"+(plan.getName() == plan2.getName()));
		System.out.println("plan.getLevel() == plan2.getLevel()"+(plan.getLevel() == plan2.getLevel()));
		System.out.println("plan.getStartDate() == plan2.getStartDate()"+(plan.getStartDate() == plan2.getStartDate()));
		System.out.println("plan.getEndDate() == plan2.getEndDate()"+(plan.getEndDate() == plan2.getEndDate()));
		System.out.println("plan.getExecutors() == plan2.getExecutors()"+(plan.getExecutors() == plan2.getExecutors()));
		System.out.println("plan:"+JSON.toJSONString(plan));
		System.out.println("plan2:"+JSON.toJSONString(plan2));
	}
}

//计划
class DPattern01Ch01_05_Plan02 implements Cloneable{
	private String name;//计划名称
	private int level;//任务级别
	private Date startDate;//开始时间
	private Date endDate;//截止时间
	private List<String> executors = new ArrayList<String>();//执行人员
	
	public DPattern01Ch01_05_Plan02 clone(){
		try {
			DPattern01Ch01_05_Plan02 plan = (DPattern01Ch01_05_Plan02) super.clone();
			//引用类型的属性,需要处理
			if(this.executors != null){
				List<String> _executors = new ArrayList<String>();
				for (String s : this.getExecutors()) {
					_executors.add(s);
				}
				plan.setExecutors(_executors);
			}
			
			return plan;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<String> getExecutors() {
		return executors;
	}
	public void setExecutors(List<String> executors) {
		this.executors = executors;
	}
	
	
}
