package Concurrent_001.T25;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T04_ConcurrentQueue {
	public static void main(String[] args) {
		Queue<Object> strs = new ConcurrentLinkedQueue<>();//无界队列
		for (int i = 0; i < 10; i++) {
			strs.offer("a"+i);// 带返回值的添加（返回值判断是否加成功）
		}
		System.out.println(strs);
		System.out.println(strs.size());
		
		System.out.println(strs.poll());//拿出队列中元素，并且删除队列中此元素
		System.out.println(strs.size());
		
		System.out.println(strs.peek());//拿出队列中元素，但不删除
		System.out.println(strs.size());
		
		/*双端队列 
		 addLast(e)
		 offerLast(e)
		 removeFirst()
		 pollFirst()
		 peekFirst()
		 
		 addFirst()
		 removeFirst()
		 peekFirst()
		 */
	}
}
