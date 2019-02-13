package Concurrent_001.T25;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
/**
 * SynchronousQueue:容量为0的队列
 * @author xmy
 * @time：2019年1月31日 下午5:32:05
 */
public class T09_SynchronusQueue {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> strs = new SynchronousQueue<>();
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		strs.put("aaa");// 阻塞 等待消费者消费
		strs.add("aaa"); // 报错异常
		System.out.println(strs.size());
	}
}
