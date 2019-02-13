package Concurrent_001.T25;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import Concurrent_001.T25.T07_DelayQueue.MyTask;

/**
 * DelayQueue（带时间，周期性队列）:无界队列， 执行定时任务
 * 类必须实现Delayed接口
 * @author xmy
 * @time：2019年1月27日 下午4:47:44
 */
public class T07_DelayQueue {
	static BlockingQueue<MyTask> tasks = new DelayQueue<>();
	static Random r = new Random();

	static class MyTask implements Delayed {
		long runningTime;

		public MyTask(long rt) {
			this.runningTime = rt;
		}

		@Override
		public int compareTo(Delayed o) {
			if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
				return -1;
			} else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
				return 1;
			} else {
				return 0;
			}
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}
		@Override
		public String toString() {
			return ""+runningTime;
		}
		
		public static void main(String[] args) throws InterruptedException {
			long  now = System.currentTimeMillis();
			MyTask myTask = new MyTask(now+1000);//现在开始 1秒后执行
			MyTask myTask1 = new MyTask(now+2000);
			MyTask myTask2 = new MyTask(now+1500);
			MyTask myTask3 = new MyTask(now+2500);
			MyTask myTask4 = new MyTask(now+500);
			tasks.put(myTask);
			tasks.put(myTask1);
			tasks.put(myTask2);
			tasks.put(myTask3);
			tasks.put(myTask4);
			System.out.println(tasks);
			for (int i = 0; i < 5; i++) {
				System.out.println(tasks.take());
			}
		}

	}
}
