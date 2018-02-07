package com.main.designpattern.designpattern01.chapter01;

/**
 * 
 *<p>Title	:DPattern01Ch01_05_Test04.java
 * @Description :
 * @author 	: admin
 * @date	: 2018年2月6日
 */
public class DPattern01Ch01_05_Test04 {
	//实现浅拷贝实例(发送银行电子广告邮件)：
	public static int MAX_COUNT = 5;
	public static void main(String[] args) {
		//发送邮件
		DPattern01Ch01_05_Mail04 mail = new DPattern01Ch01_05_Mail04(new DPattern01Ch01_05_MailTemp04());
		mail.setTail("xxxxx银行的所有版权");
		for (int i = 0; i < MAX_COUNT; i++) {
			try {
				DPattern01Ch01_05_Mail04 cloneMail = mail.clone();
				cloneMail.setSub(DPattern01Ch01_05_Test03.getRandName(2)+"(先生/女士)");
				cloneMail.setReceiver(DPattern01Ch01_05_Test03.getRandString(5)+"@"+DPattern01Ch01_05_Test03.getRandNum(3)+".com");
				sendMail(cloneMail);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void sendMail(DPattern01Ch01_05_Mail04 mail){
		System.out.println("标题："+mail.getSub()+"\t收件人："+mail.getReceiver()+"\t...发送成功");
	}
}

//银行电子广告
class DPattern01Ch01_05_Mail04 implements Cloneable{
	public String receiver;//接收者
	public String tail;//结尾备注
	private String sub;//标题
	private String context;//内容
	
	public DPattern01Ch01_05_Mail04(DPattern01Ch01_05_MailTemp04 mailTemp) {
		this.sub = mailTemp.getSubString();
		this.context = mailTemp.getMainContentString();
	}

	public DPattern01Ch01_05_Mail04 clone() throws CloneNotSupportedException{
		DPattern01Ch01_05_Mail04 mail = (DPattern01Ch01_05_Mail04) super.clone();
		return mail;
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

class DPattern01Ch01_05_MailTemp04{
	public String subString;//标题
	public String mainContentString;//广告内容
	public String getSubString() {
		return "xxx(先生/女士)";
	}
	public String getMainContentString() {
		return "xxxxx银行账单";
	}
	
}