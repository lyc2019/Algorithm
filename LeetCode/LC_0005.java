/*
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"
 */
import java.util.Scanner;
class LC_0005 {

    String longestPalindrome(String s) {

        StringBuffer ss = new StringBuffer("$#");
        for(int i = 0; i < s.length(); ++i) {
            ss.append(s.charAt(i));
            ss.append("#");
        }

        String new_s = ss.toString();
        int[] p = new int[new_s.length()];
        int mx = 0;
        int id = 0;
        int resLen = 0;
        int resCenter = 0;

        for (int i = 1; i < new_s.length(); ++i) {

            p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;
            while (i + p[i] < new_s.length() && i - p[i] >= 0 && new_s.charAt(i + p[i]) == new_s.charAt(i - p[i])) {
                ++p[i];
            }

            if (mx < i + p[i]) {
                
                mx = i + p[i];
                id = i;
            }

            if (resLen < p[i]) {
                
                resLen = p[i];
                resCenter = i;
            }
        }

        return s.substring((resCenter - resLen) / 2, (resCenter - resLen) / 2 + (resLen - 1));
    }
	// private static int maxLen = 0;
	// private static String sub = "";

 //    String longestPalindrome(String s) {
        
 //        if (s.length() <= 1) {
        	
 //        	return s;
 //        }

 //        for (int i = 0; i < s.length() - 1; i++) {
        	
 //        	findLogestPalindrome(s, i, i);//奇数
 //        	findLogestPalindrome(s, i, i+1);//偶数
 //        }
 //        return sub;
 //    }

 //    void findLogestPalindrome(String s, int low, int high) {

 //    	while (low >= 0 && high <= s.length() - 1) {

 //    		if (s.charAt(low) == s.charAt(high)) {
    			
 //    			if (high - low + 1 > maxLen) {
    				
 //    				maxLen = high - low + 1;
 //    				sub = s.substring(low, high + 1);
 //    			}
 //    			low--;
 //    			high++;
 //    		}
 //    		else {

 //    			break;
 //    		}
 //    	}
 //    }

    public static void main(String[] args){
        LC_0005 test = new LC_0005();
        Scanner in = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String A = in.nextLine();
        System.out.println("输入目标字符串中最长回文串为："+test.longestPalindrome(A));
    }
}