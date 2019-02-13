package Concurrent_001.T26;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class T14_ParallerStreamAPI {
	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < 100000; i++) {
			nums.add(1000000 + r.nextInt(2000000));
		}
		long start = System.currentTimeMillis();
		nums.forEach(v->isPrime(v));
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
		//
		start = System.currentTimeMillis();
		nums.parallelStream().forEach(T14_ParallerStreamAPI::isPrime);
		long count = nums.parallelStream().count();
		end = System.currentTimeMillis();
//		System.out.println(count);
		System.out.println(end-start);
	}

	static boolean isPrime(int num) {
		for (int i = 2; i < num / 2; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}
}
