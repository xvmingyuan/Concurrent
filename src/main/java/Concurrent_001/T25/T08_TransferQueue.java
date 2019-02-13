package Concurrent_001.T25;

import java.util.concurrent.LinkedTransferQueue;
/**
 * 消费者先启动，生产者生产，如果有消费者等待 就直接将产物给消费者
 * transfer 如果找不到消费者，生产者会阻塞
 * @author xmy
 * @time：2019年1月31日 下午5:27:08
 */
public class T08_TransferQueue {
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

		new Thread(() -> {
			try {
//				消费者 消费等待
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
//		生产者生产
		strs.transfer("aaa");//
		
//		new Thread(() -> {
//			try {
//				System.out.println(strs.take());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}).start();
	}
}
