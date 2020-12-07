凡是这种整个数组都是正数的然后又是less than的。大概都是two pointer的sliding window了。  

本题最大收获就是在确定最左端i以及最右端j的情况下，在i的for loop 里面，subarray总数可以表示为count += j-i+1

[LeetCode Link](https://leetcode.com/problems/subarray-product-less-than-k/)