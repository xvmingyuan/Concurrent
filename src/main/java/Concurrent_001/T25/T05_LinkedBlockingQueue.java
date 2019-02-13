package Concurrent_001.T25;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
/**
 * java 实现的 生产者消费者模式队列：LinkedBlockingQueue 
 * 	    一个由链表结构组成的有界阻塞队列。
 * 		但大小默认值为Integer.MAX_VALUE，所以我们在使用	
 * 		时建议手动传值，为其提供我们所需的大小，避免队列过大造成机器负载或者内存爆满等情况
 * put(): 队列满了，会等待
 * take(): 队列空了，会等待
 * @author xmy
 * @time：2019年1月31日 下午3:23:01
 */
public class T05_LinkedBlockingQueue {
	static BlockingQueue<String> strs = new LinkedBlockingQueue<>();
	static Random r = new Random();

	public static void main(String[] args) {
		new Thread(()->{
			for (int i = 0; i < 100; i++) {
				try {
					strs.put("a"+i);// 如果满了，就会等待
					TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"p1").start();
		for (int i = 0; i < 5; i++) {
			new Thread(()->{
				for(;;)
					try {
						System.out.println(Thread.currentThread().getName()+"take -"+strs.take());//如果空了，就会等待
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			},"c"+i).start();
		}
	}
}
