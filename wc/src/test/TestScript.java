package test;

import java.io.IOException;

public class TestScript {

	static String f = "D:\\project\\homework\\wc\\wc\\wcProject\\BIN\\wc.exe";

	public static void test(String cmd) {
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec(f + " " + cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testC() {
		String cmd = "wc.exe -c 1.txt";
		test(cmd);
	}

	public static void testL() {
		String cmd = "wc.exe -c -l 1.txt";
		test(cmd);
	}

	public static void testW() {
		String cmd = "wc.exe -c -l -w 1.txt";
		test(cmd);
	}

	public static void testO() {
		String cmd = "wc.exe -c -l -w 1.txt -o 2.txt";
		test(cmd);
	}

	public static void testS() {
		String cmd = "wc.exe -c -l -w -s *.txt -o 2.txt";
		test(cmd);
	}

	public static void testA() {
		String cmd = "wc.exe -c -l -w -a -s *.txt -o 2.txt";
		test(cmd);
	}

	public static void testE() {
		String cmd = "wc.exe -c -l -w -a -s *.txt -e stop.txt -o 2.txt";
		test(cmd);
	}

	public static void testX() {
		String cmd = "wc.exe -x";
		test(cmd);
	}

	public static void main(String[] args) {
//		testC();
//		testL();
//		testW();
//		testO();
//		testS();
//		testA();
//		testE();
		testX();
	}

}
