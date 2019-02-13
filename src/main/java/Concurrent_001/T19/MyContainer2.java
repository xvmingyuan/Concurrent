package Concurrent_001.T19;
/**
 * 实现一个容器，提供两个方法， add ，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监视元素的个数，当个数到5时，线程2给出提示并结束
 * 分析： 给list 加上volatile之后 t2能够收到通知，t2线程 while 循环浪费cpu ，如果不用死循环怎么做？
 * @author xmy
 * @time：2019年1月27日 下午4:50:52
 */
import java.util.ArrayList;
import java.util.List;

public class MyContainer2 {
	/**volatile 使线程间可见*/
	volatile List<Object> list = new ArrayList<>();
	
	public  void add(Object o) {
		list.add(o);
	}
	
	public int size() {
		return list.size();
	}
	public static void main(String[] args) {
		MyContainer2 myContainer2 = new MyContainer2();
		new Thread(()->{
			for (int i = 0; i < 10; i++) {
				myContainer2.add(new Object());
				System.out.println("add"+i);
			}
		},"t1").start();
		
		new Thread(()->{
			while(true) {
				if(myContainer2.size() == 5) {
					break;
				}
			}
			System.out.println("t2 结束");
		},"t2").start();;
	}

}
