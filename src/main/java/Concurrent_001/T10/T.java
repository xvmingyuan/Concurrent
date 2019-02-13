package Concurrent_001.T10;
/**
 * 子类的同步方法 可以调用父类的同步方法
 */
import java.util.concurrent.TimeUnit;

public class T {
	synchronized void m() {
		System.out.println("m .start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m end");
	}
	
	public static void main(String[] args) {
		new TT().m();
		
	}
}
class TT extends T{
	@Override
	synchronized void m() {
		System.out.println("child m start");
		super.m();
		System.out.println("child m end");
	}
}
