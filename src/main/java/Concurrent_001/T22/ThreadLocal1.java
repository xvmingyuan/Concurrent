package Concurrent_001.T22;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author xmy
 * @time：2019年1月29日 上午10:28:07
 */
public class ThreadLocal1 {
	volatile static Person p= new Person();
	public static void main(String[] args) {
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(p.name);
		}).start();
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			p.name="lisi";
		}).start();
		
		
	}
}
class Person{
	public String name = "zhangsan";
	public int age = 10;
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
