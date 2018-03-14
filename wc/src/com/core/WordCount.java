package com.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {
	private WordCount wc = new WordCount();

	public WordCount newInstance() {
		return wc;
	}

	public int e(File f) {
		if (!f.exists())
			return -1;
		BufferedReader reader = null;
		int res=0;
		try {
			reader = new BufferedReader(new FileReader(f));
			String s=null;
			while((s=reader.readLine())!=null) {
				res+=s.length();
			}
			reader.close();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return res;
	}
	
	
	public int w(File f) {
		if(!f.exists()) return -1;
		int res=0;
		BufferedReader reader=null;
		try {
			reader=new BufferedReader(new FileReader(f));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
