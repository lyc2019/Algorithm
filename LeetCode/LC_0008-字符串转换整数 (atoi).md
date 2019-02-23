#### [字符串转换整数 (atoi)](https://leetcode-cn.com/problems/string-to-integer-atoi/)


#### 方案一
---
详细的讨论了各种情况，包括符号，自然数，小数点的出现位置，判断他们是否是数字。个人以为这道题也应该有这么多种情况。但是这题只需要考虑数字和符号的情况：
1. 若字符串开头是空格，则跳过所有空格，到第一个非空格字符，如果没有，则返回0.
2. 若第一个非空格字符是符号+/-，则标记sign的真假，这道题还有个局限性，那就是在c++里面，+-1和-+1都是认可的，都是-1，而在此题里，则会返回0.
3. 若下一个字符不是数字，则返回0. 完全不考虑小数点和自然数的情况，不过这样也好，起码省事了不少。
4. 如果下一个字符是数字，则转为整形存下来，若接下来再有非数字出现，则返回目前的结果。
5. 还需要考虑边界问题，如果超过了整形数的范围，则用边界值替代当前值。

#### C-源代码
---
```
#include <string.h>
#include <limits.h>

int myAtoi(char* str) {
    int len = (int)strlen(str);
    if (len <= 0) {
        return 0;
    }
    
    //统计空格
    int i = 0;
    while (i < len && str[i] == ' ') {
        ++i;
    }
    
    //符号
    int sign = 1;
    if (str[i] == '+' || str[i] == '-') {
        sign = (str[i++] == '+') ? 1 : -1;
    }
    
    int base = 0;
    while (i < len && str[i] >= '0' && str[i] <= '9') {
        if (base > INT_MAX / 10 || (base == INT_MAX / 10 && str[i] - '0' > 7)) {
            return (sign == 1) ? INT_MAX : INT_MIN;
        }
        base = 10 * base + (str[i++] - '0');
    }
    
    return base * sign;
}

void test_0008(void) {
    char *s[5] = { "42", "   -42", "4193 with words", "words and 987", "-91283472332" };
    for (int i = 0; i < 5; i++) {
        int len = myAtoi(s[i]);
        printf("len = %d\n",len);
    }
}
```

#### Swift实现
```
func myAtoi(_ str: String) -> Int {
        
        var num = 0
        var symbol = 0
        for char in str {
            
            let empty = (num == 0) && (symbol == 0)
            if num == 0 && char == "0" {
                
                if symbol == 0 {
                    
                    symbol = 1
                }
                continue
            }
            
            if empty && char == " " {
                
                continue
            }
            
            if empty && char == "-" {
                
                symbol = -1
                continue
            }
            
            if empty && char == "+" {
                
                symbol = 1
                continue
            }
            
            if symbol == 0 {
                
                symbol = 1
            }
            
            if char >= "0" && char <= "9" {
                
                num = num * 10 + Int("\(char)")!
                if symbol * num > Int32.max {
                    
                    return Int(Int32.max)
                }
                
                if symbol * num < Int32.min {
                    
                    return Int(Int32.min)
                }
            }
            else {
                
                break
            }
        }
        
        return num * symbol
    }
```
Java 
```
class Solution {
    public int myAtoi(String str) {
        
        if (str.isEmpty()) {
        	
        	return 0;
        }

        int sign = 1;
        int base = 0;
        int i = 0;
        int n = str.length();
        
        if (n == 0) {
        	
        	return 0;
        }
        
        while (i < n && str.charAt(i) == ' ') {

        	++i;
        }
        
        if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
        	
        	sign = (str.charAt(i++) == '+') ? 1 : -1;
        }

        while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {

        	if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {

        		return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;	
        	}
        	base = 10 * base + (str.charAt(i++) - '0');
        }
        return base * sign;
    }
}
```

[参考Grandyang](http://www.cnblogs.com/grandyang/p/4125537.html)