package com.main.designpattern.designpattern01.chapter01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.alibaba.fastjson.JSON;

/**
 * 
 *<p>Title	: DPattern01Ch01_05_Test01.java</p>
 * @Description :
 * @author 	: admin
 * @date	: 2018年2月5日
 */
public class DPattern01Ch01_05_Test01 {
	//注意：
	//Object类的clone方法只会拷贝java中的8中基本类型以及他们的封装类型，另外还有String类型。对于数组、容器对象、引用对象等都不会拷贝，这就是浅拷贝。如果要实现深拷贝，必须将原型模式中的数组、容器对象、引用对象等另行拷贝。
	//浅拷贝与深拷贝：
	//浅拷贝（shallow copy）：
	//被复制对象的所有变量都含有与原来的对象相同的值(仅对于简单的值类型数据)，而所有的对其他对象的引用都仍然指向原来的对象。换言之，只负责克隆按值传递的数据（比如：基本数据类型、String类型）。
	//深拷贝 （deep copy）：
	//被复制对象的所有的变量都含有与原来的对象相同的值，除去那些引用其他对象的变量。那些引用其他对象的变量将指向被复制过的新对象，而不再是原有的那些被引用的对象。换言之，除了浅度克隆要克隆的值外，还负责克隆引用类型的数据，基本上就是被克隆实例所有的属性的数据都会被克隆出来。
	//优点与缺点：
	//优点：
	//　1、对客户端隐藏具体的实现类型：原型模式的客户端，只知道原型接口的类型，并不知道具体的实现类型，从而减少了客户端对这些具体实现类型的依赖。
	// 2、在运行时动态改变具体的实现类型：原型模式可以在运行期间，由客户来注册符合原型接口的实现类型，也可以动态的改变具体的实现类型，看起来接口没有任何变化，但其实运行的已经是另外一个类实例了。因为克隆一个原型就类似于实例化一个类。
	//缺点：
	//　深度克隆方法实现会比较困难：原型模式最大的缺点就在于每个原型的子类都必须实现clone的操作，尤其在包含引用类型的对象时，clone方法会比较麻烦，必须要能够递归的让所有的相关对象都要正确的实现克隆。
	//应用场景：
	//1、一个系统想要独立于它想要使用的对象时，可以使用原型模式，让系统只面向接口编程，在系统需要新的对象的时候，可以通过克隆原型来得到；
	//2、需要实例化的类是在运行时刻动态指定时，可以使用原型模式，通过克隆原型来得到需要的实例；
	//3、创建新对象成本较大（如初始化需要占用较长的时间，占用太多的CPU资源或网络资源），新的对象可以通过原型模式对已有对象进行复制来获得，如果是相似对象，则可以对其成员变量稍作修改。
	//实现浅拷贝实例(计划任务完成)：
	//-1 对于引用类型List，修改plan的List，plan2也随着变化了，这就是浅拷贝带来的问题。
	//-1 被复制对象的所有变量都含有与原来的对象相同的值(仅对于简单的值类型数据)，而所有的对其他对象的引用都仍然指向原来的对象。换言之，只负责克隆按值传递的数据（比如：基本数据类型、String类型）。
	//运行结果：
//	1.原型与克隆后
//	plan与plan2地址是否一样？false
//	plan.getName() == plan2.getName()false
//	plan.getLevel() == plan2.getLevel()false
//	plan.getStartDate() == plan2.getStartDate()false
//	plan.getEndDate() == plan2.getEndDate()false
//	plan.getExecutors() == plan2.getExecutors()true
//	plan:{"endDate":1522512000000,"executors":["xiaowanzi","xiaomei"],"level":1,"name":"前端登陆界面重构","startDate":1514736000000}
//	plan2:{"endDate":1525104000000,"executors":["xiaowanzi","xiaomei"],"level":2,"name":"后端接口改造","startDate":1517414400000}
//	==============================================
//	2.plan任务比较重，再给plan添加一个人
//	plan与plan2地址是否一样？false
//	plan.getName() == plan2.getName()false
//	plan.getLevel() == plan2.getLevel()false
//	plan.getStartDate() == plan2.getStartDate()false
//	plan.getEndDate() == plan2.getEndDate()false
//	plan.getExecutors() == plan2.getExecutors()true
//	plan:{"endDate":1522512000000,"executors":["xiaowanzi","xiaomei","xiaoying"],"level":1,"name":"前端登陆界面重构","startDate":1514736000000}
//	plan2:{"endDate":1525104000000,"executors":["xiaowanzi","xiaomei","xiaoying"],"level":2,"name":"后端接口改造","startDate":1517414400000}
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
//	4.plan2需要重新组织团队
//	plan与plan2地址是否一样？false
//	plan.getName() == plan2.getName()false
//	plan.getLevel() == plan2.getLevel()false
//	plan.getStartDate() == plan2.getStartDate()false
//	plan.getEndDate() == plan2.getEndDate()false
//	plan.getExecutors() == plan2.getExecutors()false
//	plan:{"endDate":1522512000000,"executors":["xiaowanzi","xiaomei","xiaoying","xiaoming"],"level":1,"name":"前端登陆界面重构","startDate":1514736000000}
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
		DPattern01Ch01_05_Plan01 plan = new DPattern01Ch01_05_Plan01();
		plan.setName("前端登陆界面重构");
		plan.setLevel(1);
		plan.setStartDate(sdf.parse(startTime));
		plan.setEndDate(sdf.parse(endTime));
		plan.setExecutors(executors);
		
		//克隆后
		DPattern01Ch01_05_Plan01 plan2 = plan.clone();
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
		System.out.println("3.plan2任务比较重，再给plan2添加一个人");
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
		System.out.println("4.plan2需要重新组织团队");
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
class DPattern01Ch01_05_Plan01 implements Cloneable{
	private String name;//计划名称
	private int level;//任务级别
	private Date startDate;//开始时间
	private Date endDate;//截止时间
	private List<String> executors = new ArrayList<String>();//执行人员
	
	public DPattern01Ch01_05_Plan01 clone(){
		try {
			return (DPattern01Ch01_05_Plan01) super.clone();
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














