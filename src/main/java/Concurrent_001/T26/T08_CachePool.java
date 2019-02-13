package Concurrent_001.T26;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 缓存线程池
 * 开启线程上限与内存有关
 * @author xmy
 * @time：2019年2月5日 下午3:58:53
 */
public class T08_CachePool {
	public static void main(String[] args) throws InterruptedException {
		// 缓存线程池， 无界线程， 默认60s空闲，释放资源
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println(service);
		for (int i = 0; i < 2; i++) {
			service.execute(() -> {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		System.out.println(service);
		TimeUnit.SECONDS.sleep(65);
		System.out.println(service);
	}
}
