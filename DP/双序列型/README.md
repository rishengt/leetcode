双序列双序列，重点是什么?双！！！！  
其他的你记不住该开几维dp就算了，这个必须得记住，开二维！！！！因为双！！！！  

给定两条序列(一般是string), S1, S2,让你找两者的某种特质的最优解(比如longest common subsequence)之类的  

定义:  
dp[i][j] -> S1[0:i] 跟 S2 [0:j] 之间的最优解  
想方设法往dp[i-1][j-1], dp[i][j-1] 或者 dp[i-1][j]身上转移  

模板:
```java
    for(int i = 0; i<S1.length; i++) //外层loop遍历第一序列
        for(int j = 0; j<S2.length; j++) //里层loop遍历第二序列
            if(S1.charAt(i) == S2.charAt(j)) dp[i][j] = dp[i-1][j-1]+1
            else{
               dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])+1
            }   
```
为啥是两条for啊？？别问，问就是双！！！也不用去想象遍历过程，想不出来，记住模板套就完事了