package Concurrent_001.T12;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字， 使用一个变量在多线程间可见
 * A B线程都用到一个变量，java默认是A线城中保留一份copy，这样如果B线程修改了该变量，则A线城未必知道。
 * 使用volatile 关键字 会让所有线程都会读到变量的修改值
 * 
 * 在下面的代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会吧runing值从内存中读到t1线程的工作区，在运行过程中世界使用这个copy，并不会每次都去
 * 读取堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 * 
 * 使用volatile， 将会强制多有线程都去堆内存中读取running的值
 * 
 * volatile并不能保存多个线程共同修改running变量是所带来的不一致问题（一致写不能保证），volatile不能代替synchronized
 * @author xmy
 * @time：2019年1月27日 下午2:53:43
 */
public class T {
	volatile boolean running = true;
	void m() {
		System.out.println("m start");
		while(running) {
			
		}
		System.out.println("m end");
		
	}
	public static void main(String[] args) {
		T t = new T();
		new Thread(t::m,"t1").start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t.running = false;
		
	}

}
