这道题跟meeting room是一摸一样的，都是由小到大sort end，而 interval系列是sort start。  
为什么是sort end呢因为是end决定包不包含其他气球的  
sort end 你要理解的就是你end sort完了以后你遍历array只要找到start比这个end大的那就一定要多一支箭  
这道题还有一个陷阱就是array sort 里面最好就不要用减法，而用Integer.compare(int a, int b)应为用减法减负数会得到一个正数，不太行  

[LeetCode Link](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/)  