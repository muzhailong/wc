package com.util;

public class Utils {

	private Utils() {}
	
	//辅助方法 实现通配符的匹配
	public static boolean match(String p,String t) {
		int len1=p.length();
		int len2=t.length();
		boolean[][]dp=new boolean[len1+1][len2+1];
		dp[0][0]=true;
		
		for(int i=1;i<=len1;++i) {
			char pc=p.charAt(i-1);
			dp[i][0]=dp[i-1][0]&&pc=='*';
			for(int j=1;j<=len2;++j) {
				char tc=t.charAt(j-1);
				if(pc=='*') {
					dp[i][j]=dp[i-1][j]||dp[i][j-1];
				}else {
					dp[i][j]=dp[i-1][j-1]&&pc==tc;
				}
			}
		}
		return dp[len1][len2];
	}
}
