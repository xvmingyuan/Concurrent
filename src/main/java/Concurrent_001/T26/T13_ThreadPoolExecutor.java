package Concurrent_001.T26;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T13_ThreadPoolExecutor {
	public static void main(String[] args) {
		/**
		 * new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler)
		 * corePoolSize:核心线程池大小（可以空闲）
		 * maximumPoolSize：最大线程数量（空闲+运行）
		 * keepAliveTime：线程池中超过corePoolSize数目的空闲线程最大存活时间；可以allowCoreThreadTimeOut(true)使得核心线程有效时间
		 * unit：keepAliveTime时间单位
		 * workQueue：阻塞队列（
		 	LinkedBlockingQueue:无界存放阻塞任务队列，大小于分配内存有关
		 	SynchronousQueue:容量为0的队列，每次要进行offer操作时必须等待poll操作，否则不能继续添加元素
		 	ArrayBlockingQueue:有界队列,数组结构的
		 	DelayQueue:无界队列 (带时间，周期性队列）: 执行定时任务
		 	LinkedTransferQueue:消费者先启动，生产者生产，如果有消费者等待，就直接将产物给消费者，如果找不到消费者，生产者会阻塞
		 	DelayedWorkQueue:无界队列,数组来储存队列中的元素，优先级队列，定时队列，周期队列
		 	ForwardingBlockingQueue:
		 	PriorityBlockingQueue:
		 * ）
		 * threadFactory:新建线程工厂 。默认：DefaultThreadFactory
		 * handler:当提交任务数超过maxmumPoolSize+workQueue之和时，任务会交给RejectedExecutionHandler来处理 默认：AbortPolicy
		 * 
		 * 
		 * 
		 * 说明：
		 * 	1.当线程池小于corePoolSize时，新提交任务将创建一个新线程执行任务，即使此时线程池中存在空闲线程。 
			2.当线程池达到corePoolSize时，新提交任务将被放入workQueue中，等待线程池中任务调度执行 
			3.当workQueue已满，且maximumPoolSize>corePoolSize时，新提交任务会创建新线程执行任务 
			4.当提交任务数超过maximumPoolSize时，新提交任务由RejectedExecutionHandler处理 
			5.当线程池中超过corePoolSize线程，空闲时间达到keepAliveTime时，关闭空闲线程 
			6.当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭 
		 */
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		
//		
	}
}
