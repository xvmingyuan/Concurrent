package Concurrent_001.T25;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue：有界队列
 * 一个由数组结构组成的有界阻塞队列。
 * 其内部按先进先出的原则对元素进行排序,其中put方法和	
 * take方法为添加和删除的阻塞方法
 * @author xmy
 * @time：2019年1月27日 下午4:47:44
 */

public class T06_ArrayBlockingQueue {
	static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
	static Random r = new Random();

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			strs.put("a" + i);
		}
		strs.put("aaa");// 满了会阻塞，程序无限等待
		strs.add("aaa");// 当队列满了，add添加会报异常
		boolean offer = strs.offer("aaa");//当队列满了，offer添加不会报异常，返回boolean值判断是否添加成功
		boolean offer2 = strs.offer("aaa", 1, TimeUnit.SECONDS);//按时间段添加，1秒钟内加不进去，就不加了,返回boolean值判断是否添加成功
		System.out.println(strs);
	}

}
