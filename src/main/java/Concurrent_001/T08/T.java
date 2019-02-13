package Concurrent_001.T08;

import java.util.concurrent.TimeUnit;

/**
 * 脏读 
 * 解决： 加读锁
 * @author xmy
 * @time：2019年1月24日 下午5:08:46
 */
public class T {
	String name;
	double balance;

	public synchronized void set(String name, double balance) {
		this.name = name;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.balance = balance;
	}
	public double getBalance(String name) {
		return this.balance;
	}
	public static void main(String[] args) {
		T t = new T();
		new Thread(()->t.set("zs", 100.0)).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t.getBalance("zs"));
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t.getBalance("zs"));
	}
}
