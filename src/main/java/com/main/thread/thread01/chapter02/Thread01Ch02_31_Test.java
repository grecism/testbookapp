package com.main.thread.thread01.chapter02;

import com.main.thread.thread01.chapter02.Thread01Ch02_31_PublicClass.PrivateClass;

/**
 * 
 *<p>Title	: Thread01Ch02_31_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月12日下午3:28:58
 */
public class Thread01Ch02_31_Test {
	/**2.2.13 内置类与静态内置类**/
	//2.2.13 内置类与静态内置类
	//-1内置类顺序打印
	//-1如果PublicClass类与Test类不在同一个包中,则需要将PrivateClass类内置声明成public公开的。
	public static void main(String[] args) {
		Thread01Ch02_31_PublicClass pub = new Thread01Ch02_31_PublicClass();
		pub.setUsername("usernamevalue");
		pub.setPassword("passwordvalue");
		System.out.println("username="+pub.getUsername()+" password:"+pub.getPassword());
		PrivateClass pri = pub.new PrivateClass();//实例化内置类
		pri.setAge("agevalue");
		pri.setAddress("addressvalue");
		System.out.println("age="+pri.getAge()+" address="+pri.getAddress());
	}
}

class Thread01Ch02_31_PublicClass {
	private String username;
	private String password;
	class PrivateClass{
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
