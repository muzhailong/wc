package com;

import com.core.Parameter;
import com.core.WordCount;
import com.ui.View;

public class StartUp {
	public static void main(String[] args) {
		if (args.length == 0) {//ui执行
			new View();
		}else {//cmd执行
			Parameter p=new Parameter(args);
			p.parse(null, null,null);
			WordCount.newInstance().execute(p);
		}
	}
}
