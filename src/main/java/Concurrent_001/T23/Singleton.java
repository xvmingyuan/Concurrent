package Concurrent_001.T23;
/**
 * 单例模式 （使用内部类实现）
 * 
 * 不需要加锁 也可以实现懒加载
 * @author xmy
 * @time：2019年1月29日 上午11:23:32
 */
public class Singleton {
	private Singleton() {
		System.out.println("single");
	};

	private static class Inner {
		private static Singleton s = new Singleton();
	}

	public static Singleton newSingle() {
		return Inner.s;
	}
	
	public static void main(String[] args) {
		Singleton single = newSingle();
	}
}
