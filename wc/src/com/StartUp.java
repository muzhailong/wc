package com;

import java.io.File;

import com.core.WordCount;

public class StartUp {

	public static void main(String[]args) {
		WordCount wc=WordCount.newInstance();
		File f=new File("1.c");
		System.out.println(wc.e(f));
		System.out.println(wc.w(f));
		System.out.println(wc.l(f));
	}
}
