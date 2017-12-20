package com.main.thread.thread01.chapter03;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 
 *<p>Title	: Thread01Ch03_25_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月19日下午4:59:12
 */
public class Thread01Ch03_25_Test {
	/**3.1.13 通过管道进行线程间通信:字符流**/
	//3.1.13 通过管道进行线程间通信:字符流
	//此实验是在两个线程中通过管道流进行字符数据的传输。
	//-1从1开始读取。
	//-1两个线程中通过管道流进行字符数据的传输。
	public static void main(String[] args) {
		try {
			Thread01Ch03_25_WriteData write = new Thread01Ch03_25_WriteData();
			Thread01Ch03_25_ReadData read = new Thread01Ch03_25_ReadData();
			PipedWriter writer = new PipedWriter();
			PipedReader reader = new PipedReader();
			//writer.connect(reader);
			reader.connect(writer);
			Thread01Ch03_25_Thread_Write twrite = new Thread01Ch03_25_Thread_Write(write,writer);
			twrite.start();
			Thread01Ch03_25_Thread_Read tread = new Thread01Ch03_25_Thread_Read(read,reader);
			tread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_25_WriteData{
	public void writeMethod(PipedWriter out){
		try {
			System.out.println("write begin:");
			for (int i = 0; i < 200; i++) {
				String outData = ""+(i+1);
				out.write(outData);
				System.out.println(outData);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_25_ReadData{
	public void readMethod(PipedReader reader){
		try {
			System.out.println("read begin:");
			char[] charArray = new char[20];
			int len;
			while((len = reader.read(charArray)) != -1){
				String readData = new String(charArray,0,len);
				System.out.println(readData);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_25_Thread_Write extends Thread{
	private Thread01Ch03_25_WriteData write;
	private PipedWriter out;
	public Thread01Ch03_25_Thread_Write(Thread01Ch03_25_WriteData write,PipedWriter out) {
		this.write = write;
		this.out = out;
	}
	@Override
	public void run() {
		super.run();
		write.writeMethod(out);
	}
}

class Thread01Ch03_25_Thread_Read extends Thread{
	private Thread01Ch03_25_ReadData read;
	private PipedReader reader;
	public Thread01Ch03_25_Thread_Read(Thread01Ch03_25_ReadData read,PipedReader reader) {
		this.read = read;
		this.reader = reader;
	}
	@Override
	public void run() {
		super.run();
		read.readMethod(reader);
	}
}