总结：
 使用reentrantlock 可以完成同样的功能 需要注意的是，必须必须必须要手动释放锁（重要的事说三遍） 使用sync锁定的话，
 如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放。
 使用reentrantlock 可以进行“尝试锁定” trylock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待。
 使用ReetrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应，在一个线程等待锁的过程中，可以被打断
 ReentrantLock(true) 指定为公平锁 