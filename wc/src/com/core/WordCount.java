package com.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.util.Utils;

public class WordCount {
	private static WordCount wc = new WordCount();

	public static WordCount newInstance() {
		return wc;
	}

	public int c(File f) {
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

	public int w(File f, Set<String> set) {
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

	public int w(File f) {
		return w(f, null);
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

	public List<File> s(File f) {
		List<File> list = new LinkedList<File>();
		s(f, list);
		return list;
	}

	public void s(File dir, List<File> list) {
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

	public List<File> s(File f, String pattern, boolean deep) {
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

	private void s(File dir, List<File> list, String pattern) {
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

	private static class Block {
		int codeLine;
		int emptyLine;
		int noteLine;

		public Block(int codeLine, int emptyLine, int noteLine) {
			this.codeLine = codeLine;
			this.emptyLine = emptyLine;
			this.noteLine = noteLine;
		}
	}

	public Block a(File f) {
		int codeLine = 0, emptyLine = 0, noteLine = 0;
		BufferedReader reader = null;
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

	public Set<String> e(File f) {
		Set<String> set = new HashSet<String>();
		BufferedReader reader = null;
		if (!f.exists())
			return set;
		try {
			reader = new BufferedReader(new FileReader(f));

			String s = null;
			while ((s = reader.readLine()) != null) {
				set.add(s);
			}
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

	public void execute(Parameter p) {
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

	private static String cw(String name, int num) {
		return name + "," + "字符数:" + num + "\n";
	}

	private static String ww(String name, int num) {
		return name + "," + "单数:" + num + "\n";
	}

	private static String lw(String name, int num) {
		return name + "," + "行数:" + num + "\n";
	}

	private String aw(String name, Block b) {
		return name + "," + "代码行/空行/注释行:" + b.codeLine + "/" + b.emptyLine + "/" + b.noteLine + "\n";
	}
}
