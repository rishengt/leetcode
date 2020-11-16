这道题的O(n^2)解法不值一提

关键是HashMap的O(n)解法，因为数组里面是有负数的，所以同一个prefix sum有可能多次出现，我们存的是这一段prefix sum 出现的次数  
ans+=的也是这一段的次数。

[LeetCode Link](https://leetcode.com/problems/subarray-sum-equals-k/)