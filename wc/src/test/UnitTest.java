package test;

import com.StartUp;

public class UnitTest {
	
	
	//测试字符数
	public static  void testC() {
		String[] args="wc.exe -c 1.txt".split(" ");
		StartUp.start(args);
	}
	
	//测试字符数、单词数目
	public static void testW() {
		String[]args="wc.exe -c -w 1.txt".split(" ");
		StartUp.start(args);
	}
	
	//测试字符数、单词数目、行数
	public static void testL() {
		String[]args="wc.exe -c -w -l 1.txt".split(" ");
		StartUp.start(args);
	}
	//测试字符数、单词数目、行数、输出文件
	public static void testO() {
		String[]args="wc.exe -c -w -l 1.txt -o 2.c".split(" ");
		StartUp.start(args);
	}
	
	//测试字符数、单词数目、行数、输出文件、递归文件夹
	public static void testS() {
		String[]args="wc.exe -c -w -l -s 1.txt -o 2.c".split(" ");
		StartUp.start(args);
	}
	
	//测试字符数、单词数目、行数、输出文件、递归文件夹、复杂结构
	public static void testA() {
		String[]args="wc.exe -l -c -w -s -a 1.txt -o 2.c".split(" ");
		StartUp.start(args);
	}
	
	//测试停用词表//测试字符数、单词数目、行数、输出文件、递归文件夹、复杂结构、停用词表
	public static void testE() {
		String[]args="wc.exe -l -c -w -s -a 1.txt -o 2.c -e stop.txt".split(" ");
		StartUp.start(args);
	}
	
	//高级功能测试
	//测试停用词表//测试字符数、单词数目、行数、输出文件、递归文件夹、复杂结构、停用词表、通配符
	public static void testP() {
		String[]args="wc.exe -l -c -w -s -a *.txt -o 2.c -e stop.txt".split(" ");
		StartUp.start(args);
	}
	
	//图形界面测试
	public static void testX() {
		String[]args="wc.exe -x".split(" ");
		StartUp.start(args);
	}
	
	public static void testE2() {
		String[]args="wc.exe -w 1.txt -e stop.txt".split(" ");
		StartUp.start(args);
	}
	
	public static void testPath() {
		String[]args="wc.exe -c -l -w D:\\project\\homework\\wc\\wc\\wc\\*.txt -e stop.txt -o 2.txt".split(" ");
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
//		UnitTest.testE2();
//		UnitTest.testPath();
	}
}
