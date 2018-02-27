package com.main.designpattern.designpattern01.chapter02;

/**
 * 
 *<p>Title	: DPattern01Ch02_01_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月26日下午1:09:06
 */
public class DPattern01Ch02_01_Test {
	/**2.结构型模式**/
	/**2.1适配器模式**/
	//适配器模式（Adapter Pattern）是作为两个不兼容的接口之间的桥梁。这种类型的设计模式属于结构型模式，它结合了两个独立接口的功能。
	//这种模式涉及到一个单一的类，该类负责加入独立的或不兼容的接口功能。举个真实的例子，读卡器是作为内存卡和笔记本之间的适配器。您将内存卡插入读卡器，
	//再将读卡器插入笔记本，这样就可以通过笔记本来读取内存卡。
	//我们通过下面的实例来演示适配器模式的使用。其中，音频播放器设备只能播放 mp3 文件，通过使用一个更高级的音频播放器来播放 mp4 和 avi 文件。
	//优点： 1、可以让任何两个没有关联的类一起运行。 2、提高了类的复用。 3、增加了类的透明度。 4、灵活性好。
	//缺点： 1、过多地使用适配器，会让系统非常零乱，不易整体进行把握。比如，明明看到调用的是 A 接口，其实内部被适配成了 B 接口的实现，一个系统如果太多出现这种
	//情况，无异于一场灾难。因此如果不是很有必要，可以不使用适配器，而是直接对系统进行重构。 2.由于 JAVA 至多继承一个类，所以至多只能适配一个适配者类，
	//而且目标类必须是抽象类。
	public static void main(String[] args) {
		DPattern01Ch02_01_AudioPlayer audioPlayer = new DPattern01Ch02_01_AudioPlayer();
		audioPlayer.play("mp3", "mine mine.mp3");
		audioPlayer.play("mp4", "let it go.mp4");
		audioPlayer.play("avi", "try everything.avi");
		audioPlayer.play("mp5", "other.mp5");
	}
}

//1.为媒体播放器和更高级的媒体播放器创建接口。
interface DPattern01Ch02_01_MediaPlayer{
	public void play(String audioType,String fileName);
}

interface DPattern01Ch02_01_AdvancedMediaPlayer{
	public void playMp4(String fileName);
	public void playAvi(String fileName);
}

//2.创建实现了AdvancedMediaPlayer接口的实体类。
class DPattern01Ch02_01_Mp4Player implements DPattern01Ch02_01_AdvancedMediaPlayer{
	@Override
	public void playMp4(String fileName) {
		System.out.println("Mp4格式 is playing:"+fileName);
	}

	@Override
	public void playAvi(String fileName) {
	}
	
}

class DPattern01Ch02_01_AviPlayer implements DPattern01Ch02_01_AdvancedMediaPlayer{
	@Override
	public void playMp4(String fileName) {
	}

	@Override
	public void playAvi(String fileName) {
		System.out.println("Avi格式 is playing:"+fileName);
	}
	
}

//3.创建实现了MediaPlayer接口的适配器类。
class DPattern01Ch02_01_MediaAdapter implements DPattern01Ch02_01_MediaPlayer{
	DPattern01Ch02_01_AdvancedMediaPlayer advancedMediaPlayer;
	public DPattern01Ch02_01_MediaAdapter(String audioType) {
		if("Mp4".equalsIgnoreCase(audioType)){
			advancedMediaPlayer = new DPattern01Ch02_01_Mp4Player();
		}else if("Avi".equalsIgnoreCase(audioType)){
			advancedMediaPlayer = new DPattern01Ch02_01_AviPlayer();
		}
	}
	@Override
	public void play(String audioType, String fileName) {
		if("Mp4".equalsIgnoreCase(audioType)){
			advancedMediaPlayer.playMp4(fileName);
		}else if("Avi".equalsIgnoreCase(audioType)){
			advancedMediaPlayer.playAvi(fileName);
		}
	}
	
}

//4.创建实现了MediaPlayer接口的实体类。
class DPattern01Ch02_01_AudioPlayer implements DPattern01Ch02_01_MediaPlayer{
	DPattern01Ch02_01_MediaAdapter mediaAdapter;
	@Override
	public void play(String audioType, String fileName) {
		//播放mp3文件的内置支持
		if("mp3".equalsIgnoreCase(audioType)){
			System.out.println("mp3格式 is playing:"+fileName);
		}else if("mp4".equalsIgnoreCase(audioType) || "avi".equalsIgnoreCase(audioType)){
			//mediaAdapter 提供了播放其他文件格式的支持
			mediaAdapter = new DPattern01Ch02_01_MediaAdapter(audioType);
			mediaAdapter.play(audioType, fileName);
		}else{
			System.out.println("Invalid media !"+audioType+"format not supported !");
		}
	}
	
}











