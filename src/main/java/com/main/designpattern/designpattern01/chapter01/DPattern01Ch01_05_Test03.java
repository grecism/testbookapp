package com.main.designpattern.designpattern01.chapter01;

import java.util.Random;

/**
 * 
 *<p>Title	:DPattern01Ch01_05_Test03.java
 * @Description :
 * @author 	: admin
 * @date	: 2018年2月6日
 */
public class DPattern01Ch01_05_Test03 {
	//发送银行电子广告邮件(没有使用原型模式)：
	//把sendMail修改为多线程，但是你只把 sendMail修改为多线程还是有问题的，产生第一封邮件对象，放到线程 1 中运行，还没有发送出去；线程 2 呢也也启动了，直接就把邮件对象 mail的标题和收件人地址改掉了，
	//线程不安全了，多种方法中使用原型模式来解决这个问题，使用对象的拷贝功能来解决这个问题。
	public static int MAX_COUNT = 5;
	public static void main(String[] args) {
		//发送邮件
		DPattern01Ch01_05_Mail03 mail = new DPattern01Ch01_05_Mail03(new DPattern01Ch01_05_MailTemp03());
		mail.setTail("xxxxx银行的所有版权");
		for (int i = 0; i < MAX_COUNT; i++) {
			mail.setSub(getRandName(2)+"(先生/女士)");
			mail.setReceiver(getRandString(5)+"@"+getRandNum(3)+".com");
			sendMail(mail);
		}
		
	}
	
	private static void sendMail(DPattern01Ch01_05_Mail03 mail){
		System.out.println("标题："+mail.getSub()+"\t收件人："+mail.getReceiver()+"\t...发送成功");
	}
	
	public static String getRandName(int maxLength){
		String sourceData = "樱桃小丸子欧阳上官赵钱孙李周吴郑王姗姗来迟";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < maxLength; i++) {
			sb.append(sourceData.charAt(random.nextInt(sourceData.length())));
		}
		return sb.toString();
	}
	
	public static String getRandNum(int maxLength){
		String sourceData = "123456789";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < maxLength; i++) {
			sb.append(sourceData.charAt(random.nextInt(sourceData.length())));
		}
		return sb.toString();
	}
	
	public static String getRandString(int maxLength){
		String sourceData = "abcdefghijklmnopqrskuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < maxLength; i++) {
			sb.append(sourceData.charAt(random.nextInt(sourceData.length())));
		}
		return sb.toString();
	}
}

//银行电子广告
class DPattern01Ch01_05_Mail03{
	public String receiver;//接收者
	public String tail;//结尾备注
	private String sub;//标题
	private String context;//内容
	
	public DPattern01Ch01_05_Mail03(DPattern01Ch01_05_MailTemp03 mailTemp) {
		this.sub = mailTemp.getSubString();
		this.context = mailTemp.getMainContentString();
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getTail() {
		return tail;
	}

	public void setTail(String tail) {
		this.tail = tail;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	
	
}

class DPattern01Ch01_05_MailTemp03{
	public String subString;//标题
	public String mainContentString;//广告内容
	public String getSubString() {
		return "xxx(先生/女士)";
	}
	public String getMainContentString() {
		return "xxxxx银行账单";
	}
	
}