KMP模式匹配算法（E.E.Knuth、J.H.Morris和V.R.Pratt)
S="abcdefgab"
T="abcdex"

如果我们知道T串中首字符“a”与T中后面的字符均不相等（前提）。
而T串中的第二位的“b”与S串中第二位的“b”,那么意味着，T串中首字符“a”与S串中的第二位“b”是不可能相等的。

### next数组值推导
![image.png](https://upload-images.jianshu.io/upload_images/15146921-caefc4e4cd90ce28.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

##### 推导
T="abcdex"推导过程
1. 当j=1时，next[1]=0
2. 当j=2时，j由1到j-1就只有字符“a”，属于其他情况next[2]=1
3. 当j=3时，j由1到j-1串是“ab”，显然“a”与“b”不相等，属其他情况，next[3]=1
4. 以后同理，所以最终此T串的next[j]为011111

##### abcdex
| j | 123456 |
| :-: | :---: |
| 模式串T | abcdex |
| next[j] | 011111 | 

##### abcabx
| j | 123456 |
| :-: | :---: |
| 模式串T | abcabx |
| next[j] | 011123 | 

##### ababaaaba
| j | 123456789 |
| :-: | :---: |
| 模式串T | ababaaaba |
| next[j] | 011234223 | 

##### aaaaaaaab
| j | 123456789 |
| :-: | :---: |
| 模式串T | aaaaaaaab |
| next[j] | 011123 | 

##### 代码实现
```
void getNext(char *t, int *next) {
    
    int j = 1;
    int k = 0;
    
//    next[j] = 0;
    int len = (int)strlen(t);
    while (j < len + 1) { 
        
        if (k == 0 || t[j - 1] == t[k - 1]) { // t[j]表示后缀的单个字符，t[k]表示前缀的单个字符
            
            ++j;
            ++k;
            next[j] = k;
        }
        else {
            
            k = next[k]; // 若字符不相同，则j值回溯
        }
    }
}
```
##### 推导
### nextval数组值推导
T="ababaaaba"的推导过程
1. 当j=1时，nextval[1] = 0
2. 当j=2时，因第二位字符“b”的next值是1，而第一位就是“a”，它们不相等，所以nextval[2] = next[2] =1维持原值
3. 当j=3时，因为第三位字符“a”的next值为1，所以与第一位的“a”比较得知它们相等，所以nextval[3]=nextval[1] = 0
4. 当j=4时，第四位字符“b”next值为2，所以与第二位的“b”比较得到结果是相等，因此nextval[4]=nextval[2] = 1
5. 当j=5时，next值为3，第五个字符“a”与第三个字符“a”相等，因此nextval[5]=nextval[3]=0
6. 当j=6时，next值为4，第六个字符“a”与第四个字符“b”不相等，因此nextval[6]=4
7. 当j=7时，next值为2，第七个字符“a”与第二个字符“b”相等，因此nextval[7]=2
8. 当j=8时，next值为2，第八个字符“b”与第二个字符“b”相等，因此nextval[8]=nextval[2]=1
9. 当j=9时，next值为3，第九个字符“a”与第三个字符“a”相等，因此nextval[9]=nextval[3]=1

##### ababaaaba
| j | 123456789 |
| :-: | :---: |
| 模式串T | ababaaaba |
| next[j] | 011234223 | 
| nextval[j] | 010104210 | 

##### aaaaaaaab
| j | 123456789 |
| :-: | :---: |
| 模式串T | aaaaaaaab |
| next[j] | 012345678 | 
| nextval[j] | 000000008 | 

##### 代码实现
```
void getNextval(char *t, int *next) {
    
    int j = 1;
    int k = 0;
    
//        next[j] = 0;
    int len = (int)strlen(t);
    while (j < len + 1) {
        
        if (k == 0 || t[j - 1] == t[k - 1]) { // t[j]表示后缀的单个字符，t[k]表示前缀的单个字符
            
            ++j;
            ++k;
            if (t[j - 1] != t[k - 1]) {
                next[j] = k;
            }
            else {
                next[j] = next[k];
            }
        }
        else {
            
            k = next[k]; // 若字符不相同，则j值回溯
        }
    }
}
```

### KMP代码实现
```
// 返回子串t在主串s中第pos个字符之后的位置，若不存在，则函数返回返回值0
int indexKMP(char *s, char *t, int pos) {
    
    int i = pos; //i 用于主串s当前位置下标值，若pos不为1，则从pos位置开始
    int j = 1; //j 用于子串T中当前位置下标值
    
    int sLen = (int)strlen(s);
    int tLen = (int)strlen(t);
    int *next = malloc(sizeof(int) * (tLen));
    getNext(t, next); // 对串t做分析，得到next数组
    
    while (i <= sLen && j <= tLen) {
        
        if (j == 0 || s[i - 1] == t[j - 1]) { // 两字母相等则继续
            
            ++i;
            ++j;
        }
        else {
            j = next[j]; // j退回合适的位置，i值不变
        }
    }
    
    if (j > tLen) {
        
        return i - tLen;
    }
    else {
        
        return 0;
    }
}// O(n+m)
```