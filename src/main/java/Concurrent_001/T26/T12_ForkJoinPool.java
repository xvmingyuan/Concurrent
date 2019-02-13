package Concurrent_001.T26;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import Concurrent_001.T26.T12_ForkJoinPool.AddTask;

/**
 * Fork-Join思想 归并思想 描述：大任务分隔成小任务处理完再合并
 * 
 * @author xmy
 * @time：2019年2月9日 下午12:36:19
 */
public class T12_ForkJoinPool {
	static int[] nums = new int[1000000];
	static final int MAX_NUM = 50000;
	static Random r = new Random();

	static {
		for (int i = 0; i < nums.length; i++) {
			nums[i] = r.nextInt(100);
		}
		System.out.println(Arrays.stream(nums).sum());
	}

	/**
	 * 不含返回值
	 */
	// static class AddTask extends RecursiveAction {
	// int start, end;
	//
	// public AddTask(int s, int e) {
	// start = s;
	// end = e;
	// }
	//
	// @Override
	// protected void compute() {
	// if (end - start <= MAX_NUM) {
	// long sum = 0;
	// for (int i = start; i < end; i++) {
	// sum += nums[i];
	// }
	// System.out.println("from:" + start + " to:" + end + " = " + sum);
	// } else {
	// // 递归调用，拆分任务
	// int middle = start + (end - start) / 2;
	// AddTask subTask1 = new AddTask(start, middle);
	// AddTask subTask2 = new AddTask(middle, end);
	// subTask1.fork();
	// subTask2.fork();
	//
	// }
	// }
	//
	// }
	/**
	 * 含返回值
	 */
	static class AddTask extends RecursiveTask<Long> {
		int start, end;

		public AddTask(int s, int e) {
			start = s;
			end = e;
		}

		@Override
		protected Long compute() {
			if (end - start <= MAX_NUM) {
				long sum = 0L;
				for (int i = start; i < end; i++) {
					sum += nums[i];
				}
				System.out.println("from:" + start + " to:" + end + " = " + sum);
				return sum;
			} else {
				int middle = start + (end - start) / 2;
				AddTask addTask = new AddTask(start, middle);
				AddTask addTask2 = new AddTask(middle, end);
				//执行任务
				addTask.fork();
				addTask2.fork();
				// 获取返回值
				return addTask.join() + addTask2.join();
			}

		}

	}

	public static void main(String[] args) throws IOException {
		ForkJoinPool fjp = new ForkJoinPool();
		AddTask task = new AddTask(0, nums.length);
		//执行服务
		fjp.execute(task);
		//获取任务返回值（总值）
		long result = task.join();
		System.out.println(result);
		// System.in.read();
	}
}
