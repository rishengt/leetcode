思虑良久还是把这到底归到Greedy下面吧，因为不是最优解没意义

#### DP
这道题可以用时间序列型II的解法来写  
定义：dp[i] -> S[0:i]之间的最长递增subsequence，以S[i]为结尾  
递推过程：  
```java
    dp[0] = 1;
    for(int i = 1; i<S.length; i++){
        for(int j = 0; j<=i; j++){
            if(S[j]<S[i]){ 
                dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }
}
```
用时间序列型II来解答是O(n^2)的，不够优秀
####Greedy
还有一种比较骚的操作就是维护一条dp[]数组，每次遇到比它里面element小的东西就换掉，换取后面来的element有机会加到这
```java
    int[] dp = new int[nums.length];
            int len = 0;
            for (int num : nums) {
                int i = Arrays.binarySearch(dp, 0, len, num);
                if (i < 0) {
                    i = -(i + 1);
                }
                dp[i] = num;
                if (i == len) {
                    len++;
                }
            }
            return len;
```
下次自己挑战一下n log n 以及自己实现Binary search;

[LeetCode Link](https://leetcode.com/problems/longest-increasing-subsequence/)