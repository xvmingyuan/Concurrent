总结：
ArrayList Vector synchronized ConcurrentLinkedQueue 实现售票机制对比
TicketSeller1.java 不是线程安全的 不能再线程中使用
TicketSeller2.java ：while判断和remove组合之间是非原子性的，会引起不同步移除问题
TicketSeller3.java ：使用synchronized 效率不高
TicketSeller4.java : ConcurrentLinkedQueue 使用并发队列提高效率，简化代码