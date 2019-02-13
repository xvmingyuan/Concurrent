package Concurrent_001.T24;

import java.util.Vector;
import java.util.concurrent.TimeUnit;
/**
 * 使用Vector
 * 
 * 问题：while判断和remove组合之间是非原子性的，会引起不同步移除问题
 * @author xmy
 * @time：2019年1月29日 下午2:35:55
 */
public class TicketSeller2 {
	// 原子性操作
	static Vector<String> tickets = new Vector<>();
	static {
		for (int i = 0; i < 1000; i++) {
			tickets.add("票编号：" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (tickets.size() > 0) {//问题：while判断和remove组合之间是非原子性的，会引起不同步移除问题
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("销售" + tickets.remove(0));
				}
			}).start();
		}
	}

}
