总结：
 volatile 使线程间可见
 wait 线程等待，让出监视器CPU资源
 notify 唤醒一个等待线程（无法指定）
 notifyAll 唤醒所有等待的线程
 CountDownLatch(num)  （num = 线程等待数）--》发令枪
 	方法 ：await()：需要的线程等待
 		countDown() ：当CountDownLatch 减到0是 await() 线程被启动继续执行