package Concurrent_001.T24;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * 使用synchronized
 * 问题：效率不高
 * @author xmy
 * @time：2019年1月29日 下午3:53:48
 */
public class TicketSeller3 {

	static List<String> tickets = new LinkedList<>();
	static {
		for (int i = 0; i < 1000; i++) {
			tickets.add("票编号：" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
					synchronized (tickets) {
						if (tickets.size() <= 0) {
							break;
						}
						try {
							TimeUnit.MILLISECONDS.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("销售" + tickets.remove(0));
					}

				}
			}).start();
		}
	}

}
