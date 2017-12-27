package com.main.designpattern.designpattern01.chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	: DPattern01Ch02_04_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月26日下午5:09:12
 */
public class DPattern01Ch02_04_Test {
	/**2.4组合模式**/
	//组合模式（Composite Pattern），又叫部分整体模式，是用于把一组相似的对象当作一个单一的对象。组合模式依据树形结构来组合对象，用来表示部分以及整体层次。这种类型的设计模式属于结构型模式，它创建了对象组的树形结构。
	//这种模式创建了一个包含自己对象组的类。该类提供了修改相同对象组的方式。
	//我们通过下面的实例来演示组合模式的用法。实例演示了一个组织中员工的层次结构。
	//优点： 1、高层模块调用简单。 2、节点自由增加。
	//缺点：在使用组合模式时，其叶子和树枝的声明都是实现类，而不是接口，违反了依赖倒置原则。
	//实现:
	//我们有一个类 Employee，该类被当作组合模型类。CompositePatternDemo，我们的演示类使用 Employee 类来添加部门层次结构，并打印所有员工。
	public static void main(String[] args) {
		//2.使用Employee类来创建和打印员工的层次结构。
		DPattern01Ch02_04_Employee ceo = new DPattern01Ch02_04_Employee("John","CEO", 30000);
		DPattern01Ch02_04_Employee headSales = new DPattern01Ch02_04_Employee("Robert","Head Sales", 20000);
		DPattern01Ch02_04_Employee headMarketing = new DPattern01Ch02_04_Employee("Michel","Head Marketing", 20000);
		ceo.add(headSales);
		ceo.add(headMarketing);
		DPattern01Ch02_04_Employee salesA = new DPattern01Ch02_04_Employee("Richard","Sales", 10000);
		DPattern01Ch02_04_Employee salesB = new DPattern01Ch02_04_Employee("Rob","Sales", 10000);
		headSales.add(salesA);
		headSales.add(salesB);
		DPattern01Ch02_04_Employee marketingA = new DPattern01Ch02_04_Employee("Laura","Marketing", 10000);
		DPattern01Ch02_04_Employee marketingB = new DPattern01Ch02_04_Employee("Bob","Marketing", 10000);
		headMarketing.add(marketingA);
		headMarketing.add(marketingB);
		
		//打印该组织所有员工
		System.out.println(ceo.toString());
		for (DPattern01Ch02_04_Employee headEmployee : ceo.getSubordinates()) {
			System.out.println(headEmployee);
			for (DPattern01Ch02_04_Employee employee : headEmployee.getSubordinates()) {
				System.out.println(employee);
			}
		}
	}
}

//1.创建Employee类,该类带有Employee对象的列表。
class DPattern01Ch02_04_Employee{
	private String name;
	private String dept;
	private int salary;
	private List<DPattern01Ch02_04_Employee> subordinates;
	
	public DPattern01Ch02_04_Employee(String name,String dept,int salary) {
		this.name = name;
		this.dept = dept;
		this.salary = salary;
		subordinates = new ArrayList<DPattern01Ch02_04_Employee>();
	}
	
	public void add(DPattern01Ch02_04_Employee employee){
		subordinates.add(employee);
	}
	
	public void remove(DPattern01Ch02_04_Employee employee){
		subordinates.remove(employee);
	}
	
	public List<DPattern01Ch02_04_Employee> getSubordinates(){
		return subordinates;
	}
	
	@Override
	public String toString() {
		return ("Employee[Name:"+name+"	Dept:"+dept+"	salary:"+salary+"]");
	}
}































