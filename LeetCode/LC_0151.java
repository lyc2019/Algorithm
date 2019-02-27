/*
给定一个字符串，逐个翻转字符串中的每个单词。

 

示例 1：

输入: "the sky is blue"
输出: "blue is sky the"
示例 2：

输入: "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
示例 3：

输入: "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 */
import java.util.List;
import java.util.ArrayList;

class LC_0151 {
    public String reverseWords(String s) {
        
        int storeIndex = 0;
        int n = s.length();
        StringBuilder sb = new StringBuilder(s).reverse();
        for (int i = 0; i < n; ++i) {
            
            if (sb.charAt(i) != ' ') {
                
                if (storeIndex != 0) {

                    sb.setCharAt(storeIndex++, ' ');
                }

                int j = i;
                while (j < n && sb.charAt(j) != ' ') {

                    sb.setCharAt(storeIndex++, sb.charAt(j++));
                }

                String t = new StringBuilder(sb.substring(storeIndex - (j - i), storeIndex)).reverse().toString();
                sb.replace(storeIndex - (j - i), storeIndex, t);
                i = j;
            }
        }
        sb.setLength(storeIndex);
        return sb.toString();
    }

    // public static void printAll(List<List<Integer>> arr) {

    //     for (int i = 0; i < arr.size(); i++) {

    //         System.out.print(i + "：");
    //         for (int j = 0; j < arr[i].length; j++) {

    //             System.out.print(arr[i] + " ");
    //         }
    //         System.out.println(" ");
    //     }
    // }

    // public static void main(String[] args) {
    //     int[] testArr = { 1, 2, 3 };
    // 	List<List<Integer>> arr = permute(testArr);
    //     printAll(arr);
    // }
}