package com.main.designpattern.designpattern01.chapter03;

/**
 * 
 *<p>Title	: DPattern01Ch03_01_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月27日下午12:44:22
 */
public class DPattern01Ch03_01_Test {
	/**3.行为型模式**/
	/**3.1责任链模式**/
	//顾名思义，责任链模式（Chain of Responsibility Pattern）为请求创建了一个接收者对象的链。这种模式给予请求的类型，对请求的发送者和接收者进行解耦。这种类型的设计模式属于行为型模式。
	//在这种模式中，通常每个接收者都包含对另一个接收者的引用。如果一个对象不能处理该请求，那么它会把相同的请求传给下一个接收者，依此类推。
	//优点： 1、降低耦合度。它将请求的发送者和接收者解耦。 2、简化了对象。使得对象不需要知道链的结构。 3、增强给对象指派职责的灵活性。通过改变链内的成员或者调动它们的次序，允许动态地新增或者删除责任。 4、增加新的请求处理类很方便。
	//缺点： 1、不能保证请求一定被接收。 2、系统性能将受到一定影响，而且在进行代码调试时不太方便，可能会造成循环调用。
	public static void main(String[] args) {
		//3.创建不同类型的记录器。赋予他们不同的错误级别,并在每个记录器中设置下一个记录器。每个记录器中的下一个记录器代表的是链的一部分。
		DPattern01Ch03_01_AbstractLogger loggerChain = getChainOfLoggers();
		System.out.println("Info Level:");
		loggerChain.logMessage(DPattern01Ch03_01_AbstractLogger.INFO, "This is an info level information.");
		System.out.println("Debug Level:");
		loggerChain.logMessage(DPattern01Ch03_01_AbstractLogger.DEBUG, "This is an debug level information.");
		System.out.println("Error Level:");
		loggerChain.logMessage(DPattern01Ch03_01_AbstractLogger.ERROR, "This is an error level information.");
		
		
	}
	
	private static DPattern01Ch03_01_AbstractLogger getChainOfLoggers(){
		DPattern01Ch03_01_ErrorLogger errorLogger = new DPattern01Ch03_01_ErrorLogger(DPattern01Ch03_01_AbstractLogger.ERROR);
		DPattern01Ch03_01_FileLogger fileLogger = new DPattern01Ch03_01_FileLogger(DPattern01Ch03_01_AbstractLogger.DEBUG);
		DPattern01Ch03_01_ConsoleLogger consoleLogger = new DPattern01Ch03_01_ConsoleLogger(DPattern01Ch03_01_AbstractLogger.INFO);
		
		errorLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(consoleLogger);
		return errorLogger;
		
	}
	
}

//1.创建抽象的记录器类。
abstract class DPattern01Ch03_01_AbstractLogger{
	public static int INFO = 1;
	public static int DEBUG = 2;
	public static int ERROR = 3;
	
	protected int level;
	protected DPattern01Ch03_01_AbstractLogger nextLogger;//责任链中的下一个元素
	public void setNextLogger(DPattern01Ch03_01_AbstractLogger nextLogger){
		this.nextLogger = nextLogger;
	}
	public void logMessage(int level,String message){
		if(this.level <= level){
			write(message);
		}
		if(nextLogger != null){
			nextLogger.logMessage(level, message);
		}
	}
	
	abstract protected void write(String message);
}

//2.创建扩展了该记录器类的实体类。
class DPattern01Ch03_01_ConsoleLogger extends DPattern01Ch03_01_AbstractLogger{
	public DPattern01Ch03_01_ConsoleLogger(int level) {
		this.level = level;
	}
	@Override
	protected void write(String message) {
		System.out.println("ConsoleLogger.Standard Console logger :"+message);
	}
	
}

class DPattern01Ch03_01_FileLogger extends DPattern01Ch03_01_AbstractLogger{
	public DPattern01Ch03_01_FileLogger(int level) {
		this.level = level;
	}
	@Override
	protected void write(String message) {
		System.out.println("FileLogger.File Console logger :"+message);
	}
	
}

class DPattern01Ch03_01_ErrorLogger extends DPattern01Ch03_01_AbstractLogger{
	public DPattern01Ch03_01_ErrorLogger(int level) {
		this.level = level;
	}
	@Override
	protected void write(String message) {
		System.out.println("ErrorLogger.Error Console logger :"+message);
	}
	
}

























