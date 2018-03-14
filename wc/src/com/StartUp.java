package com;

import java.io.File;

import com.core.Parameter;
import com.core.WordCount;

public class StartUp {

	public static void main(String[]args) {
		WordCount wc=WordCount.newInstance();
		String[] arr="wc.exe -c -w -l -a *.txt -e stop.txt -s -o f.txt".split(" ");
		Parameter p=new Parameter(arr);
		p.parse();
		wc.execute(p);
		
	}
}
