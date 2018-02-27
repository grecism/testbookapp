package com.main.designpattern.designpattern01.chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	: DPattern01Ch02_03_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月26日下午3:17:13
 */
public class DPattern01Ch02_03_Test {
	/**2.3过滤器模式**/
	//过滤器模式（Filter Pattern）或标准模式（Criteria Pattern）是一种设计模式，这种模式允许开发人员使用不同的标准来过滤一组对象，通过逻辑运算以解耦
	//的方式把它们连接起来。这种类型的设计模式属于结构型模式，它结合多个标准来获得单一标准。
	public static void main(String[] args) {
		//4.使用不同的标准(Creteria)和它们的结合来过滤Person对象列表。
		 List<DPattern01Ch02_03_Person> persons = new ArrayList<DPattern01Ch02_03_Person>();
	     persons.add(new DPattern01Ch02_03_Person("Robert","Male", "Single"));
	     persons.add(new DPattern01Ch02_03_Person("John","Male", "Married"));
	     persons.add(new DPattern01Ch02_03_Person("Laura","Female", "Married"));
	     persons.add(new DPattern01Ch02_03_Person("Diana","Female", "Single"));
	     persons.add(new DPattern01Ch02_03_Person("Mike","Male", "Single"));
	     persons.add(new DPattern01Ch02_03_Person("Bobby","Male", "Single"));
	     
	     //printPersons(persons);
	     
	     DPattern01Ch02_03_Criteria criteriaMale = new DPattern01Ch02_03_CriteriaMale();
	     DPattern01Ch02_03_Criteria criteriaFemale = new DPattern01Ch02_03_CriteriaFemale(); 
	     DPattern01Ch02_03_Criteria criteriaSingle = new DPattern01Ch02_03_CriteriaSingle();
	     DPattern01Ch02_03_Criteria criteriaAnd = new DPattern01Ch02_03_CriteriaAnd(criteriaSingle,criteriaMale);
	     DPattern01Ch02_03_Criteria criteriOr = new DPattern01Ch02_03_CriteriaOr(criteriaSingle,criteriaMale);
	     
	     System.out.println("Male:");
	     printPersons(criteriaMale.meetCriteria(persons));
	     System.out.println("Female:");
	     printPersons(criteriaFemale.meetCriteria(persons));
	     System.out.println("Single:");
	     printPersons(criteriaSingle.meetCriteria(persons));
	     System.out.println("Single And Male:");
	     printPersons(criteriaAnd.meetCriteria(persons));
	     System.out.println("Single Or Male:");
	     printPersons(criteriOr.meetCriteria(persons));
	     
	     
	}
	
	public static void printPersons(List<DPattern01Ch02_03_Person> persons){
		for (DPattern01Ch02_03_Person dPattern01Ch02_03_Person : persons) {
			System.out.println("Person[Name:"+dPattern01Ch02_03_Person.getName()+
					" Gender:"+dPattern01Ch02_03_Person.getGender()+
					" maritalStatus:"+dPattern01Ch02_03_Person.getMaritalStatus()+"]");
		}
	}
}

//1.创建一个类,在该类上应用标准。
class DPattern01Ch02_03_Person{
	private String name;
	private String gender;
	private String maritalStatus;
	public DPattern01Ch02_03_Person(String name,String gender,String maritalStatus) {
		this.name = name;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
}

//2.为标准(Criteria)创建一个接口。
interface  DPattern01Ch02_03_Criteria{ 
	public List<DPattern01Ch02_03_Person> meetCriteria(List<DPattern01Ch02_03_Person> persons);
}

//3.创建实现了Criteria接口的实现类。
class DPattern01Ch02_03_CriteriaMale implements DPattern01Ch02_03_Criteria{
	@Override
	public List<DPattern01Ch02_03_Person> meetCriteria(List<DPattern01Ch02_03_Person> persons) {
		List<DPattern01Ch02_03_Person> malePersons = new ArrayList<DPattern01Ch02_03_Person>();
		for (DPattern01Ch02_03_Person dPattern01Ch02_03_Person : persons) {
			if("male".equalsIgnoreCase(dPattern01Ch02_03_Person.getGender())){
				malePersons.add(dPattern01Ch02_03_Person);
			}
		}
		return malePersons;
	}
	
}

class DPattern01Ch02_03_CriteriaFemale implements DPattern01Ch02_03_Criteria{
	@Override
	public List<DPattern01Ch02_03_Person> meetCriteria(List<DPattern01Ch02_03_Person> persons) {
		List<DPattern01Ch02_03_Person> femalePersons = new ArrayList<DPattern01Ch02_03_Person>();
		for (DPattern01Ch02_03_Person dPattern01Ch02_03_Person : persons) {
			if("female".equalsIgnoreCase(dPattern01Ch02_03_Person.getGender())){
				femalePersons.add(dPattern01Ch02_03_Person);
			}
		}
		return femalePersons;
	}
	
}

class DPattern01Ch02_03_CriteriaSingle implements DPattern01Ch02_03_Criteria{
	@Override
	public List<DPattern01Ch02_03_Person> meetCriteria(List<DPattern01Ch02_03_Person> persons) {
		List<DPattern01Ch02_03_Person> singlePersons = new ArrayList<DPattern01Ch02_03_Person>();
		for (DPattern01Ch02_03_Person dPattern01Ch02_03_Person : persons) {
			if("single".equalsIgnoreCase(dPattern01Ch02_03_Person.getMaritalStatus())){
				singlePersons.add(dPattern01Ch02_03_Person);
			}
		}
		return singlePersons;
	}
	
}

class DPattern01Ch02_03_CriteriaAnd implements DPattern01Ch02_03_Criteria{
	private DPattern01Ch02_03_Criteria criteria;
	private DPattern01Ch02_03_Criteria othercriteria;
	public DPattern01Ch02_03_CriteriaAnd(DPattern01Ch02_03_Criteria criteria,DPattern01Ch02_03_Criteria othercriteria) {
		this.criteria = criteria;
		this.othercriteria = othercriteria;
	}
	@Override
	public List<DPattern01Ch02_03_Person> meetCriteria(List<DPattern01Ch02_03_Person> persons) {
		List<DPattern01Ch02_03_Person> criteriaone = criteria.meetCriteria(persons);
		List<DPattern01Ch02_03_Person> criteriatwo = othercriteria.meetCriteria(criteriaone);
		return criteriatwo;
	}
}

class DPattern01Ch02_03_CriteriaOr implements DPattern01Ch02_03_Criteria{
	private DPattern01Ch02_03_Criteria criteria;
	private DPattern01Ch02_03_Criteria othercriteria;
	public DPattern01Ch02_03_CriteriaOr(DPattern01Ch02_03_Criteria criteria,DPattern01Ch02_03_Criteria othercriteria) {
		this.criteria = criteria;
		this.othercriteria = othercriteria;
	}
	@Override
	public List<DPattern01Ch02_03_Person> meetCriteria(List<DPattern01Ch02_03_Person> persons) {
		List<DPattern01Ch02_03_Person> criteriaone = criteria.meetCriteria(persons);
		List<DPattern01Ch02_03_Person> criteriatwo = othercriteria.meetCriteria(persons);
		for (DPattern01Ch02_03_Person dPattern01Ch02_03_Person : criteriatwo) {
			if(!criteriaone.contains(dPattern01Ch02_03_Person)){
				criteriaone.add(dPattern01Ch02_03_Person);
			}
		}
		return criteriaone;
	}
	
}























