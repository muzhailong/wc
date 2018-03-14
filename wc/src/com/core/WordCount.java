package com.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class WordCount {
	private static WordCount wc = new WordCount();

	public static WordCount newInstance() {
		return wc;
	}

	public int e(File f) {
		if (!f.exists())
			return -1;
		BufferedReader reader = null;
		int res = 0;
		try {
			reader = new BufferedReader(new FileReader(f));
			String s = null;
			while ((s = reader.readLine()) != null) {
				res += s.length();
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
			StringBuilder sb=new StringBuilder();
			String s=null;
			while((s=reader.readLine())!=null) {
				sb.append(s);
			}
			reader.close();
			String[] arr=sb.toString().split(" |,");
			
			for(String temp:arr) {
				if(temp!=null&&!temp.equals("")) {
					++res;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
}
