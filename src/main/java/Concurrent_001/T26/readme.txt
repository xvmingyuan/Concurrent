总结：
Executor
ExecutorService
Callable = Runnable (Callable 有返回值)
Executors 线程池工厂工具
ThreadPool 线程池
Future 带返回值线程池

6种线程池：
	fixed ：固定线程池 （LinkedBlockingQueue:无界存放阻塞任务队列，大小于分配内存有关）
	cached ：缓存线程池 （SynchronousQueue:容量为0的队列，每次要进行offer操作时必须等待poll操作，否则不能继续添加元素）
	single ：串行线程池 （LinkedBlockingQueue）
	scheduled ：定周期线程池 （DelayedWorkQueue:无界队列,数组来储存队列中的元素，优先级队列，定时队列，周期队列）
	workstealing ：工作偷取线程池
	forkjoin ：归并线程池
	
底层基础：ThreadPoolExecutor(1,2,3,4,5,6) ->fixed,cached,single,scheduled
ParallerStreamAPI： 并发流式编程