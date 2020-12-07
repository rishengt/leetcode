为啥叫贪心其实感觉也没个定论，大概就是奇奇怪怪的解法但是又很精彩吧

这题先从左往右扫一遍然后把右边评分比左边大的位置update 为左边的糖的数量+1

然后再从右往左扫一遍把左边评分比较高但是糖确得到没有右边多的update为右边的糖的数量+1

[LeetCode Link](https://leetcode.com/problems/candy/)