package com.main.designpattern.designpattern01.chapter02;

/**
 * 
 *<p>Title	: DPattern01Ch02_06_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月26日下午6:40:18
 */
public class DPattern01Ch02_06_Test {
	/**2.5装饰器模式**/
	public static void main(String[] args) {
		DPattern01Ch02_06_HeroA heroA = new DPattern01Ch02_06_HeroA();
		System.out.println("heroA:");
		heroA.learnSkills();
		DPattern01Ch02_06_HeroB heroB = new DPattern01Ch02_06_HeroB();
		System.out.println("heroB:");
		heroB.learnSkills();
		DPattern01Ch02_06_CHeroDecorator cHeroDecorator = new DPattern01Ch02_06_CHeroDecorator(new DPattern01Ch02_06_HeroA());
		DPattern01Ch02_06_CHeroDecorator cHeroDecorator2 = new DPattern01Ch02_06_CHeroDecorator(new DPattern01Ch02_06_HeroB());
		System.out.println("heroA学习后:");
		cHeroDecorator.learnSkills();
		System.out.println("heroB学习后:");
		cHeroDecorator2.learnSkills();
	}
}

interface DPattern01Ch02_06_Hero{
	public void learnSkills();
}

class DPattern01Ch02_06_HeroA implements DPattern01Ch02_06_Hero{
	@Override
	public void learnSkills() {
		System.out.println("learnSkills() 返回了HeroA A技能");
	}
	
}

class DPattern01Ch02_06_HeroB implements DPattern01Ch02_06_Hero{
	@Override
	public void learnSkills() {
		System.out.println("leanSkills() 返回了HeroB B技能");
	}
	
}

abstract class DPattern01Ch02_06_HeroDecorator implements DPattern01Ch02_06_Hero{
	protected DPattern01Ch02_06_Hero heroDecorator;
	public DPattern01Ch02_06_HeroDecorator(DPattern01Ch02_06_Hero heroDecorator) {
		this.heroDecorator = heroDecorator;
	}
	@Override
	public void learnSkills() {
		heroDecorator.learnSkills();
	}
}

class DPattern01Ch02_06_CHeroDecorator extends  DPattern01Ch02_06_HeroDecorator{
	public DPattern01Ch02_06_CHeroDecorator(DPattern01Ch02_06_Hero heroDecorator) {
		super(heroDecorator);
	}
	
	@Override
	public void learnSkills() {
		super.learnSkills();
		newSkills();
	}
	
	public void newSkills(){
		System.out.println("新技能C");
	}
}










