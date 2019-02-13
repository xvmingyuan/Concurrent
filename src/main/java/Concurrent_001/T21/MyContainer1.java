package Concurrent_001.T21;
/**
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程和10个消费者线程的阻塞调用
 * 
 * 使用 wait 和 nofityAll实现
 * @author xmy
 * @time：2019年1月28日 下午3:24:26
 * @param <T>
 */

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class MyContainer1<T> {
	final private LinkedList<T> list = new LinkedList<>();
	final private int MAX = 10;
	private int count = 0;

	public synchronized void put(T t) {
		while (list.size() == MAX) {
			try {
				this.wait(); //effective java: wait 往往和 while一起使用
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		list.add(t);
		++count;
		this.notifyAll(); // 通知消费者进行消费
	}

	public synchronized T get() {
		T t = null;
		while (list.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = list.removeFirst();
		count--;
		this.notifyAll(); // 通知生产者 进行生产
		return t; 
	}
	public static void main(String[] args) {
		MyContainer1<String> c = new MyContainer1<>();
		// 启动消费者线程
		for (int i = 0; i < 10; i++) {
			new  Thread(()->{
				for (int j = 0; j < 5; j++) {
					System.out.println(c.get());
				}
			},"c"+i).start();
		}
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//启动生产者线程
		for (int i = 0; i < 2; i++) {
			new Thread(()->{
				for (int j = 0; j < 25; j++) {
					c.put(Thread.currentThread().getName()+" "+j);
					
				}
			},"p"+i).start();
		}
	}
	

}
