package com.main.designpattern.designpattern01.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *<p>Title	:DPattern01Ch01_05_Test11.java
 * @Description :
 * @author 	: admin
 * @date	: 2018年2月7日
 */
public class DPattern01Ch01_05_Test11 {
	//实现深拷贝实例(发送简历)：
	public static void main(String[] args) throws CloneNotSupportedException {
		List<String> skillList = new ArrayList<String>();
		skillList.add("php");
		skillList.add("js");
		
		DPattern01Ch01_05_Resume11 resume = new DPattern01Ch01_05_Resume11("小樱");
		resume.setPersonal("女", 12, skillList);
		resume.setWork("2017-01-01", "souhu");
		System.out.println("1.创建初始化简历");
		resume.display();
		System.out.println("================================");
		
		DPattern01Ch01_05_Resume11 cloneResume = resume.clone();
		cloneResume.setName("小明");
		cloneResume.setPersonal("男", 13, skillList);
		cloneResume.getSkillList().add("jsp");
		cloneResume.setWork("2018-09-09", "tengxun");
		System.out.println("2.通过简历1复制出简历2，并对技能和经验进行修改，skillList还是会修改小樱的skillList");
		resume.display();
		cloneResume.display();
		System.out.println("================================");
		
		DPattern01Ch01_05_Resume11 cloneResume2 = resume.clone();
		cloneResume2.setName("小明");
		cloneResume2.setPersonalInfo("男", 13);
		cloneResume2.getSkillList().add("pyson");
		cloneResume2.setWork("2018-09-09", "tengxun");
		System.out.println("3.通过简历1复制出简历2，并对技能和经验进行修改，skillList不会修改小樱的skillList");
		resume.display();
		cloneResume2.display();
		System.out.println("================================");
	}
}

//简历
class DPattern01Ch01_05_Resume11 implements Cloneable{
	private String name;
	private String sex;
	private Integer age;
	private List<String> skillList = new ArrayList<String>();
	private DPattern01Ch01_05_WorkExperience11  workExperience = null;
	
	@Override
	protected DPattern01Ch01_05_Resume11 clone() throws CloneNotSupportedException {
		DPattern01Ch01_05_Resume11 cloneResume = (DPattern01Ch01_05_Resume11) super.clone();
		List<String> _skillList = new ArrayList<String>(this.skillList);
		DPattern01Ch01_05_WorkExperience11 _workExperience = new DPattern01Ch01_05_WorkExperience11();
		_workExperience.setTimeArea(this.workExperience.getTimeArea());
		_workExperience.setCompany(this.workExperience.getCompany());
		
		cloneResume.setSkillList(_skillList);
		cloneResume.setWorkExperience(_workExperience);
		
		return cloneResume;
	}
	public DPattern01Ch01_05_Resume11(String name) {
		this.name = name;
		workExperience = new DPattern01Ch01_05_WorkExperience11();
	}
	
	public void setPersonal(String sex,int age,List<String> skillList){
		this.sex = sex;
		this.age = age;
		this.skillList = skillList;
	}
	
	public void setPersonalInfo(String sex,int age){
		this.sex = sex;
		this.age = age;
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
	public void setSkillList(List<String> skillList) {
		this.skillList = skillList;
	}
	public List<String> getSkillList() {
		return skillList;
	}
	public void setWorkExperience(DPattern01Ch01_05_WorkExperience11 workExperience) {
		this.workExperience = workExperience;
	}
	
	
}

class DPattern01Ch01_05_WorkExperience11{
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