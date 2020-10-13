这道题你用builtin function 来写肯定是很简单的  
```java
Integer.toBinaryString(num);
Integer.parseInt(bit,2);/**这也不是第一次遇到了，后面的数字告诉了前面的string是什么样的进制，parse过来输出都是10进制的*/
``` 

但是精髓就在于不用builtin你怎么写  
ans + num = 11.......1 = x;  
ans = x - num;  
细品  

[LeetCode Link](https://leetcode.com/problems/complement-of-base-10-integer/)