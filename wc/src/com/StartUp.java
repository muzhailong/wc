package com;

import java.io.File;

import com.core.WordCount;

public class StartUp {

	public static void main(String[]args) {
		WordCount wc=WordCount.newInstance();
		File f=new File("1.c");
		String[] arr="wc.exe -c -w -l -o out.txt".split(" ");
		wc.execute(arr, f);
	}
}
