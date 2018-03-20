package com.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.util.Utils;

public class WordCount {
	private static WordCount wc = new WordCount();//单例模式

	public static WordCount newInstance() {
		return wc;
	}

	public int c(File f) {//字符记数
		if (f==null||!f.exists())
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

	public int w(File f, Set<String> set) {//单词记数
		if (f==null||!f.exists())
			return -1;
		int res = 0;
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(f));
			String s = null;
			while ((s = reader.readLine()) != null) {
				String[] arr = s.split(" |,");
				for (String temp : arr) {
					temp=temp.trim();
					if (set == null) {
						if (temp != null && !temp.equals("")) {
							++res;
						}
					} else {
						if (temp != null && !temp.equals("") && !set.contains(temp)) {
							++res;
						}
					}
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

	public int w(File f) {//单词记数重载 无停用词表
		return w(f, null);
	}

	public int l(File f) {//行数记数
		if (f==null||!f.exists())
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

	public List<File> s(File f) {//递归获取文件
		List<File> list = new LinkedList<File>();
		s(f, list);
		return list;
	}

	private void s(File dir, List<File> list) {//递归获取文件 具体实现
		if (!dir.exists())
			return;

		if (dir.isFile()) {
			list.add(dir);
			return;
		}
		File[] fileArr = dir.listFiles();

		for (File tmp : fileArr) {
			if (tmp.isDirectory()) {
				s(tmp, list);
			} else if (tmp.isFile()) {
				list.add(tmp);
			}
		}
	}

	public List<File> s(File f, String pattern, boolean deep) {//深度递归
		List<File> list = new LinkedList<File>();
		if (deep) {
			s(f, list, pattern);
		} else {
			for (File temp : f.listFiles()) {
				if (temp.isFile() && Utils.match(pattern, temp.getName())) {
					list.add(temp);
				}
			}
		}
		return list;
	}

	private void s(File dir, List<File> list, String pattern) {//根据具体的模式判断
		if (dir == null)
			return;

		if (dir.isFile()) {
			if (Utils.match(pattern, dir.getName()))
				;
			{
				list.add(dir);
			}
			return;
		}
		for (File temp : dir.listFiles()) {
			if (temp.isFile() && Utils.match(pattern, temp.getName())) {
				list.add(temp);
			} else if (temp.isDirectory()) {
				s(temp, list, pattern);
			}
		}
	}

	private static class Block {//-a参数的存储结构
		int codeLine;
		int emptyLine;
		int noteLine;

		public Block(int codeLine, int emptyLine, int noteLine) {
			this.codeLine = codeLine;
			this.emptyLine = emptyLine;
			this.noteLine = noteLine;
		}
	}

	public Block a(File f) {// -a的具体实现
		int codeLine = 0, emptyLine = 0, noteLine = 0;
		BufferedReader reader = null;
		if(f==null||!f.exists()) return null;
		try {
			reader = new BufferedReader(new FileReader(f));
			String s = null;
			while ((s = reader.readLine()) != null) {
				s = s.trim();
				if (s.length() == 0) {
					++emptyLine;
				} else {
					if (s.startsWith("//")) {
						++noteLine;
					} else {
						++codeLine;
					}
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
				e.printStackTrace();
			}
		}

		return new Block(codeLine, emptyLine, noteLine);
	}

	public Set<String> e(File f) {//获取停用词
		Set<String> set = new HashSet<String>();
		BufferedReader reader = null;
		if (!f.exists())
			return set;
		try {
			reader = new BufferedReader(new FileReader(f));
			StringBuilder sb=new StringBuilder();
			String s = null;
			while ((s = reader.readLine()) != null) {
				sb.append(s);
			}
			set.addAll(Arrays.asList(sb.toString().split(" ")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return set;
	}

	public void execute(Parameter p) {//根据参数解析的结果 执行
		StringBuilder res = new StringBuilder();
		for (File f : p.src) {
			res.append(run(f, p));
		}
		p.writer.write(res.toString());
		p.writer.flush();
	}

	private String run(File f, Parameter p) {
		StringBuilder sb = new StringBuilder();
		if (p.action[0]) {
			sb.append(cw(f.getName(), c(f)));
		}

		if (p.action[1]) {
			if(!p.action[6]) {
				sb.append(ww(f.getName(), w(f)));
			}else {
				sb.append(ww(f.getName(), w(f,e(p.stopFile))));
			}
		}

		if (p.action[2]) {
			sb.append(lw(f.getName(), l(f)));
		}

		if (p.action[4]) {
			sb.append(aw(f.getName(), a(f)));
		}
		return sb.toString();
	}

	
	//辅助方法
	private static String cw(String name, int num) {
		return name + "," + "字符数:" + num + "\n";
	}

	private static String ww(String name, int num) {
		return name + "," + "单词数:" + num + "\n";
	}

	private static String lw(String name, int num) {
		return name + "," + "行数:" + num + "\n";
	}

	private String aw(String name, Block b) {
		return name + "," + "代码行/空行/注释行:" + b.codeLine + "/" + b.emptyLine + "/" + b.noteLine + "\n";
	}
}
