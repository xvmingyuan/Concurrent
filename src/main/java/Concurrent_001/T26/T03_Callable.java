package Concurrent_001.T26;

import java.util.concurrent.Callable;

/**
 * Callable 有返回值 
 * Callable 与 Runnable 区别：Callable 有返回值 可以抛出异常，Runnable 不可以
 * @author xmy
 * @param <V>
 * @time：2019年2月1日 下午3:07:07
 */
public class T03_Callable<V> implements Callable<V>{
	Object a;
	@Override
	public V call() throws Exception {
		a = "aaa";
		return (V) a;
	}

}
