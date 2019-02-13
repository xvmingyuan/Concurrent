package Concurrent_001.T24;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 使用队列
 * 
 * @author xmy
 * @time：2019年1月29日 下午3:57:14
 */
public class TicketSeller4 {

	static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	static {
		for (int i = 0; i < 1000; i++) {
			tickets.add("票编号：" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
					// 取票
					String s = tickets.poll();
					if (s == null) {
						break;
					} else {
						System.out.println("4销售" + s);
					}

				}
			}).start();
		}
	}

}
