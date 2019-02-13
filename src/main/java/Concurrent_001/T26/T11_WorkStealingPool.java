package Concurrent_001.T26;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 工作窃取功能线程池
 * 描述：当前自己线程执行完任务，会去偷取其他线程排队等待的任务，放在自己的线程上执行
 * @author xmy
 * @time：2019年2月9日 下午12:13:00
 */
public class T11_WorkStealingPool {
	public static void main(String[] args) throws IOException {
		ExecutorService service = Executors.newWorkStealingPool();
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		service.execute(new R(1000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		//由于产生的是精灵线程，（守护线程，后台线程）主线程不阻塞，看不到输出
		System.in.read();
	}

	static class R implements Runnable {
		int time;
		public R(int t) {
			this.time = t;
		}
		@Override
		public void run() {
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(time +" "+Thread.currentThread().getName());
		}
	}
}
