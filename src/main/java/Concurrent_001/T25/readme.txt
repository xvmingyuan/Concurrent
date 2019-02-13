总结：
map/set :key value 和 key
不加锁
hashmap 无顺序 线程不安全
treemap(红黑树) 有顺序 线程不安全
linkedhashmap

低并发
Hashtable(使用较少)
Collections.synchronizedXXX

并发高：
concurrenthashmap 无顺序 线程安全
concurrentskiplistmap 有顺序 线程安全


List 接口
ArrayList 线程不安全
LinkedList 线程不安全
Collections.synchronizedXXX 低并发 同步手段
ConcurrentLinkedQueue 无界队列
CopyOnWriteList 写非常少，读特别多
Queue
	ConcurrentLinkedQueue 无界队列，高并发队列
	BlockingQueue 阻塞队列
		LinkedBQ 无界
		ArrayBQ  有界
		TransferQueue 消费者线程先行，否则阻塞，0容量队列
		SynchronusQueue 0容量队列
	DelayQueue 执行定时任务队列
	