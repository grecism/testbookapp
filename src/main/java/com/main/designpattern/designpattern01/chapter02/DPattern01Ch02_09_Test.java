package com.main.designpattern.designpattern01.chapter02;

/**
 * 
 *<p>Title	: DPattern01Ch02_09_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月27日上午11:23:37
 */
public class DPattern01Ch02_09_Test {
	/**2.8代理模式**/
	//在代理模式（Proxy Pattern）中，一个类代表另一个类的功能。这种类型的设计模式属于结构型模式。
	//在代理模式中，我们创建具有现有对象的对象，以便向外界提供功能接口。
	//优点： 1、职责清晰。 2、高扩展性。 3、智能化。
	//缺点： 1、由于在客户端和真实主题之间增加了代理对象，因此有些类型的代理模式可能会造成请求的处理速度变慢。 2、实现代理模式需要额外的工作，有些代理模式的实现非常复杂。 
	//实现:
	//我们将创建一个 Image 接口和实现了 Image 接口的实体类。ProxyImage 是一个代理类，减少 RealImage 对象加载的内存占用。
	//ProxyPatternDemo，我们的演示类使用 ProxyImage 来获取要加载的 Image 对象，并按照需求进行显示。
	public static void main(String[] args) {
		//3.当被请求时,使用ProxyImage来获取RealImage类的对象。
		DPattern01Ch02_09_Image image = new DPattern01Ch02_09_ProxyImage("image.png");
		System.out.println("图像将从磁盘加载");
		image.display();
		System.out.println("图像无法从磁盘加载");
		image.display();
		
	}
}

//1.创建一个接口。
interface DPattern01Ch02_09_Image{
	public void display();
}

//2.创建实现接口的实体类。
class DPattern01Ch02_09_RealImage implements DPattern01Ch02_09_Image{
	private String fileName;
	public DPattern01Ch02_09_RealImage(String fileName) {
		this.fileName = fileName;
		loadFromDisk(fileName);
	}
	@Override
	public void display() {
		System.out.println("RealImage Displaying:"+fileName);
	}
	private void loadFromDisk(String fileName) {
		System.out.println("RealImage Loading:"+fileName);
	}
}

class DPattern01Ch02_09_ProxyImage implements DPattern01Ch02_09_Image{
	private DPattern01Ch02_09_RealImage realImage;
	private String fileName;
	public DPattern01Ch02_09_ProxyImage(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public void display() {
		if(realImage == null){
			realImage = new DPattern01Ch02_09_RealImage(fileName);
		}
		realImage.display();
	}
	
}


































