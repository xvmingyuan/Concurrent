package Concurrent_001.T13;

import java.util.ArrayList;
/**
 * 无同步加法操作
 * @author xmy
 * @time：2019年2月13日 下午3:23:13
 */
public class T {
	 volatile int count = 0;

	 void m() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}
	
	public static void main(String[] args) {
		T t = new T();
		ArrayList<Thread> threads = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(t::m,"thread"+i));
		}
		threads.forEach((o)->o.start());
		
		threads.forEach((o)->{
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println(t.count);
	}
}
