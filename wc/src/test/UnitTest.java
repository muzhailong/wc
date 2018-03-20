package test;

import java.io.File;

import com.StartUp;
import com.core.WordCount;

public class UnitTest {

	// WordCount.c();
	public static void testC() {
		System.out.println(WordCount.newInstance()
				.c(new File("1.txt")));//文件存在
	}
	public static void testC2() {//文件不存在
		System.out.println(WordCount.
				newInstance().c(new File("131.txt")));
	}
	public static void testC3() {//文件为null
		System.out.println(WordCount.
				newInstance().c(null));
	}
	
	
	//WordCount.w()测试
	public static void testW1() {//没有停用词表
		System.out.println(WordCount.
				newInstance().w(new File("1.txt"),null));
	}
	
	public static void testW2() {//文件不存在
		System.out.println(WordCount.
				newInstance().w(new File("2112.txt"),null));
	}
	
	public static void testW3() {
		System.out.println(WordCount.//文件为空
				newInstance().w(null,null));
	}
	
	
	//WordCount.l()测试
	public static void testl1() {
		System.out.println(WordCount.//ok
				newInstance().l(new File("1.txt")));
	}
	
	public static void testl2() {
		System.out.println(WordCount.//文件不存在
				newInstance().l(new File("1dfdas.txt")));
	}
	
	public static void testl3() {
		System.out.println(WordCount.//文件不存在
				newInstance().l(null));
	}
	
	//WordCount.a()测试
	
	public static void testa1() {
		System.out.println(WordCount.//ok
				newInstance().a(new File("1.txt")));
	}
	
	public static void testa2() {
		System.out.println(WordCount.//文件不存在
				newInstance().a(new File("1dfdas.txt")));
	}
	
	public static void testa3() {
		System.out.println(WordCount.//文件为null
				newInstance().a(null));
	}
	public static void main(String[] args) {
		testC();
		testC2();
		testC3();
		testW1();
		testW2();
		testW3();
		testl1();
		testl2();
		testl3();
		testa1();
		testa2();
		testa3();
	}
}
