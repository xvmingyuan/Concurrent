总结：
 * ThreadLocal线程局部变量
 * 
 * ThreadLocal 是 使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就纯在与ThreadLocal中，避免synchronized的使用
 * 
 * 每个线程都复制一个份 各自维护自己的那一份数据 互不影响
 * 每个线程可以有自己的Session，这样多线程时Session 不会冲突