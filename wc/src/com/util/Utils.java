package com.util;

public class Utils {

	private Utils() {}
	
	public static boolean match(String p,String t) {
		String[]arr=p.split("\\*");
		int len=arr.length;
		int pre=-1;
		for(int i=0;i<len;++i) {
			int tmp=t.indexOf(arr[i]);
			if(tmp==-1||tmp<=pre) {
				return false;
			}
			pre=tmp;
		}
		return true;
	}
}
