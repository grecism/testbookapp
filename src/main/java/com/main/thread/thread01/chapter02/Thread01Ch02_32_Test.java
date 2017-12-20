package com.main.thread.thread01.chapter02;

import com.main.thread.thread01.chapter02.Thread01Ch02_32_PublicClass.PrivateClass;


/**
 * 
 *<p>Title	: Thread01Ch02_32_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日下午3:39:59
 */
public class Thread01Ch02_32_Test {
	//2.2.13 内置类与静态内置类
	//-2静态内置类顺序打印
	//-2静态内置类顺序打印
	public static void main(String[] args) {
		Thread01Ch02_32_PublicClass pub = new Thread01Ch02_32_PublicClass();
		pub.setUsername("usernamevalue");
		pub.setPassword("passwordvalue");
		System.out.println("username="+pub.getUsername()+" password:"+pub.getPassword());
		PrivateClass pri = new PrivateClass();//实例化静态内置类
		pri.setAge("agevalue");
		pri.setAddress("addressvalue");
		System.out.println("age="+pri.getAge()+" address="+pri.getAddress());
	}
}

class Thread01Ch02_32_PublicClass {
	static private String username;
	static private String password;
	static class PrivateClass{
		private String age;
		private String address;
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
		public void printPublicProperty(){
			System.out.println("username="+username+" password="+password);
		}
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
