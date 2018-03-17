package com;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.core.Parameter;
import com.core.WordCount;
import com.ui.View;

public class StartUp {

	public static void start(String[] args) {
		Set<String> st = new HashSet<String>(Arrays.asList(args));

		if (args.length == 0 || st.contains("-x")) {// ui执行
			new View();
		} else {
			Parameter p = new Parameter(args);
			p.parse(null, null, null);
			WordCount.newInstance().execute(p);
		}
	}

	public static void main(String[] args) {
		start(args);
	}
}
