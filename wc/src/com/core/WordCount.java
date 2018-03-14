package com.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class WordCount {
	private static WordCount wc = new WordCount();
	private static final String[] mark = new String[] { "-c", "-w", "-l", "-o" };

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
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return res;
	}

	public int w(File f) {
		if (!f.exists())
			return -1;
		int res = 0;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(f));
			StringBuilder sb = new StringBuilder();
			String s = null;
			while ((s = reader.readLine()) != null) {
				sb.append(s);
			}
			String[] arr = sb.toString().split(" |,");

			for (String temp : arr) {
				if (temp != null && !temp.equals("")) {
					++res;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	public int l(File f) {
		if (!f.exists())
			return -1;
		BufferedReader reader = null;
		int res = 0;
		try {
			reader = new BufferedReader(new FileReader(f));
			while (reader.readLine() != null) {
				++res;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	public void execute(String[] args,File f) {
		
		List<String> arr = Arrays.asList(args);
		int t = arr.indexOf(mark[3]);
		PrintWriter writer = null;
		if(t==-1) {
			writer=new PrintWriter(System.out);
		}else {
			try {
				writer=new PrintWriter(args[t+1]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		StringBuilder res=new StringBuilder();
		
		if(arr.contains(mark[0])) {
			int ec=e(f);
			res.append(f.getName()+","+"字符数:"+ec+"\n");
		}
		
		if(arr.contains(mark[1])) {
			int wc=w(f);
			res.append(f.getName()+","+"单词数:"+wc+"\n");
		}
		
		if(arr.contains(mark[2])) {
			int lc=l(f);
			res.append(f.getName()+","+"行数:"+lc+"\n");
		}
		writer.write(res.toString());
		writer.close();
	}
	
}
