package Concurrent_001.T23;

/**
 * 单例模式 （使用双重检测）
 * @author xmy
 * @time：2019年1月29日 上午11:23:32
 */
public class Singleton2check {
	private volatile static Singleton2check s;
	private Singleton2check() {
		System.out.println("Singleton2check");
	};


	public static Singleton2check newSingle() {
		if(s==null) {
			synchronized (Singleton2check.class) {
				if(s==null) {
					s = new Singleton2check();
				}
			}
		}
		return s;
	}
	
	public static void main(String[] args) {
		Singleton2check single = newSingle();
	}
}
