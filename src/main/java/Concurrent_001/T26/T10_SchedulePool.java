package Concurrent_001.T26;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 定时执行线程池 定时执行任务 线程定时器功能
 * 构造有定时功能的线程池，配置corePoolSize，无界延迟阻塞队列DelayedWorkQueue；
 * 有意思的是：maximumPoolSize=Integer.MAX_VALUE，由于DelayedWorkQueue是无界队列，所以这个值是没有意义的 
 * @author xmy
 * @time：2019年2月8日 下午12:25:09
 */
public class T10_SchedulePool {
	volatile static int i = 0;
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(0);
		
		/**
		 * 无返回值，有初始延迟的固定频率的周期执行方法
		 */
		service.scheduleAtFixedRate(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}, 0, 2, TimeUnit.SECONDS);
		/**
		 * 带返回值(ScheduledFuture)，无初始延迟的固定频率的周期执行方法
		 */
		ScheduledFuture<Object> schedule = service.schedule(new T03_Callable<>(), 2, TimeUnit.SECONDS);
		while (true) {
			Object object = null;
			try {
				TimeUnit.SECONDS.sleep(2);
				object = schedule.get();
				synchronized (schedule) {
					i++;
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			System.out.println(object);
			if (i == 10) {
				break;
			}
		}

		/**
		 * 无返回值，无初始延迟的固定频率的周期执行方法
		 */
		// service.schedule(command, delay, unit);
		/**
		 * 无返回值，有初始延迟的固定频率的周期执行方法
		 */
		// service.scheduleWithFixedDelay(command, initialDelay, delay, unit);
	}
}
