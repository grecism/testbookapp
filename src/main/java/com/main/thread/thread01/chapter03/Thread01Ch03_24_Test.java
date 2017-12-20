package com.main.thread.thread01.chapter03;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 
 *<p>Title	: Thread01Ch03_24_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月19日下午3:56:32
 */
public class Thread01Ch03_24_Test {
	/**3.1.12 通过管道进行线程间通信:字节流**/
	//管道流(pipeStream)是一种特殊的流,用于在不同线程间直接传送数据。一个线程发送数据到输出管道,另一个线程从输入管道中读数据。通过使用管道,实现不同线程间
	//的通信,而无需借助于类似临时文件之类的东西。
	//在java的JDK中提供了4个类来使线程间可以进行通信:
	//PipedInputStream和PipedOutputStream
	//PipedReader和PipedWrite
	//3.1.12 通过管道进行线程间通信:字节流
	//-1从1开始读取
	//-1使用代码inputStream.connect(outputStream)或outputStream.connect(inputStream)的作用使两个Stream之间产生通信连接,这样才可以将数据
	//进行输出与输入。
	//读取数据线程先启动,由于当时没有数据被写入,所以线程阻塞在input.read(byteArray)代码中,直到有数据被写入,才继续向下运行。
	public static void main(String[] args) {
		try {
			Thread01Ch03_24_WriteData  writeData = new Thread01Ch03_24_WriteData();
			Thread01Ch03_24_ReadData readData = new Thread01Ch03_24_ReadData();
			PipedOutputStream outputStream = new PipedOutputStream();
			PipedInputStream inputStream = new PipedInputStream();
			outputStream.connect(inputStream);
			//inputStream.connect(outputStream);
			Thread01Ch03_24_Thread_Write twrite = new Thread01Ch03_24_Thread_Write(writeData,outputStream);
			twrite.start();
			Thread01Ch03_24_Thread_Read tread = new Thread01Ch03_24_Thread_Read(readData,inputStream);
			tread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_24_WriteData{
	public void writeMethod(PipedOutputStream out){
		try {
			System.out.println("write begin:");
			for (int i = 0; i < 200; i++) {
				String ourData = ""+(i+1);
				out.write(ourData.getBytes());
				System.out.println(ourData);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_24_ReadData{
	public void readMethod(PipedInputStream input){
		try {
			byte[] byteArray = new byte[20];
			int len;
			while((len = input.read(byteArray)) != -1){
				String readData = new String(byteArray,0,len);
				System.out.println(readData);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Thread01Ch03_24_Thread_Write extends Thread{
	private Thread01Ch03_24_WriteData write;
	private PipedOutputStream out;
	public Thread01Ch03_24_Thread_Write(Thread01Ch03_24_WriteData write,PipedOutputStream out) {
		this.write = write;
		this.out = out;
	}
	@Override
	public void run() {
		super.run();
		write.writeMethod(out);
	}
}

class Thread01Ch03_24_Thread_Read extends Thread{
	private Thread01Ch03_24_ReadData read;
	private PipedInputStream input;
	public Thread01Ch03_24_Thread_Read(Thread01Ch03_24_ReadData read,PipedInputStream input) {
		this.read = read;
		this.input = input;
	}
	@Override
	public void run() {
		super.run();
		read.readMethod(input);
	}
}













