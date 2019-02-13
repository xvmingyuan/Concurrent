package Concurrent_001.T24;

import java.util.ArrayList;
import java.util.List;

/**
 * 卖火车票
 * 
 * 问题：ArrayList 不是线程安全的 不能再线程中使用
 * 
 * @author xmy
 * @time：2019年1月29日 下午2:32:27
 */
public class TicketSeller1 {
	static List<String> tickets = new ArrayList<>();
	static {
		for (int i = 0; i < 10000; i++) {
			tickets.add("票编号：" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (tickets.size() > 0) {
					System.out.println("销售" + tickets.remove(0));//问题：remove不是原子性的
				}
			}).start();
		}
	}
}
