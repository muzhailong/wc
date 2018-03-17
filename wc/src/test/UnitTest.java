package test;

import com.StartUp;

public class UnitTest {
	
	
	//测试字符数，单词数
	public static  void testC() {
		String[] args="wc.exe -c 1.txt".split(" ");
		StartUp.start(args);
	}
	
	//测试单词数目
	public static void testW() {
		String[]args="wc.exe -w 1.txt".split(" ");
		StartUp.start(args);
	}
	
	//测试行数
	public static void testL() {
		String[]args="wc.exe -l 1.txt".split(" ");
		StartUp.start(args);
	}
	//测试输出文件
	public static void testO() {
		String[]args="wc.exe -l 1.txt -o 2.c".split(" ");
		StartUp.start(args);
	}
	//基本功能联合测试
	public static void testCWLO() {
		String[]args="wc.exe -l -c -w 1.txt -o 2.c".split(" ");
		StartUp.start(args);
	}
	
	//测试处理目录下符合文件
	public static void testS() {
		String[]args="wc.exe -l -c -w -s 1.txt -o 2.c".split(" ");
		StartUp.start(args);
	}
	
	//测试返回复杂的数据
	public static void testA() {
		String[]args="wc.exe -l -c -w -s -a 1.txt -o 2.c".split(" ");
		StartUp.start(args);
	}
	
	//测试停用词表
	public static void testE() {
		String[]args="wc.exe -l -c -w -s -a 1.txt -o 2.c -e stop.txt".split(" ");
		StartUp.start(args);
	}
	
	//高级功能测试
	//通配符测试
	public static void testP() {
		String[]args="wc.exe -l -c -w -s -a *.txt -o 2.c -e stop.txt".split(" ");
		StartUp.start(args);
	}
	
	//图形界面测试
	public static void testX() {
		String[]args="wc.exe -x".split(" ");
		StartUp.start(args);
	}
	
	
	
	
	
	
	
	public static void main(String[]args) {
//		UnitTest.testc();
//		UnitTest.testW();
//		UnitTest.testL();
//		UnitTest.testO();
//		UnitTest.testCWLO();
//		UnitTest.testS();
//		UnitTest.testA();
//		UnitTest.testE();
//		UnitTest.testP();
		UnitTest.testX();
	}
}
