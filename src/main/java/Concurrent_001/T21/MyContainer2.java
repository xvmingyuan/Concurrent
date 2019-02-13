package Concurrent_001.T21;
/**
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程和10个消费者线程的阻塞调用
 * 
 * 使用 lock和Condition 来实现
 *
 * 操作： 创建producer Condition 用来控制生产者线程，创建consumer Condition 用来控制消费者线程，
 *  当list到达MAX时，调用生产者await()方法，停止生产，并使用signalAll通知消费者消费。
 *  当list到达0时，调用消费者await()方法，停止消费，并使用signalAll通知生产者生产。
 * 
 * @author xmy
 * @time：2019年1月28日 下午3:24:26
 * @param <T>
 */

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer2<T> {

	final private LinkedList<T> list = new LinkedList<>();
	final private int MAX = 10;
	private int count = 0;

	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();

	public void put(T t) {
		try {
			lock.lock();
			while (list.size() == MAX) {
				producer.await();// 生产者等待
			}
			list.add(t);
			++count;
			consumer.signalAll();// 通知消费者进行消费
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public T get() {
		T t = null;
		try {
			lock.lock();
			while (list.size() == 0) {
				consumer.await();//消费者等待
			}
			t = list.removeFirst();
			count--;
			producer.signalAll();// 通知生产者 进行生产
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return t;
	}

	public static void main(String[] args) {
		MyContainer2<String> c = new MyContainer2<>();
		// 启动消费者线程
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 5; j++) {
					System.out.println(c.get());
				}
			}, "cc" + i).start();
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 启动生产者线程
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				for (int j = 0; j < 25; j++) {
					c.put(Thread.currentThread().getName() + " " + j);

				}
			}, "pp" + i).start();
		}
	}

}
