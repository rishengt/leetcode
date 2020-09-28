凡是这种整个数组都是正数的。大概都是two pointer的sliding window了。  
这道题跟713. Subarray Product Less Than K 的不同是你要实时update你的min，所以是在while loop里面进行的操作  
713是while完以后求subarray总数所以要while loop之后操作。

[LeetCode Link](https://leetcode.com/problems/minimum-size-subarray-sum/)