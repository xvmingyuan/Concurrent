package Concurrent_001.T26;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程 线程池，保证任务由一个线程串行执行
 * @author xmy
 * @time：2019年2月8日 上午11:22:17
 */
public class T09_SingleThreadPool {
	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			final int  j  = i;
			service.execute(()->{
				System.out.println(j+"_"+Thread.currentThread().getName());
			});
			
		}
	}
}
