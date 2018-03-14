package com.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Parameter {
	private static final String[] mark = new String[] { "-c", "-w", "-l", "-o", "-a", "-s", "-e", "-x" };
	private String[] args;
	PrintWriter writer = null;
	List<File> src = new LinkedList<File>();
	File srcFile = null;
	boolean tp = false;
	String fn = null;
	File stopFile = null;
	boolean[] action = new boolean[mark.length];

	public Parameter(String[] args) {
		this.args = args;
	}

	public void parse() {
		Arrays.fill(action, false);
		List<String> list = Arrays.asList(args);
		int t = list.indexOf(mark[3]);

		try {
			File f = null;
			if (t == -1) {
				f = new File("result.txt");
			} else {
				f = new File(list.get(t + 1));
			}

			if (!f.exists()) {
				f.createNewFile();
			}
			writer = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Set<String> set = new HashSet<String>();
		set.addAll(Arrays.asList(mark));
		String tmp = null;
		for (int i = 1; i < list.size(); ++i) {
			if (!set.contains(list.get(i))) {
				tmp = list.get(i);
				break;
			}
		}

		if (!tmp.contains("*")) {
			srcFile = new File(tmp);
			fn=tmp;
		} else {
			tp = true;
			fn = tmp;
			srcFile=new File("./");
		} // 通配符

		if (list.contains(mark[5])) {
			if (!tp) {
				src = WordCount.newInstance().s(srcFile);
			} else {
				src = WordCount.newInstance().s(srcFile, fn,true);
			}
		} else {
			if (!tp) {
				src.add(srcFile);
			}else {
				src=WordCount.newInstance().s(srcFile,fn,false);
			}
		}

		if (list.indexOf(mark[6]) != -1) {
			stopFile = new File(list.get(list.indexOf(mark[6])+1));
			action[6] = true;
		}
		
		Set<String> st=new HashSet<String>();
		st.addAll(Arrays.asList(args));
		
		for (int i = 0; i < mark.length; ++i) {
			if (st.contains(mark[i])) {
				action[i] = true;
			}
		}
	}

}
