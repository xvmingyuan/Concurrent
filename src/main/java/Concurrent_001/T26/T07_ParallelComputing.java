package Concurrent_001.T26;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * 线程池
 * 并行
 * nasa
 * @author xmy
 * @time：2019年2月1日 下午4:58:22
 */
public class T07_ParallelComputing {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		List<Integer> prime = getPrime(1, 200000);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println("质数个数："+prime.size());
		final int cpuCoreNum = 4;
		MyTask t1 = new MyTask(1, 80000);
		MyTask t2 = new MyTask(80001, 130000);
		MyTask t3 = new MyTask(130001, 170000);
		MyTask t4 = new MyTask(170001, 200000);

		ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
		Future<List<Integer>> f1 = service.submit(t1);
		Future<List<Integer>> f2 = service.submit(t2);
		Future<List<Integer>> f3 = service.submit(t3);
		Future<List<Integer>> f4 = service.submit(t4);

		start = System.currentTimeMillis();
		List<Integer> list = f1.get();
		List<Integer> list2 = f2.get();
		List<Integer> list3 = f3.get();
		List<Integer> list4 = f4.get();
		
		end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println("质数个数："+(list.size()+list2.size()+list3.size()+list4.size()));
		service.shutdown();
	}

	static class MyTask implements Callable<List<Integer>> {
		int startPos, endPos;

		public MyTask(int s, int c) {
			this.startPos = s;
			this.endPos = c;
		}

		@Override
		public List<Integer> call() throws Exception {
			List<Integer> r = getPrime(startPos, endPos);
			return r;
		}

	}

	static boolean isPrime(int num) {
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	static List<Integer> getPrime(int start, int end) {
		List<Integer> result = new ArrayList<>();
		for (int i = start; i < end; i++) {
			if (isPrime(i)) {
				result.add(i);
			}
		}
		return result;
	}
}
