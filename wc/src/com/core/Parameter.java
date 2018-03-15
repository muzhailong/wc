package com.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
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

	public void setIn(File f) {
		if (f != null) {
			srcFile = f;
		} else {
			Set<String> set = new HashSet<String>();
			set.addAll(Arrays.asList(mark));
			String temp = null;
			for (int i = 1; i < args.length; ++i) {
				if (!set.contains(args[i])) {
					temp = args[i];
					break;
				}
			}
		}
	}

	public void setOut(Writer w) {
		if (w != null) {
			writer = new PrintWriter(w);
		} else {
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
		}
	}

	public void parse(File in, Writer out) {
		Arrays.fill(action, false);
		List<String> list = Arrays.asList(args);

		setIn(in);
		setOut(out);

		if (list.contains(mark[5])) {
			src = WordCount.newInstance().s(srcFile.getParentFile(), srcFile.getName(), true);
		} else {
			src.add(srcFile);
		}

		if (list.indexOf(mark[6]) != -1) {
			stopFile = new File(list.get(list.indexOf(mark[6]) + 1));
			action[6] = true;
		}

		Set<String> st = new HashSet<String>();
		st.addAll(Arrays.asList(args));

		for (int i = 0; i < mark.length; ++i) {
			if (st.contains(mark[i])) {
				action[i] = true;
			}
		}
	}

}
