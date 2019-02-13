package Concurrent_001.T26;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 构造一个固定线程数目的线程池，配置的corePoolSize与maximumPoolSize大小相同，
 * 同时使用了一个无界LinkedBlockingQueue存放阻塞任务，
 * 因此多余的任务将存在再阻塞队列，不会由RejectedExecutionHandler处理 
 * @author xmy
 * @time：2019年2月12日 下午5:40:58
 */
public class T05_FixedThreadPool {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 6; i++) {
			service.execute(()->{
				try { 
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		System.out.println(service);
		service.shutdown();
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
	}
}
