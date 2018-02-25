package com.main.designpattern.designpattern01.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	: DPattern01Ch01_05_Test09.java</p>
 * @Description :
 * @author 	: admin
 * @date	: 2018年2月7日
 */
public class DPattern01Ch01_05_Test09 {
	//实现浅拷贝实例(发送简历)：
	//从运行结果上看，虽然小明成功复制了小樱的简历，但是随后对小明技能列表和工作经验的修改却导致了小樱的简历被同时修改，这是由于我们在实现clone() 方法时直接调用了Object类的本地clone()方法造成的，
	//因为Object的clone()方法执行的是浅拷贝，因而小明和小樱的简历中的skillList和workExperience字段都指向了同一个对象实例。要想实现深拷贝，就必须要修改clone()方法。
	public static void main(String[] args) throws CloneNotSupportedException {
		List<String> skillList = new ArrayList<String>();
		skillList.add("php");
		skillList.add("js");
		
		DPattern01Ch01_05_Resume09 resume = new DPattern01Ch01_05_Resume09("小樱");
		resume.setPersonal("女", 12, skillList);
		resume.setWork("2017-01-01", "souhu");
		System.out.println("1.创建初始化简历");
		resume.display();
		System.out.println("================================");
		
		DPattern01Ch01_05_Resume09 cloneResume = resume.clone();
		cloneResume.setName("小明");
		skillList.add("jsp");
		cloneResume.setPersonal("男", 13, skillList);
		cloneResume.setWork("2018-09-09", "tengxun");
		System.out.println("2.通过简历1复制出简历2，并对技能和经验进行修改");
		resume.display();
		cloneResume.display();
		System.out.println("================================");
	}
}

//简历
class DPattern01Ch01_05_Resume09 implements Cloneable{
	private String name;
	private String sex;
	private Integer age;
	private List<String> skillList = new ArrayList<String>();
	private DPattern01Ch01_05_WorkExperience09  workExperience = null;
	
	@Override
	protected DPattern01Ch01_05_Resume09 clone() throws CloneNotSupportedException {
		return (DPattern01Ch01_05_Resume09) super.clone();
	}
	public DPattern01Ch01_05_Resume09(String name) {
		this.name = name;
		workExperience = new DPattern01Ch01_05_WorkExperience09();
	}
	
	public void setPersonal(String sex,int age,List<String> skillList){
		this.sex = sex;
		this.age = age;
		this.skillList = skillList;
	}
	
	public void setWork(String timeArea,String company){
		this.workExperience.setTimeArea(timeArea);
		this.workExperience.setCompany(company);
	}

	public void display(){
		System.out.println("personalInfo：");
		System.out.println(this.name+"\t"+this.sex+"\t"+this.age);
		System.out.println("skillList：");
		for (String s : skillList) {
			System.out.println(s+"\t");
		}
		System.out.println("workExperience：");
		System.out.println(this.workExperience.getTimeArea() +"\t"+ this.workExperience.getCompany());
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}

class DPattern01Ch01_05_WorkExperience09{
	private String timeArea;
	private String company;
	public String getTimeArea() {
		return timeArea;
	}
	public void setTimeArea(String timeArea) {
		this.timeArea = timeArea;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
}