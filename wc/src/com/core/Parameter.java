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
	private static final String[] mark =
			new String[] { "-c", "-w", "-l", "-o", "-a", "-s", "-e", "-x" };//标记
	private String[] args;//参数
	PrintWriter writer = null;//输出对象
	List<File> src = new LinkedList<File>();//源文件列表
	File srcFile = null;//源文件
	boolean tp = false;//是否有通配符
	String fn = null;//文件名
	File stopFile = null;//停用词表文件
	boolean[] action = new boolean[mark.length];//记录参数

	public Parameter(String[] args) {
		this.args = args;
	}

	private void setIn(File f) {//设置输入流
		if (f != null) {
			srcFile = f;
		} else {
			Set<String> set = new HashSet<String>();
			set.addAll(Arrays.asList(mark));
			String temp = null;
			for (int i = 1; i < args.length; ++i) {
				if (!set.contains(args[i])) {
					temp = args[i];
					if (!temp.contains("*")) {
						srcFile = new File(temp);
					} else {
						tp = true;
						fn = temp;
					}
					break;
				}
			}
		}
	}

	private void setOut(Writer w) {//设置输出流
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

	private void setStop(File f) {//设置停用词文件
		if (f != null) {
			stopFile = f;
		} else {
			List<String> list = Arrays.asList(args);
			if (list.indexOf(mark[6]) != -1) {
				stopFile = new File(list.get(list.indexOf(mark[6]) + 1));
				action[6] = true;
			}
		}
	}

	public void parse(File in, Writer out, File stopFile) {//参数解析
		Arrays.fill(action, false);
		List<String> list = Arrays.asList(args);

		setIn(in);
		setOut(out);
		setStop(stopFile);

		if (list.contains(mark[5])) {//"-s" 与"*"情况分析
			if (!tp) {
				File t=srcFile.getAbsoluteFile().getParentFile();
				src = WordCount.newInstance().s(t, srcFile.getName(), true);
			} else {
				src = WordCount.newInstance().s(new File("./").getAbsoluteFile(), fn, true);
			}

		} else {
			if (!tp) {
				src.add(srcFile);
			} else {
				src = WordCount.newInstance().s(new File("./").getAbsoluteFile(), fn, false);
			}
		}

		Set<String> st = new HashSet<String>();
		st.addAll(Arrays.asList(args));

		for (int i = 0; i < mark.length; ++i) {//解析剩下参数
			if (st.contains(mark[i])) {
				action[i] = true;
			}
		}
	}

}
