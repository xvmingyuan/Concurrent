package Concurrent_001.T19;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个容器，提供两个方法， add ，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监视元素的个数，当个数到5时，线程2给出提示并结束
 * 问题： t2不结束
 * @author xmy
 * @time：2019年1月27日 下午4:50:52
 */
public class MyContainer1 {
	List<Object> list = new ArrayList<>();
	
	public  void add(Object o) {
		list.add(o);
	}
	
	public int size() {
		return list.size();
	}
	public static void main(String[] args) {
		MyContainer1 myContainer1 = new MyContainer1();
		new Thread(()->{
			for (int i = 0; i < 10; i++) {
				myContainer1.add(new Object());
				System.out.println("add"+i);
			}
		},"t1").start();
		
		new Thread(()->{
			while(true) {
				if(myContainer1.size() == 5) {
					break;
				}
			}
			System.out.println("t2 结束");
		},"t2").start();;
	}
}
